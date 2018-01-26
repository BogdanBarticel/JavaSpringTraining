import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whatever.Main;
import whatever.Product;
import whatever.ProductRepository;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class MainTest {
    @Autowired
    private ProductRepository prodRepository;

    @Test
    public void findAllProducts() {
        List<Product> prods = prodRepository.findAll();
        assertNotNull(prods);
        assertTrue(!prods.isEmpty());
    }
}
