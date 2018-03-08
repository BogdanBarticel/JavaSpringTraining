package spring.tutorial.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Customer;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;
import spring.tutorial.model.pojo.google.matrix.DistanceMatrixResponse;
import spring.tutorial.model.pojo.google.matrix.Row;
import spring.tutorial.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

public class ClosestLocationSearch implements SearchStrategy {

    private StockRepository stockRep;
    @Value("${google.distance.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public ClosestLocationSearch(StockRepository stockRep) {
        this.stockRep = stockRep;
    }

    @Override
    public Location findLocation(Product product, int quantity, Customer customer) {
        List<Stock> stocks = stockRep.findAllByProductAndQuantityGreaterThan(product, quantity);
        List<Location> locations = new ArrayList<>();
        for (Stock stock : stocks) {
            locations.add(stock.getLocation());
        }

        if (!locations.isEmpty()) {
            return getClosestLocation(restTemplate.getForObject(url,
                    DistanceMatrixResponse.class,
                    generateParamStringFromList(locations),
                    customer.getAddress().toUrlFormatString()
            ), locations);
        } else {
            throw new NoStockFoundException();
        }
    }

    private Location getClosestLocation(DistanceMatrixResponse response, List<Location> locations) {

        List<Integer> distances = new ArrayList<>();
        for (Row row : response.getRows()) {
            distances.add(row.getElements().get(0).getDistance().getValue());
        }

        int shortestDist = 0;
        for (Integer distance : distances) {
            if (shortestDist == 0 || shortestDist > distance) shortestDist = distance;
        }
        return locations.get(distances.indexOf(shortestDist));
    }

    private String generateParamStringFromList(List<Location> destinationsList) {
        String destinations = "";
        for (Location destination : destinationsList) {
            if (!destinations.equals("")) destinations = destinations + "|";
            destinations = destinations + destination.getAddress().toUrlFormatString();
        }
        return destinations;
    }


}
