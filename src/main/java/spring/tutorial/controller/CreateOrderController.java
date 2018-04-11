package spring.tutorial.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.tutorial.model.Order;
import spring.tutorial.model.pojo.OrderRequest;
import spring.tutorial.service.CreateOrderService;

@Controller
public class CreateOrderController {


    private CreateOrderService orderCreator;

    @Autowired
    public CreateOrderController(CreateOrderService orderCreator) {
        this.orderCreator = orderCreator;
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String createOrder(@RequestBody OrderRequest orderRequest) throws JsonProcessingException {

        Order order = orderCreator.createOrder(orderRequest);
        return orderCreator.orderDetailsToString(order);


    }


}
