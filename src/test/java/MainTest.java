import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.service.CreateOrderService;
import spring.tutorial.service.ExportStockService;
import spring.tutorial.Main;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class MainTest {

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    @Autowired
    private CreateOrderService orderCreator;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private ExportStockService stockExporter;

    @Test(expected = OrderNotCreatedException.class)
    public void createOrder() throws OrderNotCreatedException {
        Product prod = stockRepo.findOne(1L).getProduct();
        Order order;
        Address address = new Address("Somewhere", "over", "the", "rainbow");
        OrderRequest request = new OrderRequest(new Customer(), prod, 1, address);

        order = orderCreator.createOrder(request);
        assertNotNull(order);

        request.setQuantity(10);
        order = orderCreator.createOrder(request);
        assertTrue(order == null);
    }

    @Test
    public void exportStock() throws NoStockFoundException {
        Location location = new Location();
        List<Stock> stocks;
        stocks = stockExporter.exportAllStocksFromLocation(location,stockRepo);
        assertTrue(!stocks.isEmpty());
        log.info(stocks.toString());

    }
}
