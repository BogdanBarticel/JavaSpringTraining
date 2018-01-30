package whatever.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whatever.model.*;


@Service
public class CreateService {

    @Autowired
    private OrderRepository orderRep;
    @Autowired
    private LocationRepository locRep;

    private SearchStrategy locationFinder= new SingleLocationSearch();

    public Order createOrder(OrderDetail orderDetail){
        Location loc = locRep.findOne(1L);
        Order order = new Order();
        order.setShippedFrom(locationFinder.findLocation(orderDetail));
        order.setCustomer(1); //todo figure out where to get customer from
        order.setAddressCity(loc.getAddressCity());
        order.setAddressCountry(loc.getAddressCountry());
        order.setAddressStreet(loc.getAddressStreet());
        order.setAddressCounty(loc.getAddressCounty());
        orderRep.save(order);
        return order;
    }
}
