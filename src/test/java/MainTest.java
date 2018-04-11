import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import spring.tutorial.Main;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.model.pojo.OrderRequest;
import spring.tutorial.model.pojo.google.matrix.Distance;
import spring.tutorial.model.pojo.google.matrix.DistanceElement;
import spring.tutorial.model.pojo.google.matrix.DistanceMatrixResponse;
import spring.tutorial.model.pojo.google.matrix.Row;
import spring.tutorial.repository.*;
import spring.tutorial.security.ShopUserDetailsService;
import spring.tutorial.service.CreateOrderService;
import spring.tutorial.strategy.ClosestLocationSearch;
import spring.tutorial.strategy.SearchStrategy;
import spring.tutorial.strategy.SingleLocationSearch;
import spring.tutorial.util.DistanceComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainTest {

    private Address closeAdd, farAdd, customerAdd;
    private CreateOrderService createOrderService;
    private Map<Integer, Integer> products;
    private OrderRequest request;
    private Product product;
    private Customer customer;
    private Stock stock;

    @Mock
    private StockRepository stockRepo;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRoleRepository roleRepository;
    @Mock
    private OrderRepository orderRep;
    @Mock
    private OrderDetailRepository detailRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private SearchStrategy mockStrategy;
    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private DistanceComparator comparator;

    @Before
    public void setup(){

        closeAdd = new Address("Romania", "Iasi", "Iasi", "Bul Poitiers 11");
        farAdd= new Address("Romania", "Bucuresti", "Ilfov", "Bulevardul Eroilor 1");
        customerAdd = new Address("Romania", "Vaslui", "Vaslui", "Bulevardul Traian nr. 8" );
        product = new Product();
        customer = new Customer();
        stock = new Stock(1, product, new Location(), 1);
        given(customerRepository.findOne(1L)).willReturn(customer);
        given(productRepository.findOne(1L)).willReturn(product);
        given(stockRepo.findByProductAndLocation(anyObject(), anyObject())).willReturn(stock);
        given(restTemplate.getForObject(anyString(), anyObject(), anyString(), anyString()))
                .willReturn(generateMockResponse());
    }

    @Test
    public void createOrderSuccessful() {
        products = new HashMap<>();
        products.put(1,1);
        createOrderService = new CreateOrderService(orderRep,
                detailRepository,
                customerRepository,
                stockRepo,
                productRepository,
                mockStrategy);
        request = new OrderRequest(1,1, products, customerAdd);
        Order order = createOrderService.createOrder(request);

        assertNotNull(order);
        assertTrue(order.getDestination().equals(customerAdd));
        assertTrue(order.getCustomer().equals(customer));
    }

    @Test(expected = OrderNotCreatedException.class)
    public void createOrderCustomerNotFound() {
        products = new HashMap<>();
        products.put(1,1);
        given(customerRepository.findOne(1L)).willReturn(null);
        createOrderService = new CreateOrderService(orderRep,
                detailRepository,
                customerRepository,
                stockRepo,
                productRepository,
                mockStrategy);
        request = new OrderRequest(1,1, products, customerAdd);
        createOrderService.createOrder(request);
    }

    @Test(expected = OrderNotCreatedException.class)
    public void createOrderProductNull() {
        products = new HashMap<>();
        product = new Product();
        products.put(1,1);
        stock = new Stock(1, product, new Location(), 1);
        given(customerRepository.findOne(1L)).willReturn(new Customer());
        given(productRepository.findOne(anyLong())).willReturn(null);
        createOrderService = new CreateOrderService(orderRep,
                detailRepository,
                customerRepository,
                stockRepo,
                productRepository,
                mockStrategy);
        request = new OrderRequest(1,1, products, customerAdd);
        createOrderService.createOrder(request);
    }

    @Test(expected = OrderNotCreatedException.class)
    public void createOrderNoStockFound() {
        products = new HashMap<>();
        product = new Product();
        products.put(1,1);
        stock = new Stock(1, product, new Location(), 1);
        given(customerRepository.findOne(1L)).willReturn(new Customer());
        given(productRepository.findOne(1L)).willReturn(product);
        given(stockRepo.findByProductAndLocation(anyObject(), anyObject())).willReturn(null);
        createOrderService = new CreateOrderService(orderRep,
                detailRepository,
                customerRepository,
                stockRepo,
                productRepository,
                mockStrategy);
        request = new OrderRequest(1,1, products, customerAdd);
        createOrderService.createOrder(request);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByName() {
        User user = new User();
        user.setPassword("password");
        ShopUserDetailsService shopUserDetailsService = new ShopUserDetailsService(userRepository, roleRepository);

        given(userRepository.findByUsername("user")).willReturn(user);
        given(userRepository.findByUsername("non-user")).willReturn(null);

        UserDetails userDetails = shopUserDetailsService.loadUserByUsername("user");
        assertTrue(userDetails.getPassword().equals("password")); //returned userDetails should contain
        shopUserDetailsService.loadUserByUsername("non-user"); //user not in rep, return exception
    }

    @Test(expected = NoStockFoundException.class)
    public void searchSingleStrategyAvailable() {
        Product product = new Product();
        Customer customer = new Customer();
        customer.setAddress(new Address());

        given(stockRepo.findByProductAndQuantityGreaterThan(product, 1))
                .willReturn(new Stock(1, product, new Location(1, "", new Address()), 1));
        given(stockRepo.findByProductAndQuantityGreaterThan(product, 10))
                .willReturn(null);
        SearchStrategy strategy = new SingleLocationSearch(stockRepo);

        Location location = strategy.findLocation(product, 1, customer); //search 1 returns location 1
        assertEquals(1, location.getId());
        strategy.findLocation(product, 10, customer); //search 10 returns exception
    }

    @Test(expected = NoStockFoundException.class)
    public void searchClosestStrategyAvailable() {
        Product product = new Product();
        Customer customer = new Customer();
        customer.setAddress(customerAdd);

        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1, product, new Location(1, "1", closeAdd), 1));
        stocks.add(new Stock(1, product, new Location(1, "2", farAdd), 2));

        given(stockRepo.findAllByProductAndQuantityGreaterThan(product, 1))
                .willReturn(stocks);
        given(stockRepo.findAllByProductAndQuantityGreaterThan(product, 2))
                .willReturn(null);

        SearchStrategy strategy = new ClosestLocationSearch(stockRepo, comparator, locationRepository);

        assertNotNull(strategy.findLocation(product, 1, customer)); //search 1 , get a non null location;
        assertNotNull(strategy.findLocation(product, 2, customer)); //search 2, no stock found, throws exception;
        strategy.findLocation(product, 2, customer); //search 2, no stock found, throws exception;
    }

    private DistanceMatrixResponse generateMockResponse(){
        DistanceMatrixResponse response = new DistanceMatrixResponse();
        List<Row> rows = new ArrayList<>();
        List<DistanceElement> distanceElements = new ArrayList<>();
        DistanceElement distanceElement1 = new DistanceElement();
        Distance distance1 = new Distance();
        distance1.setValue(1);
        DistanceElement distanceElement2 = new DistanceElement();
        Distance distance2 = new Distance();
        distance2.setValue(2);
        distanceElement1.setDistance(distance1);
        distanceElement2.setDistance(distance2);
        distanceElements.add(distanceElement1);
        distanceElements.add(distanceElement2);
        Row row = new Row();
        row.setElements(distanceElements);
        rows.add(row);
        response.setRows(rows);
        return response;
    }

}
