package net.zarski.dojo.pos

import net.zarski.dojo.pos.domain.Product
import net.zarski.dojo.pos.domain.Recipe
import spock.lang.Specification

/**
 * Created by lb_lb on 24.11.14.
 */
class RecipeSpec extends Specification{

    def "when 2 products are added to recipe then recipe size is 2" () {
        given:
        Recipe recipe = new Recipe()

        when:
        recipe.add(new Product("apples", 100))
        recipe.add(new Product("oranges", 200))

        then:
        recipe.size() == 2
    }
}
