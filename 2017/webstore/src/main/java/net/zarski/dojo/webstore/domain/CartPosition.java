package net.zarski.dojo.webstore.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartPosition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_product", nullable = false)
    private Product product;

//    @ManyToOne
//    @JoinColumn(name = "fk_cart", nullable = false)
//    private Cart cart;

    @Column(name = "amount")
    private int amount;

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

//    public Cart getCart() {
//        return cart;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartPosition that = (CartPosition) o;

        if (amount != that.amount) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }

    //Dummy constructor for hibernate, accessed by reflection
    private CartPosition() {
    }

    //Dummy setter for hibernate, accessed by reflection
    private void setId(Long id) {
        this.id = id;
    }

    //Dummy setter for hibernate, accessed by reflection
    private void setProduct(Product product) {
        this.product = product;
    }

    //Dummy setter for hibernate, accessed by reflection
    private void setAmount(int amount) {
        this.amount = amount;
    }

//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
}
