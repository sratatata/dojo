package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.services.StoreCore;
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
    private static final long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "Cocoa";
    private static final Product EXPECTED_PRODUCT = new Product(PRODUCT_ID, "Cocoa", "Delicious like apples, but cocoas!");

    @Test
    public void isReturningListOfProducts(){
        ProductsRepository products = mock(ProductsRepository.class);
        StoreCore store = new StoreCore(products);
        when(products.findAll()).thenReturn(Arrays.asList(new Product(1L, "Test1", "Desc1")));

        List<Product> productsList = store.listAllProducts();
        assertThat(productsList).isNotEmpty();
    }

    @Test
    public void isReturningProductById(){
        ProductsRepository products = mock(ProductsRepository.class);
        StoreCore store = new StoreCore(products);
        when(products.findById(PRODUCT_ID)).thenReturn(new Product(PRODUCT_ID, "Test1", "Desc1"));

        Product product = store.findProductById(PRODUCT_ID);
        assertThat(product).hasFieldOrPropertyWithValue("name", "Test1");
        assertThat(product).hasFieldOrPropertyWithValue("description", "Desc1");
    }

    @Test
    public void isReturningProductByName() {
        ProductsRepository products = mock(ProductsRepository.class);
        StoreCore store = new StoreCore(products);
        when(products.findByName(PRODUCT_NAME)).thenReturn(Arrays.asList(EXPECTED_PRODUCT));

        List<Product> resp = store.findProductByName(PRODUCT_NAME);
        assertThat(resp).containsExactly(EXPECTED_PRODUCT);
    }
}
