package spring.tutorial.util;

import spring.tutorial.model.Address;
import spring.tutorial.model.Location;

import java.util.List;

public interface DistanceComparator {

    public Location getClosestLocation(Address destinations, List<Location> origins);

}
