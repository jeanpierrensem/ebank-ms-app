package com.jp.ebankmcpclient.agents;

import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.chat.memory.*;
import org.springframework.ai.tool.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Component
public class EbankAgent {
    private final ChatClient chatClient;

    public EbankAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Tu es un assistant bancaire pour l'application qui gère les comptes et les clients
                        Tu réponds en français, de manière concise et professionnelle.
                        Utilise les outils disponibles pour consulter les clients et les comptes bancaires.
                        N'invente jamais de données : si une information n'est pas disponible, répond simplement : JE NE SAIS PAS
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }

    public  final String  chat(String query){
        return chatClient
                .prompt(query)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID, "CI"))
                .call().content();
    }

    public  final Flux<String> chatStream(String query){
        return chatClient
                .prompt(query)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID, "CI"))
                .stream().content();
    }
}
