package com.projectss.theUltimateTodo.OAuth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/openApi/kakao")
@Slf4j
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping()
    public String kakao(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        String token  = openApiService.getToken(code);
        log.info("token : {} ",token );
        // 리디렉트 수행
        return token;
//
    }
    @PostMapping("callback")
    public ResponseEntity<String> handleKakaoCallback(@RequestBody String callbackData) {
        // 받은 콜백 데이터를 로그에 출력하거나 원하는 처리를 수행합니다.
        log.info("Received KakaoTalk Callback Data:");
        log.info(callbackData);

        // 처리 완료 후 클라이언트에 응답을 반환합니다.
        return new ResponseEntity<>("Callback data received successfully", HttpStatus.OK);
    }


}