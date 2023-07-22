// import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Driver {


    
    // we only need to store the data (vending machines), so ArrayList is optimal
    // if we needed to manipulate the data, LinkedLists are better (internally doubly linked lists)

    public static void main(String[] args) {
        //pre-defined objects (to make demo easy to explain)
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


        VendingMachine testVend = new VendingMachine("fruit pizza", 8, 10, denoms);
        Item apple = new Item("apple", 100);
        Item orange = new Item("orange", 500);
        Item pineapple = new Item("pineapple", 600);

        Message message1 = new Message("Cutting apple", 0);
        Message message2 = new Message("Sprinkling apple", 3);

        ArrayList<Message> appleM = new ArrayList<>(List.of(message1, message2));

        Message message3 = new Message("Cutting orange", 0);
        Message message4 = new Message("Sprinkling orange", 3);

        ArrayList<Message> orangeM = new ArrayList<>(List.of(message3, message4));

        Message message5 = new Message("Cutting pineapple", 0);
        Message message6 = new Message("Sprinkling pineapple", 3);

        ArrayList<Message> pineappleM = new ArrayList<>(List.of(message5, message6));

        testVend.addSlot(apple, 30, 0, appleM);
        testVend.addSlot(orange, 70, 1, orangeM);
        testVend.addSlot(pineapple, 100, 3, pineappleM);



        testVend.updateInventoryCount();
        HashMap<Integer, Integer> itemsSoFar = testVend.inventoryCount;

        for(int i = 0; i < 3; ++i){
            System.out.println(i + " - " + itemsSoFar.get(i));
            System.out.println(testVend.getItemSlots().get(i).getItem().getItemName());
        }


        testVend.purchaseItem(0);
        testVend.purchaseItem(1);
        testVend.purchaseItem(2);
        testVend.purchaseItem(0);
        testVend.purchaseItem(0);
        testVend.removeItem(0);
        testVend.removeItem(0);
        testVend.removeItem(0);

        testVend.dispenseCoin(500);
        testVend.dispenseCoin(10000);

        System.out.println(testVend.currentTransaction.getTotalDispensed() + " " + testVend.currentTransaction.getTotalCalories() + " " + testVend.currentTransaction.getTotalPrice());

        HashMap<Integer, Integer> purchased = testVend.currentTransaction.getCartedItems();

        for(int i = 0; i < 3; ++i){
            System.out.println(i + " - " + purchased.get(i));
        }





    }



    // MCO2 (Pizza Ingredients)
    // Thin or thick crust
    // Sauce
    // Toppings
    // cannot buy alone: dough, sauce

}