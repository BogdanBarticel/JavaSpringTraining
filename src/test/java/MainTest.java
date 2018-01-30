import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whatever.Controller.CreateService;
import whatever.Controller.SearchStrategy;
import whatever.Main;
import whatever.model.*;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class MainTest {
    @Autowired
    private ProductRepository prodRepository;

    @Autowired
    private ProductCategoryRepository prodCatRepository;

    @Autowired
    CreateService create;

    @Autowired
    StockRepository stockRep;



    @Test
    public void findAllProducts() {
        List<Product> prods =(List) prodRepository.findAll();
        assertNotNull(prods);
        assertTrue(!prods.isEmpty());
    }
    @Test
    public void findAllProductsCategories() {
        List<Product> prods =(List) prodCatRepository.findAll();
        assertNotNull(prods);
        assertTrue(!prods.isEmpty());
    }
    @Test
    public void findLocations() {
        List<Stock> stocks = stockRep.findByProductAndQuantityGreaterThan(1L, 9L);
        System.err.println(stocks.toString());
        assertNotNull(stocks);
        assertTrue(!stocks.isEmpty());
        stocks = stockRep.findByProductAndQuantityGreaterThan(1L, 10L);
        assertTrue(stocks.isEmpty());
        stocks = stockRep.findByProductAndQuantityGreaterThan(1L, 11L);
        assertTrue(stocks.isEmpty());
    }
}
