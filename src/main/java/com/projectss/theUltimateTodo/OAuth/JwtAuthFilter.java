package com.projectss.theUltimateTodo.OAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDetailsService jpaUserDetailsService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request URL = {}", request.getRequestURI());
        log.info("Access Token Value = {}", request.getHeader("Authorization"));

        String jwt = tokenService.resolveToken(request);

        if(StringUtils.hasText(jwt) && tokenService.isTokenValid(jwt)) {
            setAuthentication(tokenService.getUserIdFromToken(jwt));
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String userId) {
        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}