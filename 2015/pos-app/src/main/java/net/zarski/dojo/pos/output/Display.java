package net.zarski.dojo.pos.output;

import net.zarski.dojo.pos.domain.Product;

/**
 * Created by lb_lb on 24.11.14.
 */
public interface Display {
    void display(Product product);

    void display(String message);
}
