package com.jp.ebankmcpclient.telegram;

import com.jp.ebankmcpclient.agents.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.client.okhttp.*;
import org.telegram.telegrambots.longpolling.*;
import org.telegram.telegrambots.longpolling.util.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.*;
import org.telegram.telegrambots.meta.generics.*;


@Component
public class EbankTelegramBot implements LongPollingSingleThreadUpdateConsumer {

    private String telegramBotToken;
    private EbankAgent ebankAgent;
    private final TelegramClient telegramClient;
    private TelegramBotsLongPollingApplication botsApplication;

    public EbankTelegramBot(EbankAgent ebankAgent, @Value("${telegram.token}") String telegramBotToken  ) {
        this.ebankAgent = ebankAgent;
        this.telegramBotToken = telegramBotToken;
        this.telegramClient = new OkHttpTelegramClient(telegramBotToken);

    }

    @PostConstruct
    public void registerTelegramBot() {
        try {
            botsApplication = new TelegramBotsLongPollingApplication();
            botsApplication.registerBot(telegramBotToken, this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;
        Long chatId = update.getMessage().getChatId();
        String answer = ebankAgent.chat(update.getMessage().getText());
        try {
            telegramClient.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(answer)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void stop() throws Exception {
        if (botsApplication != null) botsApplication.close();
    }


}
