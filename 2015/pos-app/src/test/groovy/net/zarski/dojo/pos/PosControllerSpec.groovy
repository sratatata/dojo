package net.zarski.dojo.pos

import net.zarski.dojo.pos.domain.Product
import net.zarski.dojo.pos.repositories.ProductsRepository
import net.zarski.dojo.pos.services.PosService
import spock.lang.Specification

/**
 * Created by lb_lb on 24.11.14.
 */
class PosControllerSpec extends Specification{

    final PosService outputService= Mock()
    final ProductsRepository productsRepository = Mock()
    final PosController posController = new PosController(outputService, productsRepository);


    def setup() {
        productsRepository.findProductWithBarCode("123") >> new Product("apples", 1233)
        productsRepository.findProductWithBarCode("124") >> new Product("oranges", 13)
    }

    def "If bar-code is recognized as valid product then sends message to pos service" (){
        given:
        String barCode = "123"

        when:
        posController.enterBarCode(barCode)

        then:
        1 * outputService.productFound(_)

    }

    def "bar-code \"123\" is recognized then it's matches product \"apples\"" (){
        given:
        def barCode = "123"
        def product = new Product("apples", 1233)

        when:
        posController.enterBarCode(barCode)

        then:
        1 * outputService.productFound(product)
    }

    def "bar-code \"124\" is recognized then it's matches product \"oranges\"" (){
        given:
        def barCode = "124"
        def product = new Product("oranges", 13)

        when:
        posController.enterBarCode(barCode)

        then:
        1 * outputService.productFound(product)
    }

    def "bar-code \"125\" is not recognized then it's calls product not found routine" (){
        given:
        def barCode = "125"

        when:
        posController.enterBarCode(barCode)

        then:
        1 * outputService.productNotFound()
    }

    def "bar-code is empty then it's calls invalid bar-code routine" (){
        given:
        def barCode = ""

        when:
        posController.enterBarCode(barCode)

        then:
        1 * outputService.invalidBarCode()
    }

    def "bar-code is searched in products repository" (){
        given:
        def barCode = "123"

        when:
        posController.enterBarCode(barCode)

        then:
        1 * productsRepository.findProductWithBarCode(barCode)
    }

}
