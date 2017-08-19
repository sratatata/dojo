package net.zarski.dojo.webstore.controllers;

/**
 * Created by lb_lb on 18.08.17.
 */

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.services.StoreCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class ProductController {
    @Autowired
    StoreCore storeCore;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Product> listOfProducts() {
        return storeCore.listAllProducts();

    }

}
