package net.zarski.dojo.webstore.domain;

import net.zarski.dojo.webstore.FastTests;
import net.zarski.dojo.webstore.repositories.CartsRepository;
import net.zarski.dojo.webstore.services.Store;
import net.zarski.dojo.webstore.repositories.ProductsRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by lb_lb on 18.08.17.
 */
@Category(FastTests.class)
public class StoreTest {
    private static final long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "Cocoa";
    private static final Product EXPECTED_PRODUCT = new Product(PRODUCT_ID, "Cocoa", "Delicious like apples, but cocoas!");
    private static final String SESSION_ID = "123D";
    private static final Cart EXPECTED_CART = new Cart(SESSION_ID);

    @Test
    public void isReturningListOfProducts(){
        ProductsRepository products = mock(ProductsRepository.class);
        CartsRepository carts = mock(CartsRepository.class);

        Store store = new Store(products, carts);
        when(products.findAll()).thenReturn(Arrays.asList(new Product(1L, "Test1", "Desc1")));

        List<Product> productsList = store.listAllProducts();
        assertThat(productsList).isNotEmpty();
    }

    @Test
    public void isReturningProductById(){
        ProductsRepository products = mock(ProductsRepository.class);
        CartsRepository carts = mock(CartsRepository.class);

        Store store = new Store(products, carts);
        when(products.findById(PRODUCT_ID)).thenReturn(new Product(PRODUCT_ID, "Test1", "Desc1"));

        Product product = store.findProductById(PRODUCT_ID);
        assertThat(product).hasFieldOrPropertyWithValue("name", "Test1");
        assertThat(product).hasFieldOrPropertyWithValue("description", "Desc1");
    }

    @Test
    public void isReturningProductByName() {
        ProductsRepository products = mock(ProductsRepository.class);
        CartsRepository carts = mock(CartsRepository.class);

        Store store = new Store(products, carts);
        when(products.findByName(PRODUCT_NAME)).thenReturn(Collections.singletonList(EXPECTED_PRODUCT));

        List<Product> resp = store.findProductByName(PRODUCT_NAME);
        assertThat(resp).containsExactly(EXPECTED_PRODUCT);
    }

    @Test
    public void isRegisteringNewEmptyCart() {
        CartsRepository carts = mock(CartsRepository.class);
        ProductsRepository products = mock(ProductsRepository.class);
        Store store = new Store(products, carts);
        store.registerNewCart(SESSION_ID);

        verify(carts, atLeastOnce()).save((Cart) argThat(t -> ((Cart)t).getSessionId().equals("123D")));
    }

    @Test
    public void isAddingProductToCart(){
        CartsRepository carts = mock(CartsRepository.class);
        ProductsRepository products = mock(ProductsRepository.class);
        Store store = new Store(products, carts);

        when(products.findById(PRODUCT_ID)).thenReturn(EXPECTED_PRODUCT);
        when(carts.findBySessionId(SESSION_ID)).thenReturn(EXPECTED_CART);
        store.addProductToCart(SESSION_ID, PRODUCT_ID, 3);
        verify(carts, atLeastOnce()).save((Cart) argThat(cart -> ((Cart)cart).getProducts().size() == 1));
    }

    @Test
    public void isReturningCartBySessionId(){
        CartsRepository carts = mock(CartsRepository.class);
        ProductsRepository products = mock(ProductsRepository.class);
        Store store = new Store(products, carts);
        when(carts.findBySessionId(SESSION_ID)).thenReturn(EXPECTED_CART);

        Cart cart = store.findCartBySessionId(SESSION_ID);

        assertThat(cart).isEqualTo(EXPECTED_CART);
    }

    @Test
    public void isRemovingProductFromCart(){
        CartsRepository carts = mock(CartsRepository.class);
        ProductsRepository products = mock(ProductsRepository.class);
        Cart mockedCart = mock(Cart.class); //non anemic domain object, contains logic
        Store store = new Store(products, carts);

        when(products.findById(PRODUCT_ID)).thenReturn(EXPECTED_PRODUCT);
        when(carts.findBySessionId(SESSION_ID)).thenReturn(mockedCart);

        store.removeProductFromCart(SESSION_ID, PRODUCT_ID);

        verify(mockedCart).removeProduct(EXPECTED_PRODUCT);
        verify(carts).save(mockedCart);
    }
}
