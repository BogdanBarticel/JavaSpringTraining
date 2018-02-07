import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.tutorial.service.CreateOrderService;
import spring.tutorial.service.ExportStockService;
import spring.tutorial.util.SearchStrategy;
import spring.tutorial.util.SingleLocationSearch;
import spring.tutorial.Main;
import spring.tutorial.exceptions.NoStockFoundException;
import spring.tutorial.exceptions.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.repository.ProductCategoryRepository;
import spring.tutorial.repository.ProductRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.repository.SupplierRepository;

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
            Long locationId = locationFinder.findLocation(1L, 9L, stockRep);
            assert (locationId == 1);

    }

    @Test
    public void createOrder() throws OrderNotCreatedException{
        Order order;
        SearchStrategy search = new SingleLocationSearch();
        Address address = new Address("Somewhere", "over", "the", "rainbow");
        OrderRequest request = new OrderRequest(1, 1,1, address);
        Long location;
        Stock controlStock;

        location = search.findLocation(1L, 1L, stockRep);
        controlStock = stockRep.findByProductAndLocation(1L, location);
        assertTrue(controlStock.getQuantity() == 10);
        
        order = orderCreator.createOrder(request);
        assertNotNull(order);
        controlStock = stockRep.findByProductAndLocation(1L,order.getShippedFrom());
        assertTrue(controlStock.getQuantity() == 9);


        request.setQuantity(10);
        order = orderCreator.createOrder(request);
        assertTrue(order == null);

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
