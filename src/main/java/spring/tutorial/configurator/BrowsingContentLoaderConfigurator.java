package spring.tutorial.configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.repository.ProductRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.service.BrowsingContentLoaderService;

@Configuration
public class BrowsingContentLoaderConfigurator {

    @Bean
    BrowsingContentLoaderService browsingContentLoader(ProductRepository prodRep, StockRepository stockRep){
        return new BrowsingContentLoaderService(prodRep, stockRep);
    }
}
