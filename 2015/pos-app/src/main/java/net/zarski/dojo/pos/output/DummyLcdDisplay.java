package net.zarski.dojo.pos.output;

import net.zarski.dojo.pos.domain.Product;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by lb_lb on 24.11.14.
 */
public class DummyLcdDisplay implements Display{
    private static final int DEFAULT_SIZE = 32;

    private int size;
    private Writer writer;

    public DummyLcdDisplay(int size, Writer writer) {
        this.size = size;
        this.writer = writer;

    }

    public DummyLcdDisplay(Writer writer) {
        this(DEFAULT_SIZE, writer);
    }

    @Override
    public void display(Product product) {
        String format = calculateFormat();
        String content = String.format(format, product.getName(), product.getPrice()/100.0f);
        try {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void display(String message) {
        String format = "%32.32s\n";
        String content = String.format(format, message);
        try {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String calculateFormat() {
        int slot = size /2;
        int slot2 = size - slot;
        return "%-"+slot+"s%"+slot2+".2f\n";
    }
}
