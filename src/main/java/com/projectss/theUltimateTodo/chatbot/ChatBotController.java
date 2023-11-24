package com.projectss.theUltimateTodo.chatbot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



        return "{\n" +
                "    \"version\": \"2.0\",\n" +
                "    \"template\": {\n" +
                "        \"outputs\": [\n" +
                "            {\n" +
                "                \"simpleText\": {\n" +
                "                    \"text\": \"메모에 저장되었습니다. POST.\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";


    }
}