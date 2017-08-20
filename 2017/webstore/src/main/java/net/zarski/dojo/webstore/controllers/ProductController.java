package net.zarski.dojo.webstore.controllers;

/**
 * Created by lb_lb on 18.08.17.
 */

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.services.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    Store store;

    @RequestMapping(value = "", method = RequestMethod.GET, produces="application/json")
    public List<Product> listOfProducts() {
        return store.listAllProducts();
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces="application/json", params="name")
    public List<Product> searchProductsByName(@RequestParam(value="name", required = true) String name) {
        return store.findProductByName(name);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces="application/json")
    public Product productDetails(@PathVariable("id") Long id) {
        return store.findProductById(id);
    }



}
