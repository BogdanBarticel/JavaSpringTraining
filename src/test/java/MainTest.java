import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whatever.Main;
import whatever.model.Product;
import whatever.model.ProductCategoryRepository;
import whatever.model.ProductRepository;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class MainTest {
    @Autowired
    private ProductRepository prodRepository;

    @Autowired
    private ProductCategoryRepository prodCatRepository;

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

}
