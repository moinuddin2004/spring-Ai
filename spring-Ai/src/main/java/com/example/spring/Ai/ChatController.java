package com.example.spring.Ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

//@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @PostMapping("/Chat")
    public String chat(@RequestBody String prompt) {
        ChatClient.CallResponseSpec response = chatClient.prompt(prompt)
                .call();
        return response.content();

    }

    @PostMapping("/stream")
    public Flux<String> stream(@RequestBody String prompt) {
        ChatClient.StreamResponseSpec response = chatClient.prompt()
                .user(prompt)
                .stream();
        return response.content();

    }

}
