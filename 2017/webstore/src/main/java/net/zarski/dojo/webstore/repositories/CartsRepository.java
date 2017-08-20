package net.zarski.dojo.webstore.repositories;

import net.zarski.dojo.webstore.domain.Cart;
import net.zarski.dojo.webstore.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface CartsRepository extends CrudRepository<Cart, Long> {
}
