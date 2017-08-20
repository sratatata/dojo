package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Cart;
import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.repositories.CartsRepository;
import net.zarski.dojo.webstore.services.Store;
import net.zarski.dojo.webstore.repositories.ProductsRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;


/**
 * Created by lb_lb on 18.08.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreIntegrationTest {
    private static final long PRODUCT_ID = 1L;
    private static final String SESSION_ID = "123D";
    private String PRODUCT_NAME = "Tomatoes";

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    CartsRepository cartsRepository;

    @Test
    @Category(SlowTests.class)
    public void findsAllProducts() {
        Store store = new Store(productsRepository, cartsRepository);
        Collection<Product> products = store.listAllProducts();
        assertThat(products.size()).isGreaterThan(1);
    }

    @Test
    @Category(SlowTests.class)
    public void findsProductById() {
        Store store = new Store(productsRepository, cartsRepository);
        Product product = store.findProductById(PRODUCT_ID);
        assertThat(product).hasFieldOrPropertyWithValue("name", "Carrots");
        assertThat(product).hasFieldOrPropertyWithValue("description", "Fresh and juicy carrots from outer space");
    }

    @Test
    @Category(SlowTests.class)
    public void findsProductByName() {
        Store store = new Store(productsRepository, cartsRepository);
        List<Product> products = store.findProductByName(PRODUCT_NAME);
        assertThat(products).containsExactly(new Product(2L, "Tomatoes", "Round and red, like sun - during blood bath sundown"));
    }

    @Test
    @Category(SlowTests.class)
    public void registerNewCart() {
        Store store = new Store(productsRepository, cartsRepository);


        Cart newCart = store.registerNewCart(SESSION_ID);
        assertThat(cartsRepository.exists(newCart.getId())).isTrue();
    }
}
