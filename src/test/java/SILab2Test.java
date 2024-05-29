import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    SILab2 siLab2 = new SILab2();

    @Test
    public void everyBranchTest(){
        RuntimeException ex =  assertThrows(RuntimeException.class,()->siLab2.checkCart(null,1));
        assertTrue(ex.getMessage().contains("can't be null!"));

        List<Item> items1 = new ArrayList<>();
        items1.add(new Item("",null,30,0));
        ex =  assertThrows(RuntimeException.class,()->siLab2.checkCart(items1,1));
        assertTrue(ex.getMessage().contains("No barcode!"));

        List<Item> items2 = new ArrayList<>();
        items2.add(new Item("valid","1lol",300,0));
        ex =  assertThrows(RuntimeException.class,()->siLab2.checkCart(items2,301));
        assertTrue(ex.getMessage().contains("Invalid character"));

        List<Item> items3 = new ArrayList<>();
        items3.add(new Item("valid","023",100,0.1f));
        items3.add(new Item("valid","123",100,0));
        assertTrue(siLab2.checkCart(items3, 301));


        List<Item> items4 = new ArrayList<>();
        items4.add(new Item("valid","023",100,0.1f));
        items4.add(new Item("valid","123",100,0));
        assertFalse(siLab2.checkCart(items4,100));
    }

    @Test
    public void multipleConditionsTest(){
        List<Item> items4 = new ArrayList<>();
        items4.add(new Item("valid","023",200,0.1f));
        items4.add(new Item("valid","123",301,0));
        items4.add(new Item("valid","123",301,0.1f));
        items4.add(new Item("valid","023",301,0.1f));

        assertTrue(siLab2.checkCart(items4,1000));
        assertFalse(siLab2.checkCart(items4,100));
    }

}