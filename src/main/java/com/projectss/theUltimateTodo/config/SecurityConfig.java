package com.projectss.theUltimateTodo.config;

import com.projectss.theUltimateTodo.OAuth.JpaUserDetailsService;
import com.projectss.theUltimateTodo.OAuth.JwtAuthFilter;
import com.projectss.theUltimateTodo.OAuth.OAuth2UserService;
import com.projectss.theUltimateTodo.OAuth.TokenService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;
    private final TokenService tokenService;
    private final JpaUserDetailsService jpaUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(basic->basic.disable())
                .formLogin(form -> form.disable())
                .csrf(csrf->csrf.disable())
//                .cors()
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> {
//                        authorize.requestMatchers("/login/**").permitAll();
//                        authorize.anyRequest().authenticated();}
//                )
                .authorizeHttpRequests(authorize -> {
                        authorize.requestMatchers("/**").permitAll();}
                )
                .addFilterBefore(new JwtAuthFilter(tokenService, jpaUserDetailsService), UsernamePasswordAuthenticationFilter.class)
//                .userDetailsService(jpaUserDetailsService)
                .logout((logout) -> logout
                        .logoutSuccessUrl("/"))
                .oauth2Login((oauth2) -> oauth2 //로그인 config 시작
                        .loginPage("/login") //
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                            .userService(oAuth2UserService)) // OAuth2 Login 성공시 후 작업
//                            .defaultSuccessUrl("/", false));
                        .successHandler(successHandler())); // 진짜 성공 후 작업

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

            log.info("req : {}",request.toString());
            log.info("res : {}",response.toString());
            log.info("aut : {}",authentication.toString());
            log.info("def : {}", defaultOAuth2User.toString());

//            jwt 발급


            String id = defaultOAuth2User.getAttributes().get("id").toString();
            Map<String, Object> properties = (Map<String, Object>) defaultOAuth2User.getAttributes().get("properties");
            String nickname = properties.get("nickname").toString();
            String token = tokenService.generateToken(id,nickname);
            // 쿠키 생성 및 설정
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(30 * 60); // 쿠키 만료 시간 (초 단위, 여기서는 30분)
            cookie.setPath("/"); // 모든 경로에서 접근 가능하도록 설정
            response.addCookie(cookie);

            // 리디렉트 수행
            response.sendRedirect("https://k28951c68ade3a.user-app.krampoline.com"); // 원하는 리디렉션 경로로 변경 가능

            log.info("id : {}", id);
        });
    }
}
