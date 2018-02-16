package spring.tutorial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.tutorial.exception.OrderNotCreatedException;
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
    public Order createOrder(@RequestBody OrderRequest orderRequest) throws OrderNotCreatedException {

        return orderCreator.createOrder(orderRequest);

    }
}
