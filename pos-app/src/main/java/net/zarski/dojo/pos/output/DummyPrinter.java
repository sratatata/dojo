package net.zarski.dojo.pos.output;

import net.zarski.dojo.pos.domain.Product;
import net.zarski.dojo.pos.domain.ProductsFilter;
import net.zarski.dojo.pos.domain.Recipe;

import java.io.PrintWriter;

/**
 * Created by lb_lb on 24.11.14.
 */
public class DummyPrinter implements Printer {
    @Override
    public void printRecipe(Recipe recipe) {

        System.out.print("-------------------------\n");
        System.out.print("----------Recipe---------\n");
        System.out.print("-------------------------\n");
        int total = recipe.getTotalWithFilter(new ProductsFilter() {
            @Override
            public Product filter(Product product) {
                System.out.printf("%10s%15.2f\n", product.getName(), product.getPrice()/100.0f);
                return product;
            }
        });

        System.out.print ("-------------------------\n");
        System.out.printf("Total:%19.2f\n",total/100.0f);
        System.out.print ("-------------------------\n");
    }
}
