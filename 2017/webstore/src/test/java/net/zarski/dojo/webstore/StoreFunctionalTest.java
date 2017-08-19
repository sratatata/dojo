package net.zarski.dojo.webstore;

import net.zarski.dojo.webstore.domain.Product;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by lb_lb on 19.08.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void downloadsListOfProducts() {
        Product[] products = restTemplate.getForObject("/products", Product[].class);
        assertThat(Arrays.asList(products))
                .contains(
                        new Product(1L, "Test1"),
                        new Product(2L, "Test2"));
    }
}