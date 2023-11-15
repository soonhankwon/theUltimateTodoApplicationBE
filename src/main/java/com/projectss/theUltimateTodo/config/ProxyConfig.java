
package com.projectss.theUltimateTodo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class ProxyConfig {
    @Value("${proxy.host}") // 프록시 호스트를 프로퍼티로 설정하여 외부에서 설정할 수 있도록 합니다.
    private String proxyHost;

    @Value("${proxy.port}") // 프록시 포트를 프로퍼티로 설정하여 외부에서 설정할 수 있도록 합니다.
    private int proxyPort;
    @Bean
    public RestTemplate restTemplate() {
        // 프록시 설정
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(proxyHost, proxyPort));
        factory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate(factory);
//        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}