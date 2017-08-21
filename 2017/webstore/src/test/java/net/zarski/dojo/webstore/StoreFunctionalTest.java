package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Cart;
import net.zarski.dojo.webstore.domain.CartPosition;
import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.repositories.CartsRepository;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by lb_lb on 19.08.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreFunctionalTest {

    private static final String SEARCH_NAME = "Tomatoes";
    private static final String PRODUCT_ID = "1";
    private static final String CART_ID = "1";
    private static final String EXISTING_CART = "543ASD";
    private static final int EXAMPLE_PRODUCTS_AMOUNT = 3;
    private static final String EXISTING_CART_WITH_PRODUCT = "QWE123";
    private static final String EXISTING_CART_WITH_2_PRODUCTS = "ZXC321";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CartsRepository cartsRepository;


    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void downloadsListOfProducts() {
        Product[] products = restTemplate.getForObject("/products", Product[].class);
        assertThat(Arrays.asList(products))
                .contains(
                        new Product(1L, "Carrots", "Fresh and juicy carrots from outer space"),
                        new Product(2L, "Tomatoes", "Round and red, like sun - during blood bath sundown"));
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void searchProductsByName() {
        Product[] products = restTemplate.getForObject("/products?name={name}", Product[].class, SEARCH_NAME);
        assertThat(Arrays.asList(products))
                .containsExactly(
                        new Product(2L, "Tomatoes", "Round and red, like sun - during blood bath sundown"));
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void getProductDetails() {
        Product product = restTemplate.getForObject("/products/{id}", Product.class, PRODUCT_ID);
        assertThat(product)
                .isEqualTo(
                        new Product(1L, "Carrots", "Fresh and juicy carrots from outer space"));
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void createCart() throws IOException {

        HttpEntity<String> request = new HttpEntity<>("123F");
        ResponseEntity<Cart> response = restTemplate.exchange("/carts", HttpMethod.POST, request, Cart.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isOfAnyClassIn(Long.class);
        assertThat(response.getBody().getSessionId()).isEqualTo("123F");
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void addProductToCart() throws IOException {
        ResponseEntity<String> response = restTemplate.exchange("/carts/{session_id}/products/{product_id}?amount={amount}", HttpMethod.PUT, null, String.class, EXISTING_CART, PRODUCT_ID, EXAMPLE_PRODUCTS_AMOUNT);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(cartsRepository.findBySessionId(EXISTING_CART).getProducts()).hasSize(1);
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void removeProductFromCart() throws IOException {

        ResponseEntity<String> response = restTemplate.exchange("/carts/{session_id}/products/{product_id}", HttpMethod.DELETE, null, String.class, EXISTING_CART_WITH_PRODUCT, PRODUCT_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(cartsRepository.findBySessionId(EXISTING_CART_WITH_PRODUCT).getProducts()).hasSize(0);
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void updateAmountOfProductInCartWhenProductAlreadyAdded() throws IOException {
        assertThat(cartsRepository.findBySessionId(EXISTING_CART_WITH_2_PRODUCTS).getProducts()).hasSize(2);

        int newAmount = 1;
        ResponseEntity<String> response = restTemplate.exchange("/carts/{session_id}/products/{product_id}?amount={amount}", HttpMethod.PUT, null, String.class, EXISTING_CART_WITH_2_PRODUCTS, PRODUCT_ID, newAmount);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Optional<CartPosition> cartPosition = cartsRepository.findBySessionId(EXISTING_CART_WITH_2_PRODUCTS).getProducts().stream().findFirst();
        assertThat(cartPosition).isNotEmpty();
        assertThat(cartPosition.get()).hasFieldOrPropertyWithValue("amount", 4);
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void updateAmountOfProductInCart() throws IOException {
        assertThat(cartsRepository.findBySessionId(EXISTING_CART_WITH_PRODUCT).getProducts()).hasSize(1);

        int newAmount = 1;
        ResponseEntity<String> response = restTemplate.exchange("/carts/{session_id}/products/{product_id}?amount={amount}", HttpMethod.PATCH, null, String.class, EXISTING_CART_WITH_PRODUCT, PRODUCT_ID, newAmount);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Optional<CartPosition> cartPosition = cartsRepository.findBySessionId(EXISTING_CART_WITH_PRODUCT).getProducts().stream().findFirst();
        assertThat(cartPosition).isNotEmpty();
        assertThat(cartPosition.get()).hasFieldOrPropertyWithValue("amount", 1);
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void getCart() {
        Cart cart = restTemplate.getForObject("/carts/{id}", Cart.class, EXISTING_CART);
        assertThat(cart).hasFieldOrPropertyWithValue("sessionId", EXISTING_CART);

    }

}