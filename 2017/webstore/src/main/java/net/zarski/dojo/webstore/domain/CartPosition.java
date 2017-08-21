package net.zarski.dojo.webstore.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartPosition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Product product;
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cart", nullable = false)
    private Cart cart;

    public CartPosition(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartPosition that = (CartPosition) o;

        if (amount != that.amount) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        return cart != null ? cart.equals(that.cart) : that.cart == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + amount;
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        return result;
    }
}
