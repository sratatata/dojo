package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Cart;
import net.zarski.dojo.webstore.domain.CartPosition;
import net.zarski.dojo.webstore.domain.Product;
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

    @Autowired
    private TestRestTemplate restTemplate;

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
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void removeProductFromCart() throws IOException {

        ResponseEntity<String> response = restTemplate.exchange("/carts/{session_id}/products/{product_id}", HttpMethod.DELETE, null, String.class, EXISTING_CART_WITH_PRODUCT, PRODUCT_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void updateAmountOfProductInCart() throws IOException {
        final RequestCallback requestCallback = new RequestCallback() {
            @Override
            public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {

            }
        };

        ClientHttpResponse response = restTemplate.execute("/carts/{cart_id}/products/{product_id}&amount={amount}", HttpMethod.PUT, requestCallback,   new ResponseFromHeadersExtractor(), CART_ID, PRODUCT_ID, "2");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void getCart(){
        Cart cart = restTemplate.getForObject("/products/{id}", Cart.class, CART_ID);
        assertThat(cart)
                .isEqualTo(
                        //TODO update details of cart when designed
                        new Cart("1231234123ASD")
                );
    }

    private static class ResponseFromHeadersExtractor implements ResponseExtractor<ClientHttpResponse> {

        @Override
        public ClientHttpResponse extractData(ClientHttpResponse response) {
            System.out.println("StringFromHeadersExtractor - response headers: " + response.getHeaders());
            return response;
        }
    }
}