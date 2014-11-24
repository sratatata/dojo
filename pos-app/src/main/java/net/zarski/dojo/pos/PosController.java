package net.zarski.dojo.pos;

import net.zarski.dojo.pos.domain.Product;
import net.zarski.dojo.pos.repositories.ProductsRepository;
import net.zarski.dojo.pos.services.PosService;

/**
 * Created by lb_lb on 24.11.14.
 */
public class PosController {
    final private PosService posService;
    final private ProductsRepository productsRepository;

    public PosController(PosService posService, ProductsRepository productsRepository) {
        this.posService = posService;
        this.productsRepository = productsRepository;
    }

    public void enterBarCode(String barCode){
        if(barCode.isEmpty()){
            posService.invalidBarCode();
        }else {
            Product product = searchForProduct(barCode);

        }
    }

    public void exit(){
        posService.printRecipe();
    }

    public Product searchForProduct(String barCode){
        Product product = productsRepository.findProductWithBarCode(barCode);
        if (null == product) {
            posService.productNotFound();
            return null;
        }
        posService.productFound(product);
        return product;
    }


}
