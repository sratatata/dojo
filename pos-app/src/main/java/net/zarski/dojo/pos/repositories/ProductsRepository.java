package net.zarski.dojo.pos.repositories;

import net.zarski.dojo.pos.domain.Product;

/**
 * Created by lb_lb on 24.11.14.
 */
public interface ProductsRepository {
     Product findProductWithBarCode(String barCode);
}
