package spring.tutorial.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import spring.tutorial.model.Address;
import spring.tutorial.model.Location;
import spring.tutorial.model.pojo.google.matrix.DistanceMatrixResponse;
import spring.tutorial.model.pojo.google.matrix.Row;

import java.util.ArrayList;
import java.util.List;

public class GoogleDistanceComparator implements  DistanceComparator {


    @Value("${google.distance.url}")
    private String url;
    private RestTemplate restTemplate;

    public GoogleDistanceComparator(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Location getClosestLocation(Address destination, List<Location> locations) {
        DistanceMatrixResponse response = restTemplate.getForObject(url,
                DistanceMatrixResponse.class,
                generateParamStringFromList(locations),
                destination.toUrlFormatString()
        );
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
