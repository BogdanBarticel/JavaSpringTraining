package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.tutorial.model.Location;
import spring.tutorial.model.OrderDetail;
import spring.tutorial.model.Revenue;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.repository.RevenueRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class SalesAggregatorService {

    private OrderDetailRepository detailRepository;
    private LocationRepository locationRepository;

    private RevenueRepository revenueRepository;

    @Autowired
    public SalesAggregatorService(OrderDetailRepository detailRepository, LocationRepository locationRepository,RevenueRepository revenueRepository){
        this.detailRepository = detailRepository;
        this.revenueRepository = revenueRepository;
        this.locationRepository = locationRepository;
    }

    public void aggregateSalesForAllLocations(){
        Date date = new Date();
        List<Location> stocks = locationRepository.findAll();
        for(Location location : stocks){
            int sum = getSalesForLocation(location);
            Revenue revenue = new Revenue(location, date, BigInteger.valueOf(sum) );
            revenueRepository.save(revenue);
        }

    }

    private int getSalesForLocation(Location loc) {
        int sum = 0;
        List<OrderDetail> orderDetails = detailRepository.findAllByShippedFrom(loc);
        for(OrderDetail details : orderDetails){
             sum += details.getProduct().getPrice().intValue() * details.getQuantity();
        }
        return sum;
    }


}
