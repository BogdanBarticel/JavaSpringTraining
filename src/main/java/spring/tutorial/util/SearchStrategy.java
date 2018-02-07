package spring.tutorial.util;

import spring.tutorial.repository.StockRepository;

public interface SearchStrategy {

    Long findLocation(Long product, Long quantity, StockRepository stockRep);
}
