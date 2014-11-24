package net.zarski.dojo.pos

import net.zarski.dojo.pos.domain.Product
import net.zarski.dojo.pos.domain.Recipe
import net.zarski.dojo.pos.output.Display
import net.zarski.dojo.pos.output.Printer
import net.zarski.dojo.pos.services.PosService
import spock.lang.Specification

/**
 * Created by lb_lb on 24.11.14.
 */
class PosServiceSpec extends Specification{
    final private Display display = Mock()
    final private Printer printer = Mock()
    final private PosService posService = new PosService(display, printer)

    def "when product is found, it's displayed on lcd display" (){
        given:
        Product product = new Product("apples", 123)
        when:
        posService.productFound(product)

        then:
        1 * display.display(product)
    }

    def "when 2 products are found their are added to recipe and recipe size is 2" (){
        given:
        Product product1 = new Product("apples", 100)
        Product product2 = new Product("oranges", 200)


        when:
        posService.productFound(product1)
        posService.productFound(product2)

        then:
        posService.getRecipe().size() == 2
    }

    def "when product is not in database, display error message" (){
        when:
        posService.productNotFound()

        then:
        1 * display.display("Product not found")
    }

    def "when barCode is empty, then display error message" (){
        when:
        posService.invalidBarCode()

        then:
        1 * display.display("Invalid bar-code")
    }

    def "if print recipe is called then print recipe with printer" (){
        given:
        posService.productFound(new Product("apples", 100))
        posService.productFound(new Product("oranges", 200))

        when:
        posService.printRecipe()

        then:
        1 * printer.printRecipe(_)
    }

}
