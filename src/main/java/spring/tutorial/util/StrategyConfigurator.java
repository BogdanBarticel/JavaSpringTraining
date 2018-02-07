package spring.tutorial.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StrategyConfigurator {

    @Bean
    public SearchStrategy setSearchStrategy(@Value("${spring.search.strategy}") String strategy){
        SearchStrategy searchStrategy;
        switch (strategy) {
            case "single" : searchStrategy = new SingleLocationSearch();
                            break;
            default :       searchStrategy = new SingleLocationSearch();
                            break;
        }
        return searchStrategy;
    }

}
