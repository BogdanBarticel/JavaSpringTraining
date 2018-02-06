import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whatever.Controller.CreateOrderService;
import whatever.Controller.ExportStockService;
import whatever.Controller.SearchStrategy;
import whatever.Controller.SingleLocationSearch;
import whatever.Main;
import whatever.exceptions.NoStockFoundException;
import whatever.exceptions.OrderNotCreatedException;
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
    private SupplierRepository supplierRep;

    @Autowired
    CreateOrderService orderCreator;

    @Autowired
    ExportStockService stockExporter;

    @Autowired
    StockRepository stockRep;


    //Todo: change to test single location search strategy
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
        Order order;
        SearchStrategy search = new SingleLocationSearch();
        Address address = new Address("Somewhere", "over", "the", "rainbow");
        OrderRequest request = new OrderRequest(1, 1,1, address);
        Long location;
        Stock controlStock;

        location = search.findLocation(1L, 1L, stockRep);
        controlStock = stockRep.findByProductAndLocation(1L,location);
        assertTrue(controlStock.getQuantity() == 10);


        orderCreator.setStrategy(new SingleLocationSearch());
        try {
            order = orderCreator.createOrder(request);
            assertNotNull(order);
            controlStock = stockRep.findByProductAndLocation(1L,order.getShippedFrom());
            assertTrue(controlStock.getQuantity() == 9);
        } catch (OrderNotCreatedException ex) {
            log.info("OrderNotCreated");
        }


        request.setQuantity(10);
        try {
            order = orderCreator.createOrder(request);
            assertTrue(order == null);
        } catch (OrderNotCreatedException ex) { log.info(ex.getMessage());}
    }
    @Test
    public void exportStock() {
        Long locationId = 1L;
        List<Stock> stocks;
        try {
            stocks = stockExporter.exportAllStocksFromLocation(locationId);
            assertTrue(!stocks.isEmpty());
            log.info(stocks.toString());
        } catch (NoStockFoundException ex) { log.info(ex.getMessage());}
    }
}
