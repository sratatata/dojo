package net.zarski.dojo.webstore;

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
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/products", String.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo("[{\"id\":1,\"name\":\"Test1\"},{\"id\":2,\"name\":\"Test2\"}]");
    }
}