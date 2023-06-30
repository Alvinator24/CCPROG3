import java.util.*;

public class VendingMachine {

    private String name;
    private int slotLimit;
    private int itemLimit;
    private double totalAmount; // hashmap instead ?

    private Transaction currentTransaction; //

    private ArrayList<Slot> itemSlots;
    private ArrayList<Denomination> denom;

    // TODO: MAKE SURE DRIVER CODE DOES NOT PASS LESS THAN MINIMUM NUMBER OF SLOTS
    // AND ITEMS
    VendingMachine(String name, int slotLimit, int itemLimit) {
        this.name = name;
        this.slotLimit = slotLimit;
        this.itemLimit = itemLimit;
        itemSlots = new ArrayList<Slot>();
        
        denom = new ArrayList<Denomination>();

        
        // TODO: initialize denomm

        newTransaction();
    }

    public boolean addItem(Item item) { 
        boolean isSuccessul = false; 

       if(itemSlots.size() < slotLimit){
            Slot slot = new Slot(item, itemLimit);
            itemSlots.add(slot);
            isSuccessul = true;
       }

        return isSuccessul;
    }

    // make it static (list of slots, since irl u dont change it once it's made)
    // get the index from the user (n - 1, since it starts from 0)
    
    // if restockAmount is negative, it means remove
    public boolean restockItem(Item item) {
        boolean isSuccessful =  false;
        getSlot(item).addItem(item);
        return isSuccessful;
    }

    public void restockCash() {
        
    }

    public void removeItem(Item item) {
        getSlot(item).removeItem();
    }

    public Slot getSlot(Item item) {
        for (Slot i: itemSlots) {
            if(i.getItem().getItemName() == item.getItemName() && i.getItem().getPrice() == item.getPrice() && i.getItem().getCalories() == item.getCalories()) {
                return i;
            }
        }
    }

    }

    public int purchaseItem(int slotNum) {
        if( < ) {
           return; 
        }
    }

    public boolean getTransactionStatus() {
        return currentTransaction.isEmpty;
    }

    public void newTransaction(){
        currentTransaction = new Transaction();
    }

    public void addCash(int test) {

    }

    public void checkout(boolean followThrough){

    }

    public void displayItems() {
        // return 2d array, or hashmap (name: key, price: value)
    }

}
