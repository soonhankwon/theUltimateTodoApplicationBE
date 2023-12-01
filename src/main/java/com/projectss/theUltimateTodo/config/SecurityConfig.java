package com.projectss.theUltimateTodo.config;

import com.projectss.theUltimateTodo.security.JpaUserDetailsService;
import com.projectss.theUltimateTodo.security.filter.JwtAuthFilter;
import com.projectss.theUltimateTodo.security.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final TokenService tokenService;
    private final JpaUserDetailsService jpaUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "https://memo-fe-woad.vercel.app"));
                            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
                            config.setAllowedHeaders(List.of("*"));
                            return config;
                        }))
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                            authorize.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
                            authorize.requestMatchers(new AntPathRequestMatcher("/openApi/**")).permitAll();
                            authorize.requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll();
                            authorize.requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll();
                            authorize.requestMatchers(new AntPathRequestMatcher("/chatbot/**")).permitAll();
                            authorize.anyRequest().authenticated();
                        }
                )
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .addFilterBefore(new JwtAuthFilter(tokenService, jpaUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
