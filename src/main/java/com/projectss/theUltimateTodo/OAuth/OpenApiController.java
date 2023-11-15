package com.projectss.theUltimateTodo.OAuth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/openApi")
@Slf4j
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping("/kakao")
    public void kakao(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        Cookie cookie  = openApiService.getToken(code);
        log.info("cookie : {} ",cookie.toString() );
        response.addCookie(cookie);
        // 리디렉트 수행
        response.sendRedirect("https://k28951c68ade3a.user-app.krampoline.com"); // 원하는 리디렉션 경로로 변경 가능
//
    }

}