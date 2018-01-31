import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whatever.Controller.CreateOrderService;
import whatever.Main;
import whatever.model.*;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class MainTest {

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    @Autowired
    private ProductRepository prodRepository;

    @Autowired
    private ProductCategoryRepository prodCatRepository;

    @Autowired
    CreateOrderService create;

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
        log.info(stocks.toString());
        assertNotNull(stocks);
        assertTrue(!stocks.isEmpty());
        stocks = stockRep.findByProductAndQuantityGreaterThan(1L, 10L);
        assertTrue(stocks.isEmpty());
        stocks = stockRep.findByProductAndQuantityGreaterThan(1L, 11L);
        assertTrue(stocks.isEmpty());
    }
    @Test
    public void createOrder() {
        OrderRequest request = new OrderRequest();
        request.setCustomer(1);
        request.setProduct(1);
        request.setQuantity(1);
        Address address = new Address();
        address.setAddressCity("a");
        address.setAddressCounty("a");
        address.setAddressCountry("a");
        address.setAddressStreet("a");
        request.setAddress(address);
        Order order = create.createOrder(request);
        log.info(order.toString());
        assertNotNull(order);
    }
}
