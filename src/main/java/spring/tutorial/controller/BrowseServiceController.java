package spring.tutorial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.ProductRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.util.ShopAuthorityUtil;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BrowseServiceController {

    private ProductRepository prodRep;
    private StockRepository stockRep;

    @Autowired
    public BrowseServiceController(ProductRepository prodRep, StockRepository stockRep) {
        this.prodRep = prodRep;
        this.stockRep = stockRep;
    }

    @GetMapping(value = "/browse")
    public String browse(Model model) {
        model = getProductAndStock(model);
        model = ShopAuthorityUtil.setAuthorityAttributes(model);
        return "browse";
    }


    private Model getProductAndStock(Model model) {
        List<Product> prod = prodRep.findAll();
        List<Integer> stock = new ArrayList<>();
        for (Product p : prod) {
            List<Stock> stockByProduct = stockRep.findByProduct(p);
            if (!stockByProduct.isEmpty()) {
                int quantity = 0;
                for (Stock s : stockByProduct) {
                    quantity = quantity + s.getQuantity();
                }
                stock.add(quantity);
            } else {
                stock.add(0);
            }
        }
        model.addAttribute("stock", stock);
        model.addAttribute("prod", prod);
        return model;
    }
}
