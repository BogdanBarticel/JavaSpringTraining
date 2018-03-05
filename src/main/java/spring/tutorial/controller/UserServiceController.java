package spring.tutorial.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.tutorial.model.Address;
import spring.tutorial.model.Customer;
import spring.tutorial.model.User;
import spring.tutorial.util.ShopAuthorityUtil;


/**
 * Sample service.
 *
 * @author P.J. Meisch (pj.meisch@sothawo.com).
 */
@Controller
public class UserServiceController {

    @GetMapping(value = "/login")
    public String login(Model model) {
        ShopAuthorityUtil.setAuthorityAttributes(model);
        return "login";
    }

    @GetMapping(value = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    @RequestMapping(value = "/register")
    public String getRegisterForm(Model model) {
        model.addAttribute(new Customer());
        model.addAttribute(new Address());
        return "register";
    }
    @PostMapping(value = "/register")
    public String register(@ModelAttribute User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return "registerResult";
    }

}