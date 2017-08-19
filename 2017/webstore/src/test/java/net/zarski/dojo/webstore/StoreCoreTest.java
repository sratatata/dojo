package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.domain.StoreCore;
import net.zarski.dojo.webstore.repositories.ProductsRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by lb_lb on 18.08.17.
 */
@Category(FastTests.class)
public class StoreCoreTest {
    @Test
    public void isReturningListOfProducts(){
        ProductsRepository products = mock(ProductsRepository.class);
        StoreCore store = new StoreCore(products);
        when(products.findAll()).thenReturn(Arrays.asList(new Product()));

        List<Product> productsList = store.listAllProducts();
        assertThat(productsList).isNotEmpty();
    }
}
