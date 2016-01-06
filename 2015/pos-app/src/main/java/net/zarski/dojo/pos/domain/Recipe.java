package net.zarski.dojo.pos.domain;

import java.util.*;

/**
 * Created by lb_lb on 24.11.14.
 */
public class Recipe{
    private List<Product> products;

    public Recipe() {
        products = new ArrayList<>();
    }

    public int size() {
        return products.size();
    }

    public void add(Product product){
        products.add(product);
    }

    public int getTotal(){
        int total= 0;
        for(Product product : products){
            total += product.getPrice();
        }
        return total;
    }

    public int getTotalWithFilter(ProductsFilter productsFilter){
        int total= 0;
        for(Product product : products){
            total += productsFilter.filter(product).getPrice();


        }
        return total;
    }
}
