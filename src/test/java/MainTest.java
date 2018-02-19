import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.tutorial.model.pojo.StockPojo;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.service.CreateOrderService;
import spring.tutorial.service.ExportStockService;
import spring.tutorial.Main;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainTest {


    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CreateOrderService orderCreator;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private ExportStockService stockExporter;

    private HttpEntity httpEntity;

    @Before
    public void setup(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(
                new MediaType("text", "csv"))
        );
        httpHeaders.setContentType(new MediaType("text", "csv"));
        httpEntity = new HttpEntity<>(httpHeaders);
    }

    @Test(expected = OrderNotCreatedException.class)
    public void createOrder() throws OrderNotCreatedException {
        Product prod = stockRepo.findOne(1L).getProduct();
        Order order;
        Address address = new Address("Somewhere", "over", "the", "rainbow");

    }

    @Test
    public void exportStock() throws NoStockFoundException {
        Location location = new Location();
        List<Stock> stocks;
        stocks = stockExporter.exportAllStocksFromLocation(1);
        assertTrue(!stocks.isEmpty());
        log.info(stocks.toString());
    }


    @Test
    public void getAllStocksByLocationWhenValidLocation() {
        List<Location> locations = locationRepo.findAll();
        Location foundLocation = null;
        if(!locations.isEmpty())
            foundLocation = locations.get(0);
        else Assert.fail("No locations found.");

        ResponseEntity<?> responseEntity = restTemplate.exchange("/export?location=" + foundLocation.getId(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<StockPojo>>() {});
        Assert.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

}
