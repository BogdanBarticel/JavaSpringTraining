package spring.tutorial.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.repository.RevenueRepository;
import spring.tutorial.service.SalesAggregatorService;

@Configuration
@EnableScheduling
public class CronScheduler {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private RevenueRepository revenueRepository;
    @Autowired
    private LocationRepository locationRepository;


    @Scheduled(cron = "0/2 * * * * ?")
    public void aggregateSales(){

        SalesAggregatorService aggregator = new SalesAggregatorService(orderDetailRepository, locationRepository, revenueRepository);
        aggregator.aggregateSalesForAllLocations();
    }
}
