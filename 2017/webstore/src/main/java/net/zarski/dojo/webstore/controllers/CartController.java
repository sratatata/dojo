package net.zarski.dojo.webstore.controllers;

/**
 * Created by lb_lb on 18.08.17.
 */

import net.zarski.dojo.webstore.domain.Cart;
import net.zarski.dojo.webstore.domain.Product;
import net.zarski.dojo.webstore.services.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/carts")
public class CartController {
    @Autowired
    Store store;

    @RequestMapping(value = "", method = RequestMethod.POST, produces="application/json")
    public Cart registerNewCart(@RequestBody String sessionId) {
        //I'm not checking if cart is already existing for simplicity. In real application it's necessary.
        return store.registerNewCart(sessionId);
    }


    @RequestMapping(value = "{session_id}", method = RequestMethod.GET, produces="application/json")
    public Cart getCartDetails(@PathVariable(name = "session_id") String sessionId) {
        return store.findCartBySessionId(sessionId);
    }

    @RequestMapping(value = "{session_id}/products/{product_id}", method = RequestMethod.PUT)
    public void addProductToCart(@PathVariable(name = "session_id") String sessionId,
                                  @PathVariable(name = "product_id") Long productId,
                                  @RequestParam(name = "amount") Integer amount) {
        store.addProductToCart(sessionId, productId, amount);
    }

    @RequestMapping(value = "{session_id}/products/{product_id}", method = RequestMethod.PATCH)
    public void updateProductAmountInCart(@PathVariable(name = "session_id") String sessionId,
                                 @PathVariable(name = "product_id") Long productId,
                                 @RequestParam(name = "amount") Integer newAmount) {
        store.removeProductFromCart(sessionId, productId);
        store.addProductToCart(sessionId, productId, newAmount);
    }

    @RequestMapping(value = "{session_id}/products/{product_id}", method = RequestMethod.DELETE)
    public void removeProductFromCart(@PathVariable(name = "session_id") String sessionId,
                                  @PathVariable(name = "product_id") Long productId) {
        store.removeProductFromCart(sessionId, productId);
    }



}
