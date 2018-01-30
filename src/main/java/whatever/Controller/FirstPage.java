package whatever.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whatever.model.ProductRepository;

@Controller
public class FirstPage {

    @Autowired
    ProductRepository prodRep;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name,
                           Model model) {
        model.addAttribute("name", name);
        System.err.println("greetings!");
        return "greeting";
    }

}
