package com.projectss.theUltimateTodo.chatbot;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ChatBotController {
    @GetMapping("/chatbot/test")
    public void chatbot(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
      log.info(body);
      log.info(request.toString());

    }
    @PostMapping("/chatbot/test")
    public void chatbotPost(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
        log.info(body);
        log.info(request.toString());

    }
}