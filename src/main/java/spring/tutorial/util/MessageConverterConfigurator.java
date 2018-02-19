package spring.tutorial.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageConverterConfigurator {

    @Bean
    public CsvMessageConverter csvMessageConverter() {
        return new CsvMessageConverter();
    }

}
