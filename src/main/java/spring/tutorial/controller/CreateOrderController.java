package spring.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.tutorial.exceptions.OrderNotCreatedException;
import spring.tutorial.model.OrderRequest;
import spring.tutorial.service.CreateOrderService;

@Controller
public class CreateOrderController {

    @Autowired
    CreateOrderService orderCreator;

    @RequestMapping(method= RequestMethod.POST, value="create", consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createOrder(@RequestBody OrderRequest orderRequest) throws OrderNotCreatedException {

        return orderCreator.createOrder(orderRequest).toString();

    }
}
