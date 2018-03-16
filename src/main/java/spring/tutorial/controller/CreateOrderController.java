package spring.tutorial.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.tutorial.model.Order;
import spring.tutorial.model.OrderDetail;
import spring.tutorial.model.OrderRequest;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.service.CreateOrderService;

import java.util.List;

@Controller
public class CreateOrderController {

    @Autowired
    private OrderDetailRepository detailRepository;

    private CreateOrderService orderCreator;

    @Autowired
    public CreateOrderController(CreateOrderService orderCreator) {
        this.orderCreator = orderCreator;
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String createOrder(@RequestBody OrderRequest orderRequest) throws JsonProcessingException {

        Order order = orderCreator.createOrder(orderRequest);
        ObjectMapper om = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(om.writeValueAsString(order));
        List<OrderDetail> ods = detailRepository.findByOrder(order);
        for (OrderDetail od : ods) {
            sb.append(", ").append(om.writeValueAsString(od));
        }
        sb.append("]");
        return sb.toString();


    }


}
