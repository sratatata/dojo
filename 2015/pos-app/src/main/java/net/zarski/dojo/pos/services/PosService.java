package net.zarski.dojo.pos.services;

import net.zarski.dojo.pos.domain.Product;
import net.zarski.dojo.pos.domain.Recipe;
import net.zarski.dojo.pos.output.Display;
import net.zarski.dojo.pos.output.Printer;

/**
 * Created by lb_lb on 24.11.14.
 */
public class PosService {
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found";
    private static final String INVALID_BAR_CODE_MESSAGE = "Invalid bar-code";
    final private Display display;
    final private Printer printer;
    final private Recipe recipe;

    public PosService(Display display, Printer printer) {
        this.display = display;
        this.printer = printer;
        this.recipe = new Recipe();
    }

    public void productFound(Product product){
        display.display(product);
        recipe.add(product);
    }

    public void productNotFound(){
        display.display(PRODUCT_NOT_FOUND_MESSAGE);
    }

    public void invalidBarCode(){
        display.display(INVALID_BAR_CODE_MESSAGE);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void printRecipe() {
        printer.printRecipe(recipe);
    }
}
