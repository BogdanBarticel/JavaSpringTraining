package spring.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import spring.tutorial.util.ShopAuthorityHelper;


@Controller
public class BaseRestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/", produces = "text/html")
    public String home(Model model) {
        ShopAuthorityHelper.setAuthorityAttributes(model);
        return "home";

    }


}
