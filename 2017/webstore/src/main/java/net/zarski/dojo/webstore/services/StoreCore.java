package net.zarski.dojo.webstore.services;

import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lb_lb on 18.08.17.
 */
@Service
public class StoreCore {
    ProductsRepository productsRepository;

    @Autowired
    public StoreCore(ProductsRepository products) {
        productsRepository = products;
    }

    public List<Product> listAllProducts() {
        return productsRepository.findAll();
    }
}
