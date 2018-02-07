import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.tutorial.util.CreateOrderService;
import spring.tutorial.service.ExportStockService;
import spring.tutorial.Main;
import spring.tutorial.exceptions.NoStockFoundException;
import spring.tutorial.exceptions.OrderNotCreatedException;
import spring.tutorial.model.*;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class MainTest {

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    @Autowired
    CreateOrderService orderCreator;

    @Autowired
    ExportStockService stockExporter;

    @Test(expected=OrderNotCreatedException.class)
    public void createOrder() throws OrderNotCreatedException{
        Order order;
        Address address = new Address("Somewhere", "over", "the", "rainbow");
        OrderRequest request = new OrderRequest(1, 1,1, address);

        order = orderCreator.createOrder(request);
        assertNotNull(order);

        request.setQuantity(10);
        order = orderCreator.createOrder(request);
        assertTrue(order == null);
    }

    @Test
    public void exportStock() throws NoStockFoundException{
        Long locationId = 1L;
        List<Stock> stocks;
        stocks = stockExporter.exportAllStocksFromLocation(locationId);
        assertTrue(!stocks.isEmpty());
        log.info(stocks.toString());

    }
}
