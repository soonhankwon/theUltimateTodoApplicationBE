package com.projectss.theUltimateTodo.chatbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatBotController {
    private final ChatBotService chatBotService;

    @PostMapping("/chatbot/register")
    public String chatbot(@RequestBody String body) {

        log.info("chatbot register body :  {}",body);
        String result = chatBotService.register(body);
        log.info("result : {}",result);
        return result;
    }
    @PostMapping("/chatbot/fallback")
    public String chatbotPost(@RequestBody String body) {

        log.info("chatbot fallback body: {} ",body);
        return chatBotService.fallback(body);




    }
}