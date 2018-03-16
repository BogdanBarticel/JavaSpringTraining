package spring.tutorial.configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import spring.tutorial.util.DistanceComparator;
import spring.tutorial.util.GoogleDistanceComparator;

@Configuration
public class DistanceComparatorConfigurator {


    @Bean
    public DistanceComparator distanceComparator(RestTemplate restTemplate) {
        return new GoogleDistanceComparator(restTemplate);
    }


}
