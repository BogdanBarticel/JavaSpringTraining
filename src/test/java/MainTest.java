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
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import spring.tutorial.configurator.StrategyConfigurator;
import spring.tutorial.model.pojo.StockPojo;
import spring.tutorial.repository.CustomerRepository;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.ProductRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.service.CreateOrderService;
import spring.tutorial.service.ExportStockService;
import spring.tutorial.Main;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.strategy.SearchStrategy;
import spring.tutorial.strategy.SingleLocationSearch;

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
    private ProductRepository productRepo;

    @Autowired
    private CustomerRepository customerRepo;


    @Autowired
    private ExportStockService stockExporter;

    @Autowired
    private UserDetailsService userDetailsService;

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

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByName() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetails userDetails = userDetailsService.loadUserByUsername("user1");
        assertTrue(encoder.matches("msg123", userDetails.getPassword()));
        userDetails = userDetailsService.loadUserByUsername("non-user");
    }


    @Test(expected = NoStockFoundException.class)
    public void searchSingleStrategyAvailable() {
        SearchStrategy strategy = new SingleLocationSearch(stockRepo);
        Product product = productRepo.findOne(1L);
        Customer customer = customerRepo.findOne(1L);
        strategy.findLocation(product, 1, customer); //search 1 finds one
        strategy.findLocation(product, 10, customer); //search 10 finds less

    }

    @Test
    public void searchClosestStrategyAvailable() {
        SearchStrategy strategy = new SingleLocationSearch(stockRepo);
        Product product = productRepo.findOne(1L);
        Customer customer = customerRepo.findOne(1L);
        strategy.findLocation(product, 1, customer); //search 1 finds one
        strategy.findLocation(product, 10, customer); //search 10 finds less
    }


    @Test
    public void updateStock() {

    }

    @Test
    public void createOrder() {

    }

    @Test
    public void exportAllStock() throws NoStockFoundException {
        Location location = new Location();
        List<Stock> stocks;
        stocks = stockExporter.exportAllStocksFromLocation(1);
        assertTrue(!stocks.isEmpty());
        log.info(stocks.toString());
    }

}
