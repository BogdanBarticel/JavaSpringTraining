package spring.tutorial.configurator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.strategy.ClosestLocationSearch;
import spring.tutorial.strategy.SearchStrategy;
import spring.tutorial.strategy.SingleLocationSearch;
import spring.tutorial.util.DistanceComparator;


@Configuration
public class StrategyConfigurator {


    @Bean
    public SearchStrategy searchStrategy(@Value("${spring.search.strategy}") String strategy, StockRepository stockRepo, DistanceComparator comparator, LocationRepository locationRepo) {

        switch (strategy) {
            case "single":
                return new SingleLocationSearch(stockRepo);
            case "closest":
                return new ClosestLocationSearch(stockRepo, comparator, locationRepo);
            default:
                return new SingleLocationSearch(stockRepo);
        }
    }

}
