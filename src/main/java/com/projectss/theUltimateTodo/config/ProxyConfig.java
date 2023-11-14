import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProxyConfig {
    @Bean
    public RestTemplate restTemplate() {
        // 프록시 설정
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setProxy(new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress("krmp-proxy.9rum.cc", 3128)));
        
        return new RestTemplate(factory);
    }
}