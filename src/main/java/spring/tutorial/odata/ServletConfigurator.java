package spring.tutorial.odata;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfigurator {

    @Bean
    public ServletRegistrationBean odataServlet(ShopServlet servlet) {
        return new ServletRegistrationBean(servlet, "/odata/create/*");
    }
}
