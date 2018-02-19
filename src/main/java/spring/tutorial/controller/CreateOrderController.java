package spring.tutorial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring.tutorial.model.Order;
import spring.tutorial.model.OrderRequest;
import spring.tutorial.service.CreateOrderService;

@RestController
public class CreateOrderController {

    private CreateOrderService orderCreator;

    @Autowired
    public CreateOrderController(CreateOrderService orderCreator) {
        this.orderCreator = orderCreator;
    }

    @PostMapping(value = "create")
    @ResponseBody
    public Order createOrder(@RequestBody OrderRequest orderRequest){

        return orderCreator.createOrder(orderRequest);

    }
}
