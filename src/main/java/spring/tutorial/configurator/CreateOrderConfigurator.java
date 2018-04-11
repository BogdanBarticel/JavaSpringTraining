package spring.tutorial.configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.repository.*;
import spring.tutorial.service.CreateOrderService;
import spring.tutorial.strategy.SearchStrategy;

@Configuration
public class CreateOrderConfigurator {

    @Bean
    CreateOrderService createOrderService(OrderRepository orderRep, OrderDetailRepository detailRep, CustomerRepository custRep, StockRepository stockRep, ProductRepository prodRep, SearchStrategy strategy){
        return new CreateOrderService(orderRep, detailRep, custRep, stockRep, prodRep, strategy);
    }
}
