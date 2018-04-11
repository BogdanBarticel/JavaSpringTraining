package spring.tutorial.configurator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class RestTemplateConfigurator {

    @Bean
    public RestTemplate getRestTemplate(@Value("${shop.proxy.domain}") String domain,  @Value("${shop.proxy.port}") int port) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(domain, port)));
        return new RestTemplate(requestFactory);
    }
}
