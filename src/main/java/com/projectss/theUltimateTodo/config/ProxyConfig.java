
package com.projectss.theUltimateTodo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
@Slf4j
public class ProxyConfig {
    @Value("${proxy.host}") // 프록시 호스트를 프로퍼티로 설정하여 외부에서 설정할 수 있도록 합니다.
    private String proxyHost;
    
    @Value("${proxy.port}") // 프록시 포트를 프로퍼티로 설정하여 외부에서 설정할 수 있도록 합니다.
    private int proxyPort;

    @Bean
    public RestTemplate restTemplate() {
        // 프록시 설정
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        factory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setRequestFactory(factory);

        InetSocketAddress proxyAddress = (InetSocketAddress) proxy.address();
        if (proxyAddress != null) {
            log.info("Proxy Host: {}", proxyAddress.getHostString());
            log.info("Proxy Port: {}", proxyAddress.getPort());
            log.info("factory Port: {}", factory.toString());

        }
//        RestTemplate restTemplate = new RestTemplate();


        return restTemplate;
    }
}