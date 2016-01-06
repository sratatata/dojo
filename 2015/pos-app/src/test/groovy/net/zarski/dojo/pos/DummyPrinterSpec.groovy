package net.zarski.dojo.pos

import net.zarski.dojo.pos.domain.Product
import net.zarski.dojo.pos.domain.Recipe
import net.zarski.dojo.pos.output.DummyPrinter
import net.zarski.dojo.pos.output.Printer
import spock.lang.Specification

/**
 * Created by lb_lb on 24.11.14.
 */
class DummyPrinterSpec extends Specification{
    final private Printer printer = new DummyPrinter();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    def setup() {
        System.setOut(new PrintStream(outContent));
    }

    def cleanup() {
        System.setOut(null);
    }

    def "Prints well formatted recipe" (){
        given:
        Recipe recipe = new Recipe();
        recipe.add(new Product("apples", 100))
        recipe.add(new Product("oranges", 200))

        when:
        printer.printRecipe(recipe)

        then:
        outContent.toString() == "-------------------------\n" +
                                 "----------Recipe---------\n" +
                                 "-------------------------\n" +
                                 "    apples           1.00\n" +
                                 "   oranges           2.00\n" +
                                 "-------------------------\n" +
                                 "Total:               3.00\n" +
                                 "-------------------------\n"
    }
}
