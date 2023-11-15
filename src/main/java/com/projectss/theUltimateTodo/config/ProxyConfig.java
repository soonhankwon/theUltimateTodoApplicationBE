
package com.projectss.theUltimateTodo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class ProxyConfig {
    @Bean
    public RestTemplate restTemplate() {
        // 프록시 설정
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("krmp-proxy.9rum.cc", 3128));
        factory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }

}