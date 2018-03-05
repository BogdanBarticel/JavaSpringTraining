package spring.tutorial.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.repository.ProductRepository;
import spring.tutorial.repository.StockRepository;


@Configuration
public class StrategyConfigurator {

    private StockRepository stockRepo;
    private ProductRepository prodRepo;

    @Autowired
    public StrategyConfigurator(StockRepository stockRepo, ProductRepository prodRepo) {
        this.stockRepo = stockRepo;
        this.prodRepo = prodRepo;
    }

    public StrategyConfigurator() {
    }

    @Bean
    public SearchStrategy setSearchStrategy(@Value("${spring.search.strategy}") String strategy) {

        switch (strategy) {
            case "single":
                return new SingleLocationSearch(stockRepo, prodRepo);
            default:
                return new SingleLocationSearch(stockRepo, prodRepo);
        }
    }

}
