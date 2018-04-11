package spring.tutorial.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import spring.tutorial.service.SalesAggregatorService;

@Configuration
@EnableScheduling
public class CronScheduler {

    private SalesAggregatorService salesAggregatorService;

    @Autowired
    public CronScheduler(SalesAggregatorService salesAggregatorService) {
        this.salesAggregatorService = salesAggregatorService;
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void aggregateSales(){
        salesAggregatorService.aggregateSalesForAllLocations();
    }
}
