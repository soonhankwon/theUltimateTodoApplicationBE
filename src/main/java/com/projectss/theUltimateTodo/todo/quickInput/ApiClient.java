package com.projectss.theUltimateTodo.todo.quickInput;

import com.projectss.theUltimateTodo.todo.quickInput.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ApiClient {
    private final CurrentDate currentDate;

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    public ApiResponse getApiMethod(String input) {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(apiKey);

        String requestBody = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": \"" + currentDate.inputDate() + "날짜를 표현하는 단어를 주어진 오늘 날짜에 맞춰 나타내고 할 일 내용을 {yyyy-MM-dd, 할 일}의 형식으로 반환합니다.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"" + input + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"temperature\": 0.1,\n" +
                "  \"max_tokens\": 50,\n" +
                "  \"top_p\": 1,\n" +
                "  \"frequency_penalty\": 0,\n" +
                "  \"presence_penalty\": 0\n" +
                "}";

        WebClient webClient = WebClient.builder().baseUrl(apiUrl).defaultHeaders(headers -> headers.addAll(header)).build();

        String responseBody = webClient.post()
                .uri(apiUrl)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = jsonParser.parseMap(responseBody);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) jsonMap.get("choices");
        Map<String, Object> choicesMap = choices.get(0);
        Map<String, Object> messageMap = (Map<String, Object>) choicesMap.get("message");
        String parsing = (String) messageMap.get("content");

        String[] splitParsing = parsing.split(",");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(splitParsing[0], formatter);
        String content = splitParsing[1];

        return new ApiResponse(date, content);
    }
}
