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
    public String chatbot(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
      log.info(body);

        return "{\n" +
                "    \"version\": \"2.0\",\n" +
                "    \"template\": {\n" +
                "        \"outputs\": [\n" +
                "            {\n" +
                "                \"simpleText\": {\n" +
                "                    \"text\": \"메모에 저장되었습니다. GET\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }
    @PostMapping("/chatbot/test")
    public String chatbotPost(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
        log.info("post chatbot body: {} ",body);

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