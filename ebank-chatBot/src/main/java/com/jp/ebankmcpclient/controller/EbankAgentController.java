package com.jp.ebankmcpclient.controller;

import com.jp.ebankmcpclient.agents.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

@RestController
public class EbankAgentController {

    private final EbankAgent ebankAgent;
    public EbankAgentController(EbankAgent ebankAgent) {
        this.ebankAgent = ebankAgent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(@RequestParam(name = "query", defaultValue = "bonjour") String query){
        return ebankAgent.chat(query);
    }

    @GetMapping(value = "/chatstream", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> chatStream(@RequestParam(name = "query", defaultValue = "bonjour") String query){
        return ebankAgent.chatStream(query);
    }
}
