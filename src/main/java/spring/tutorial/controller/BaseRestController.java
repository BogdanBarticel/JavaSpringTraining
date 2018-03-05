package spring.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.tutorial.util.ShopAuthorityUtil;


@Controller
public class BaseRestController {

    @GetMapping(value = "/",  produces = "text/html")
    public String home(Model model) {
        ShopAuthorityUtil.setAuthorityAttributes(model);
        return "home";
    }

}
