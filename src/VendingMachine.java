import java.util.*;
import java.util.Scanner;

public class VendingMachine {

    private String name;
    private int slotLimit;
    private int itemLimit;
    private String password;
    Transaction currentTransaction; //make private

    CoinDispenser coinBank;

    private ArrayList<Slot> itemSlots;
    private ArrayList<Denomination> denom;

    // TODO: MAKE SURE DRIVER CODE DOES NOT PASS LESS THAN MINIMUM NUMBER OF SLOTS
    // AND ITEMS
    VendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms) {
        this.name = name;
        this.slotLimit = slotLimit;
        this.itemLimit = itemLimit;
        password = "admin";
        itemSlots = new ArrayList<Slot>();
        coinBank = new CoinDispenser(denoms);
        
        denom = denoms;

        
        // TODO: initialize denomm

        newTransaction();
    }

    VendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms, String password) {
        this.name = name;
        this.slotLimit = slotLimit;
        this.itemLimit = itemLimit;
        this.password = password;
        itemSlots = new ArrayList<Slot>();
        coinBank = new CoinDispenser(denoms);

        denom = new ArrayList<Denomination>();

        newTransaction();
    }

    public boolean addItem(Item item) { 
        boolean isSuccessul = false;
        Slot found = getSlot(item);

       if(found == null){
           if(itemSlots.size() < slotLimit){
               Slot slot = new Slot(item, itemLimit);
               itemSlots.add(slot);
               isSuccessul = true;

           }
       }
       else{
           System.out.println("Slot for item already exists");
       }


        return isSuccessul;
    }

    // make it static (list of slots, since irl u dont change it once it's made)
    // get the index from the user (n - 1, since it starts from 0)
    
    public boolean restockItem(Item item) {
        boolean isSuccessful =  false;
        Slot found = getSlot(item);
        if(found != null){
            if(found.getQuantity() < itemLimit){
                found.addItem(item);
            }
         isSuccessful = true;
        }
        else{
            System.out.println("Item isn't registered into vending machine");
        }
        return isSuccessful;
    }

    public void restockCash(double cash) {
        coinBank.dispenseCoin(cash);
    }

    public boolean removeItem(int slotNum) { //change to private (make maintenance method w password)
        boolean isSuccesful = false;
        --slotNum;
        Slot slot = itemSlots.get(slotNum);
        if(slot.getQuantity() == 0){
            System.out.println("Slot already empty");

        }
        else{
            slot.removeItem();
            isSuccesful = true;
        }
        return  isSuccesful;
    }

    public Slot getSlot(Item item) {
        Slot slot = null;
        for (Slot i: itemSlots) {
            if(i.getItem().getItemName().equals(item.getItemName()) && i.getItem().getPrice() == item.getPrice() && i.getItem().getCalories() == item.getCalories()) {
                slot = i;
            }
        }
        return slot;
    }



    public boolean purchaseItem(int slotNum) {
        boolean success = false;
        if(slotNum < itemSlots.size()) {

            Item item = itemSlots.get(slotNum-1).getItem();
            int itemsWanted = currentTransaction.getItemQuantity(item) + 1;
            if(getAvailability(item, itemsWanted)){
                currentTransaction.addItem(item);
                success = true;
            }
        }
        return success;

    }


    public boolean dispenseCoin(double valueInserted){
        return currentTransaction.dispenseCoin(valueInserted);

    }

    public boolean displayTransaction() {
        double change = 0;
        boolean readyForCheckout = false;
        int index = 0;

        HashMap<Denomination, Integer> projectedChange = new HashMap<Denomination, Integer>();
        for(Denomination s : denom){
            projectedChange.put(s, 0);
        }


        for(Item item: currentTransaction.getCartedItems()){
            ++index;
            double itemPrice = item.getPrice();
            double itemCalories = item.getCalories();


            System.out.println("["+index+ "]" + item.getItemName() + "(" + itemCalories + " cals)");
            System.out.println(itemPrice);
        }

        System.out.println("----------------");
        System.out.println("Total Price: " + currentTransaction.getTotalPrice() + " | Total Calories : " + currentTransaction.getTotalCalories());

        System.out.println("Amount inserted: " + currentTransaction.getTotalDispensed());
        if(currentTransaction.getTotalDispensed() >= currentTransaction.getTotalPrice()) {
            //print change
            if (coinBank.checkout(currentTransaction, projectedChange, false)) ;
            {
                readyForCheckout = true;
                for (Denomination s : denom) {
                    change += projectedChange.get(s) * s.getValue();
                }

                System.out.println("Change: " + change);

            }
        }
        return readyForCheckout;
    }

    public boolean haveChange(){ //FINISH
        boolean enoughChange = false;


        return enoughChange;
    }


    public void newTransaction(){
        currentTransaction = new Transaction(denom);
    }

    public void addCash(int test) {

    }

    public void checkout(boolean followThrough){
        HashMap<Denomination, Integer> change = new HashMap<Denomination, Integer>();
        for(Denomination s : denom){
            change.put(s, 0);
        }

        if(followThrough){
            if(coinBank.checkout(currentTransaction, change,true)){

                System.out.println("\n\nThank you for your purchase!");
                System.out.println("===================");
                System.out.println("Summary of purchase");
                for(Item item : currentTransaction.getCartedItems()){
                    System.out.println("Item: " + item.getItemName());
                    System.out.println("Price: " + item.getPrice());
                    System.out.println("Calories: " + item.getCalories());
                    System.out.println("-------------------");
                }
                System.out.println("===================");
                System.out.println("Total Price: " + currentTransaction.getTotalPrice() + " | Total Calories: " + currentTransaction.getTotalCalories());

                System.out.println("\n\n*Change given*");
                for(Denomination s : denom){
                    System.out.println(s.getValue() + " : " + change.get(s));
                }
            }
            else{
                System.out.println("Error dispensing change | maybe called when displayTransaction() returned false");
            }
        }
        System.out.println("Thank you!\n");
        newTransaction();

    }

    public void displayItems() {
        int slotNum = 0;
        for(Slot s : itemSlots){
            ++slotNum;
            String name = s.getItem().getItemName();
            double price = s.getItem().getPrice();
            double calories = s.getItem().getCalories();

            System.out.println("------------------");
            System.out.println("Slot " + slotNum);
            System.out.println("Item: " + name);
            System.out.println("Price: " + price);
            System.out.println("Calories: " + calories);
            System.out.println("Available: |" + s.getQuantity() + "|");
        }
    }

    public boolean getAvailability(Item item, int numberOfOrders) {
        boolean available = false;
        if(getSlot(item).getQuantity() >= numberOfOrders) {
            available = true;
        }
        return available;
    }

    public void maintenance(String password){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object


        if(password.equals(this.password)){
            System.out.println("[1] Restock\n [2] Print Summary\n [3] Refill Denominations\n [4] Withdraw Cash");
            int choice = myObj.nextInt();

            switch(choice){
                case 1: {

                    System.out.println("Enter item name: ");
                    String myObj2 =  myObj.nextLine();

                    System.out.println("Enter price: ");
                    double myObj3 =  myObj.nextDouble();

                    System.out.println("Enter Calories: ");
                    double myObj4 =  myObj.nextDouble();

                    restockItem(new Item(myObj2, myObj3, myObj4));
                    break;
                }

                case 2: {


                    break;
                }

                case 3: {
                    System.out.println("Enter Denomination Value: ");
                    double myObj5 =  myObj.nextDouble();

                    dispenseCoin(myObj5);

                    break;
                }

                case 4: {

                    break;
                }
            }
        }
        else{
            System.out.println("Invalid Password");
        }
    }

}
