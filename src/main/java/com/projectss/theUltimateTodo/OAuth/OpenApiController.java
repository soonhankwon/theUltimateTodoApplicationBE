package com.projectss.theUltimateTodo.OAuth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/openApi")
@Slf4j
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping("/kakao")
    public String kakao(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        String token  = openApiService.getToken(code);
        log.info("token : {} ",token );
        // 리디렉트 수행
        return token;
//
    }
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getGithubUsers")
    public ResponseEntity<String> getGithubUsers() {
        // GitHub API에 프록시를 통해 GET 요청을 보냅니다.
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.github.com/users", String.class);
        return responseEntity;
    }

}