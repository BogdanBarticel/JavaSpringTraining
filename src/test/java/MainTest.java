import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.tutorial.Controller.CreateOrderService;
import spring.tutorial.Controller.ExportStockService;
import spring.tutorial.Controller.SearchStrategy;
import spring.tutorial.Controller.SingleLocationSearch;
import spring.tutorial.Main;
import spring.tutorial.exceptions.LocationNotFoundForProductException;
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



    @Test
    public void findLocationsWhereProductQuantityExists() {
        SearchStrategy locationFinder = new SingleLocationSearch();
        try {
            Long locationId = locationFinder.findLocation(1L, 9L, stockRep);
            assert (locationId == 1);
        } catch (LocationNotFoundForProductException ex) {
            log.info(ex.getMessage());
        }
    }

    @Test(expected=LocationNotFoundForProductException.class)
    public void findLocationsWhereProductQuantityDoesNotExist() throws Exception {
        SearchStrategy locationFinder = new SingleLocationSearch();
        locationFinder.findLocation(1L, 11L, stockRep);
    }

    @Test
    public void createOrder() {
        Order order;
        SearchStrategy search = new SingleLocationSearch();
        Address address = new Address("Somewhere", "over", "the", "rainbow");
        OrderRequest request = new OrderRequest(1, 1,1, address);
        Long location;
        Stock controlStock;

        try {
            location = search.findLocation(1L, 1L, stockRep);
            controlStock = stockRep.findByProductAndLocation(1L, location);
            assertTrue(controlStock.getQuantity() == 10);
        }catch (LocationNotFoundForProductException ex) {
            log.info(ex.getMessage());
        }

        orderCreator.setStrategy(new SingleLocationSearch());
        try {
            order = orderCreator.createOrder(request);
            assertNotNull(order);
            controlStock = stockRep.findByProductAndLocation(1L,order.getShippedFrom());
            assertTrue(controlStock.getQuantity() == 9);
        } catch (OrderNotCreatedException ex) {
            log.info(ex.getMessage());
        } catch (LocationNotFoundForProductException ex) {
            log.info(ex.getMessage());
        }


        request.setQuantity(10);
        try {
            order = orderCreator.createOrder(request);
            assertTrue(order == null);
        } catch (OrderNotCreatedException ex) {
            log.info(ex.getMessage());
        } catch (LocationNotFoundForProductException ex) {
            log.info(ex.getMessage());
        }
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
