package net.zarski.dojo.webstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String sessionId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fk_cart")
    private Set<CartPosition> products;

    public Cart(@JsonProperty("sessionId") String sessionId) {
        this.sessionId = sessionId;
    }

    public void addProduct(Product product, int amount) {
        if(this.products == null){
            this.products = new HashSet<>();
        }
        products.add(new CartPosition(product, amount));
    }

    //Dummy constructor for hibernate, accessed by reflection
    private Cart() {
    }

    public Long getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Set<CartPosition> getProducts() {
        return products;
    }

    //Dummy setters for hibernate, accessed by reflection

    private void setId(Long id) {
        this.id = id;
    }

    private void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private void setProducts(Set<CartPosition> products) {
        this.products = products;
    }
}
