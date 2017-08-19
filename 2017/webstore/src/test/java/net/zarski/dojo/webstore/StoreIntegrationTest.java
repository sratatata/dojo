package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.domain.StoreCore;
import net.zarski.dojo.webstore.repositories.ProductsRepository;
import org.apache.catalina.Store;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;


/**
 * Created by lb_lb on 18.08.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreIntegrationTest {
    @Autowired
    ProductsRepository repository;

    @Test
    @Category(SlowTests.class)
    public void findsAllProducts() {
        StoreCore storeCore = new StoreCore(repository);
        Collection<Product> products = storeCore.listAllProducts();
        assertThat(products.size()).isGreaterThan(1);
    }
}
