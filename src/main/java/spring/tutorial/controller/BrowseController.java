package spring.tutorial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.tutorial.service.BrowsingContentLoaderService;
import spring.tutorial.util.ShopAuthorityHelper;

@Controller
public class BrowseController {

    private BrowsingContentLoaderService contentLoader;

    @Autowired
    public BrowseController(BrowsingContentLoaderService contentLoader) {
        this.contentLoader = contentLoader;
    }

    @GetMapping(value = "/browse")
    public String browse(Model model) {
        model = contentLoader.getProductAndStock(model);
        model = ShopAuthorityHelper.setAuthorityAttributes(model);
        return "browse";
    }

}
