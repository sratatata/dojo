package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.services.Store;
import net.zarski.dojo.webstore.repositories.ProductsRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;


/**
 * Created by lb_lb on 18.08.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreIntegrationTest {
    private static final long PRODUCT_ID = 1L;
    @Autowired
    ProductsRepository repository;
    private String PRODUCT_NAME = "Tomatoes";

    @Test
    @Category(SlowTests.class)
    public void findsAllProducts() {
        Store storeCore = new Store(repository);
        Collection<Product> products = storeCore.listAllProducts();
        assertThat(products.size()).isGreaterThan(1);
    }

    @Test
    @Category(SlowTests.class)
    public void findsProductById() {
        Store storeCore = new Store(repository);
        Product product = storeCore.findProductById(PRODUCT_ID);
        assertThat(product).hasFieldOrPropertyWithValue("name", "Carrots");
        assertThat(product).hasFieldOrPropertyWithValue("description", "Fresh and juicy carrots from outer space");
    }

    @Test
    @Category(SlowTests.class)
    public void findsProductByName() {
        Store storeCore = new Store(repository);
        List<Product> products = storeCore.findProductByName(PRODUCT_NAME);
        assertThat(products).containsExactly(new Product(2L, "Tomatoes", "Round and red, like sun - during blood bath sundown"));
    }
}
