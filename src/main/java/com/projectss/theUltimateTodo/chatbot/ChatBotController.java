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

        return chatBotService.register(body);
    }
    @PostMapping("/chatbot/fallback")
    public String chatbotPost(@RequestBody String body) {

        log.info("chatbot fallback body: {} ",body);
        return chatBotService.fallback(body);




    }
    @PostMapping("/chatbot/test")
    public String chatbotTest(@RequestBody String body) {

        log.info("test body : {}",body);
        return "{\n" +
                "  \"version\": \"2.0\",\n" +
                "  \"template\": {\n" +
                "    \"outputs\": [\n" +
                "      {\n" +
                "        \"textCard\": {\n" +
                "          \"title\": \"챗봇 관리자센터에 오신 것을 환영합니다.\",\n" +
                "          \"description\": \"챗봇 관리자센터로 챗봇을 제작해 보세요. \\n카카오톡 채널과 연결하여, 이용자에게 챗봇 서비스를 제공할 수 있습니다.\",\n" +
                "          \"buttons\": [\n" +
                "            {\n" +
                "              \"action\": \"webLink\",\n" +
                "              \"label\": \"소개 보러가기\",\n" +
                "              \"webLinkUrl\": \"https://chatbot.kakao.com/docs/getting-started-overview/\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";


    }
}