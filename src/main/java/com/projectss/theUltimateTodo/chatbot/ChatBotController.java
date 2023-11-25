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
                "          \"title\": \"서비스를 이용하려면 회원가입이 필요합니다. \",\n" +
                "          \"description\": \" 회원가입 시 이곳에 적는 글이 메모에 저장되어 PC에서도 확인하실 수 있습니다. \",\n" +
//                "          \"buttons\": [\n" +
//                "            {\n" +
//                "              \"action\": \"block\",\n" +
//                "              \"blockId\": \"656011bc501358649ef6974a\"\n" +
//                "            },\n" +
//                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";


    }
}

