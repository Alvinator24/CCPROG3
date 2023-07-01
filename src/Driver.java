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

        System.out.println("1. Create RVM");
        System.out.println("2. Create SVM");
        System.out.println("3. Exit");
        
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();


        while(choice != 3) {
            if(choice == 1) {
                VendingMachine test = new VendingMachine("test", 10,10, denoms );

                Item iceTea = new Item("IceTea", 65, 300);
                Item orangeJuice = new Item("Orangey", 165, 3300);
                Item appleJuice = new Item("Appley", 101, 250);
                Item iceTea2 = new Item("IceTea", 65, 300);

                test.restockItem(new Item("IceTea", 65, 300));
                test.restockItem(new Item("IceTea", 65, 300));
                test.restockItem(new Item("IceTea", 65, 300));
                test.restockItem(new Item("IceTea", 65, 300));
                test.restockItem(new Item("IceTea", 65, 300));
                test.restockItem(new Item("IceTea", 65, 300));

                test.restockItem(new Item("Orangey", 165, 3300));
                test.restockItem(new Item("Appley", 101, 250));


                test.purchaseItem(1);
                test.purchaseItem(1);
                test.purchaseItem(2);

                test.currentTransaction.removeItem(3);

                test.dispenseCoin(20);
                test.dispenseCoin(1);
                test.dispenseCoin(.25);
            }

            sc.close();

        }

        
        //test.currentTransaction.displayTransaction();
        

        //test.displayItems();
        //System.out.println(test.currentTransaction.cartedItems.get(0).getItemName());
        //System.out.println(test.getSlot(iceTea2).getItem().getItemName());
    }

    // MCO2 (Pizza Ingredients)
    // Thin or thick crust
    // Sauce
    // Toppings
    // cannot buy alone: dough, sauce

}