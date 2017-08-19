package net.zarski.dojo.webstore;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by lb_lb on 19.08.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreFunctionalTest {
    @Test
    @Category({SlowTests.class, FunctionalTests.class})
    public void downloadsListOfProducts(){
        assertTrue(false);
    }
}
