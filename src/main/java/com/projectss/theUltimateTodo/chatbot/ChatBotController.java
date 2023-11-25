package com.projectss.theUltimateTodo.chatbot;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        log.info("chatbot register body",body);
        return chatBotService.register(body);
    }
    @PostMapping("/chatbot/fallback")
    public String chatbotPost(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {

        log.info("chatbot fallback body: {} ",body);
        return chatBotService.fallback(body);




    }
}