package net.zarski.dojo.webstore.repositories;

import net.zarski.dojo.webstore.domain.Product;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by lb_lb on 18.08.17.
 */

public interface ProductsRepository extends Repository<Product, Long> {

    List<Product> findAll();
}
