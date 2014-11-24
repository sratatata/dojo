package net.zarski.dojo.pos;

import net.zarski.dojo.pos.domain.Product;
import net.zarski.dojo.pos.output.Display;
import net.zarski.dojo.pos.output.DummyPrinter;
import net.zarski.dojo.pos.output.DummyLcdDisplay;
import net.zarski.dojo.pos.output.Printer;
import net.zarski.dojo.pos.repositories.ProductsRepository;
import net.zarski.dojo.pos.services.PosService;

import java.io.Console;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String EXIT = "exit";
    private static final int SIZE = 32;
    private static final PrintWriter pw = new PrintWriter(System.out);

    private static final  Display display = new DummyLcdDisplay(SIZE, pw);
    private static final  Printer printer = new DummyPrinter();
    private static final  PosService posService = new PosService(display, printer);



    private static PosController pos = new PosController(posService, new ProductsRepository() {
        private final Map<String,Product> products = new HashMap<>();
        {
            products.put("123",new Product("oranges", 100));
            products.put("234",new Product("apples", 3230));
            products.put("345",new Product("kiwi", 1001));
            products.put("456",new Product("melons", 12));
            products.put("567",new Product("papayas", 200));

            System.out.println("Available products:");

            for (String barCode : products.keySet()){
                System.out.printf("%s : %s - %5.2f\n", barCode, products.get(barCode).getName(), products.get(barCode).getPrice() / 100.0f);
            }
        }

        @Override
        public Product findProductWithBarCode(String barCode) {
            return products.get(barCode);
        }
    });


    public static void main( String[] args )
    {




        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        boolean exit = false;
        while(!exit) {
            String input = c.readLine("Enter bar code: ");
            input = input.trim().toLowerCase();

            if(input.equals(EXIT)){
                exit = true;
                pos.exit();
            }else{
                pos.enterBarCode(input);
            }
        }
    }
}
