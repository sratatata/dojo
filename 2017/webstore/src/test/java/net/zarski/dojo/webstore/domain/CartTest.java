package net.zarski.dojo.webstore.domain;

import net.zarski.dojo.webstore.FastTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.*;

public class CartTest {
    private Product EXPECTED_PRODUCT = new Product(1L, "test1", "desc1");

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

}