package net.zarski.dojo.pos.output;

import net.zarski.dojo.pos.domain.Recipe;

/**
 * Created by lb_lb on 24.11.14.
 */
public interface Printer {
    void printRecipe(Recipe recipe);
}
