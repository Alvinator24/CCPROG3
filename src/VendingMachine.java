import java.util.*;

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
        coinBank = new CoinDispenser(denom);
        
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
        coinBank = new CoinDispenser(denom);

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
    
    // if restockAmount is negative, it means remove
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

    public void restockCash(ArrayList<Denomination> cash) {
        
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

    public void displayTransaction() {
        double totalPrice = 0;
        double totalCalories = 0;
        int index = 0;

        for(Item item: currentTransaction.getCartedItems()){
            ++index;
            double itemPrice = item.getPrice();
            double itemCalories = item.getCalories();


            totalPrice += itemPrice;
            totalCalories += itemCalories;
            System.out.println("["+index+ "]" + item.getItemName() + "(" + itemCalories + " cals)");
            System.out.println(itemPrice);
        }

        System.out.println("----------------");
        System.out.println("Total Price: " + totalPrice + " | Total Calories : " + totalCalories);

        System.out.println("Amount inserted: " + currentTransaction.getTotalDispensed());
        if(currentTransaction.getTotalDispensed() >= totalPrice){
            //print change
            coinBank.checkout()

            //check if change is possible
            //if so let user know

        }

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

        if(coinBank.dispenseCoin(currentTransaction, change,1)){
            System.out.println("Thank you for your purchase!");
            System.out.println()
        }
        else{

        }

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

    }

}
