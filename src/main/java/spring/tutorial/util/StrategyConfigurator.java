package spring.tutorial.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.repository.StockRepository;


@Configuration
public class StrategyConfigurator {

    private StockRepository stockRepo;
    private OrderDetailRepository detailRepo;

    @Autowired
    public StrategyConfigurator(StockRepository stockRepo, OrderDetailRepository detailRepo) {
        this.stockRepo = stockRepo;
        this.detailRepo = detailRepo;
    }

    public StrategyConfigurator() {
    }

    @Bean
    public SearchStrategy setSearchStrategy(@Value("${spring.search.strategy}") String strategy) {

        switch (strategy) {
            case "single":
                return new SingleLocationSearch(stockRepo, detailRepo);
            default:
                return new SingleLocationSearch(stockRepo, detailRepo);
        }
    }

}
