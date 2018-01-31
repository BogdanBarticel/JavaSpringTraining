package whatever.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whatever.model.*;


@Service
public class CreateOrderService {

    @Autowired
    private OrderRepository orderRep;
    @Autowired
    private LocationRepository locationRep;

    private SearchStrategy locationFinder;

    public void setStrategy(SearchStrategy strategy){
        locationFinder = strategy;
    }

    public Order createOrder(OrderRequest request){
        //CREATE ORDER
        Order order = new Order();
        if(locationFinder != null){
            Location loc = locationRep.findOne(locationFinder.findLocation(request.getProduct(), request.getQuantity()));
            order.setShippedFrom(loc.getId());
        }
        order.setDestination(request.getAddress());
        order.setCustomer(request.getCustomer());
        orderRep.save(order);

        //CREATE ORDER_DETAIL
        OrderDetail detail = new OrderDetail();
        detail.setProduct(request.getProduct());
        detail.setQuantity(request.getQuantity());
        detail.setOrder(order.getId());

        return order;
    }
}
