package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.ProductRepository;
import spring.tutorial.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

public class BrowsingContentLoaderService {

    private ProductRepository prodRep;
    private StockRepository stockRep;

    @Autowired
    public BrowsingContentLoaderService(ProductRepository prodRep, StockRepository stockRep){
        this.prodRep = prodRep;
        this.stockRep = stockRep;
    }

    public Model getProductAndStock(Model model) {
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
