package net.zarski.dojo.webstore.controllers;

/**
 * Created by lb_lb on 18.08.17.
 */

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.services.StoreCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class ProductController {
    @Autowired
    StoreCore storeCore;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces="application/json")
    public List<Product> listOfProducts() {
        return storeCore.listAllProducts();

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces="application/json")
    public Product productDetails(@PathVariable("id") Long id) {
        return storeCore.findProductById(id);

    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces="application/json", params="name")
    public List<Product> searchProductsByName(@RequestParam(value="name", required = true) String name) {
        return storeCore.findProductByName(name);

    }

}
