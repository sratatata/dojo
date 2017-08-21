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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private Set<CartPosition> products;

    public Cart(@JsonProperty("sessionId") String sessionId) {
        this.sessionId = sessionId;
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

    public void addProduct(Product product, int amount) {
        if(this.products == null){
            this.products = new HashSet<>();
        }
        products.add(new CartPosition(product, amount));
    }
}
