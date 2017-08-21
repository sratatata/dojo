package net.zarski.dojo.webstore.domain;

import net.zarski.dojo.webstore.FastTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.*;

public class CartTest {
    private static final Product EXPECTED_ADDITIONAL_PRODUCT = new Product(2L, "test12", 20, "desc2");
    private Product EXPECTED_PRODUCT = new Product(1L, "test1", 10, "desc1");

    @Test
    @Category(FastTests.class)
    public void addProduct() {
        Cart cart = new Cart("123D");
        cart.addProduct(EXPECTED_PRODUCT, 3);

        assertThat(cart.getProducts())
                .hasOnlyOneElementSatisfying(cartPosition -> {
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("amount", 3);
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("product", EXPECTED_PRODUCT);
                });
    }

    @Test
    @Category(FastTests.class)
    public void addedProductsAreExpectedToBeUnique() {
        Cart cart = new Cart("123D");
        cart.addProduct(EXPECTED_PRODUCT, 3);
        cart.addProduct(EXPECTED_PRODUCT, 3);

        assertThat(cart.getProducts())
                .hasOnlyOneElementSatisfying(cartPosition -> {
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("product", EXPECTED_PRODUCT);
                });
    }

    @Test
    @Category(FastTests.class)
    public void removeExistingProduct() {
        Cart cart = new Cart("123D");
        cart.addProduct(EXPECTED_PRODUCT, 3);

        cart.removeProduct(EXPECTED_PRODUCT);

        assertThat(cart.getProducts()).doesNotContain(
                new CartPosition(EXPECTED_PRODUCT, 3)
        );
    }

    @Test
    @Category(FastTests.class)
    public void increaseProductAmount(){
        Cart cart = new Cart("123D");
        cart.addProduct(EXPECTED_PRODUCT, 3);
        cart.addProduct(EXPECTED_PRODUCT, 3);

        assertThat(cart.getProducts())
                .hasOnlyOneElementSatisfying(cartPosition -> {
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("amount", 6);
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("product", EXPECTED_PRODUCT);
                });
    }
    @Test
    @Category(FastTests.class)
    public void totalsCartPrice(){
        Cart cart = new Cart("123D");
        cart.addProduct(EXPECTED_PRODUCT, 3);
        cart.addProduct(EXPECTED_ADDITIONAL_PRODUCT, 1);

        assertThat(cart.getTotal()).isEqualTo(50);
    }

    @Test
    @Category(FastTests.class)
    public void whenProductsPriceChangeTotalOfAlreadyAddedCartIsNotChanging(){
        Cart cart = new Cart("123D");
        cart.addProduct(EXPECTED_PRODUCT, 3);
        cart.addProduct(EXPECTED_ADDITIONAL_PRODUCT, 1);

        assertThat(cart.getTotal()).isEqualTo(50);

        EXPECTED_PRODUCT.setPrice(100);

        assertThat(cart.getTotal()).isEqualTo(50);
    }

    @Test
    @Category(FastTests.class)
    public void addProductCopiesPrice() {
        Cart cart = new Cart("123D");
        cart.addProduct(EXPECTED_PRODUCT, 3);

        assertThat(cart.getProducts())
                .hasOnlyOneElementSatisfying(cartPosition -> {
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("amount", 3);
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("product", EXPECTED_PRODUCT);
                    assertThat(cartPosition).hasFieldOrPropertyWithValue("unitPrice", EXPECTED_PRODUCT.getPrice());
                });
    }
}