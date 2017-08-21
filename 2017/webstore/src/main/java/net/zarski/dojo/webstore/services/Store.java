package net.zarski.dojo.webstore.services;

import net.zarski.dojo.webstore.domain.Cart;
import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.repositories.CartsRepository;
import net.zarski.dojo.webstore.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lb_lb on 18.08.17.
 */
@Service
public class Store {
    private ProductsRepository productsRepository;
    private CartsRepository cartsRepository;

    @Autowired
    public Store(ProductsRepository productsRepository, CartsRepository cartsRepository) {
        this.productsRepository = productsRepository;
        this.cartsRepository = cartsRepository;
    }

    public List<Product> listAllProducts() {
        return productsRepository.findAll();
    }

    public Product findProductById(long productId) {
        return productsRepository.findById(productId);
    }

    public List<Product> findProductByName(String name) {
        return productsRepository.findByName(name);
    }

    public Cart registerNewCart(String sessionId) {
        Cart cart = new Cart(sessionId);
        return cartsRepository.save(cart);
    }

    public void addProductToCart(String sessionId, long productId, int amount) {
        Cart cart = this.findCartBySessionId(sessionId);
        Product product = this.findProductById(productId);
        cart.addProduct(product, amount);
        cartsRepository.save(cart);
    }

    public Cart findCartBySessionId(String sessionId) {
        return cartsRepository.findBySessionId(sessionId);
    }

    public void removeProductToCart(String sessionId, long productId, int amount) {
        Cart cart = this.findCartBySessionId(sessionId);
        Product product = this.findProductById(productId);
        cart.removeProduct(product);
        cartsRepository.save(cart);
    }
}
