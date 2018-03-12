package spring.tutorial.configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.converter.CsvMessageConverter;


@Configuration
public class MessageConverterConfigurator {

    @Bean
    public CsvMessageConverter csvMessageConverter() {
        return new CsvMessageConverter();
    }

}
