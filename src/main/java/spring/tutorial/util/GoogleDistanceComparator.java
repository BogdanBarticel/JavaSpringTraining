package spring.tutorial.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import spring.tutorial.model.Address;
import spring.tutorial.model.Location;
import spring.tutorial.model.pojo.google.matrix.DistanceMatrixResponse;
import spring.tutorial.model.pojo.google.matrix.Row;

import java.util.ArrayList;
import java.util.List;

public class GoogleDistanceComparator implements DistanceComparator {

    private String url;
    private RestTemplate restTemplate;

    public GoogleDistanceComparator(RestTemplate restTemplate,  @Value("${google.distance.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
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
        StringBuilder sb = new StringBuilder();
        for (Location destination : destinationsList) {
            if (sb.length() > 0) sb.append("|");
            sb.append(destination.getAddress().toUrlFormatString());
        }
        return sb.toString();
    }

}
