// import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {


    
    // we only need to store the data (vending machines), so ArrayList is optimal
    // if we needed to manipulate the data, LinkedLists are better (internally doubly linked lists)

    public static void main(String[] args) {
        ArrayList<Denomination> denoms = new ArrayList<Denomination>();
        denoms.add(new Denomination(.01));
        denoms.add(new Denomination(.05));
        denoms.add(new Denomination(.1));
        denoms.add(new Denomination(.25));
        denoms.add(new Denomination(1));
        denoms.add(new Denomination(5));
        denoms.add(new Denomination(10));
        denoms.add(new Denomination(20));
        denoms.add(new Denomination(50));
        denoms.add(new Denomination(100));
        denoms.add(new Denomination(200));
        denoms.add(new Denomination(500));
        denoms.add(new Denomination(1000));


        VendingMachine test = new VendingMachine("test", 10,10, denoms );

        Item iceTea = new Item("IceTea", 65, 300);
        Item orangeJuice = new Item("Orangey", 165, 3300);
        Item appleJuice = new Item("Appley", 101, 250);
        Item iceTea2 = new Item("IceTea", 65, 300);

        test.addItem(iceTea);
        test.addItem(orangeJuice);
        test.addItem(appleJuice);
        test.addItem(iceTea2);

        test.purchaseItem(1);
        //System.out.println(test.currentTransaction.cartedItems.get(0).getItemName());
        //System.out.println(test.getSlot(iceTea2).getItem().getItemName());
    }

    // MCO2 (Pizza Ingredients)
    // Thin or thick crust
    // Sauce
    // Toppings
    // cannot buy alone: dough, sauce

}