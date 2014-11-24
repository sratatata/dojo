package net.zarski.dojo.pos

import net.zarski.dojo.pos.domain.Product
import net.zarski.dojo.pos.output.Display
import net.zarski.dojo.pos.output.DummyLcdDisplay
import spock.lang.Specification
import spock.lang.Unroll
@Unroll
class DisplaySpec extends Specification {

    def "display of #productName cost #price on #displaySize should look like #displays"() {
        given:
        StringWriter lcdContent = new StringWriter()
        Display lcd = new DummyLcdDisplay(displaySize, lcdContent)
        Product product = new Product(productName, price)

        expect:
        lcd.display(product)
        lcdContent.toString() == displays

        lcdContent.close()

        where:
        productName   | price  | displaySize || displays
        "apple"       | 126    | 16          || "apple       1.26"
        "oranges"     | 11130  | 16          || "oranges   111.30"
        "kiwi"        | 2222   | 32          || "kiwi                       22.22"



    }

    def "#message is well displayed as #displays" (){
        given:
        StringWriter lcdContent = new StringWriter()
        Display lcd = new DummyLcdDisplay(displaySize, lcdContent)

        expect:
        lcd.display(message)
        lcdContent.toString() == displays

        lcdContent.close()
        where:
        message   | displaySize || displays
        "Product not found"   | 32          || "               Product not found"
        "Invalid bar-code"    | 32          || "                Invalid bar-code"


    }


}