import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;

public class VendingMachine {

    private String name;
    protected int slotLimit;
    private int itemLimit;
    Transaction currentTransaction; //make private

    CoinDispenser coinBank;

    protected ArrayList<Slot> itemSlots;
    private ArrayList<Denomination> denom;

    private HashMap<Item, Integer> inventoryCount;

    // AND ITEMS
    VendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms) {
        this.name = name;
        this.slotLimit = slotLimit;
        this.itemLimit = itemLimit;
        itemSlots = new ArrayList<Slot>();
        coinBank = new CoinDispenser(denoms);
        inventoryCount = new HashMap<Item, Integer>();

        denom = denoms;
        newTransaction();
    }

    public boolean addSlot(Item item, double price, int itemType ,ArrayList<Message> messages) {  //change to slot number
        boolean isSuccessful = false;
        if (itemSlots.size() < slotLimit) {
            itemSlots.add(new Slot(item, itemLimit, price, itemType, messages));
            isSuccessful = true;
        }
        return isSuccessful;
    }

    public boolean removeSlot(int index) {
        boolean isSuccessful = false;
        if (index >= 0 && index < itemSlots.size()) {
            itemSlots.remove(index);
            isSuccessful = true;
        }
        return isSuccessful;
    }

    public boolean restockItem(HashMap<Integer, Integer> adjustments, HashMap<Item, Integer> previousStock) { //change to slot number
        boolean allGood = true;

        previousStock = inventoryCount;
        updateInventoryCount();
        for (Slot slot : itemSlots) {
            for (int i = 0; i < Math.abs(adjustments.get(i)); ++i) {
                if (adjustments.get(i) > 0) {
                    if (slot.addItem() == false) {
                        allGood = false;
                    }
                } else {
                    if (slot.removeItem() == false) {
                        allGood = false;
                    }
                }
            }
        }

        return allGood;
    }

    private void updateInventoryCount() {
        HashMap<Item, Integer> newCount = new HashMap<Item, Integer>();
        for (Slot slot : itemSlots) {
            Item item = slot.getItem();
            item = new Item(item.getItemName(), item.getCalories());
            newCount.put(item, slot.getQuantity());
        }
        inventoryCount = newCount;
    }


    public void restockCash(HashMap<Denomination, Integer> coinPouch) {
        coinBank.dispenseCoin(coinPouch);
    }

    public HashMap<Denomination, Integer> emptyCoins() {
        return coinBank.emptyMachine();
    } //


    //BELOW ARE FOR ITEM PURCHASING
    public boolean purchaseItem(int slotNum) {
        return currentTransaction.addItem(slotNum - 1);
    }

    public boolean removeItem(int slotNum) {
        return currentTransaction.removeItem(slotNum);
    }


    public boolean dispenseCoin(double valueInserted) {
        return currentTransaction.dispenseCoin(valueInserted);
    }


    //add messages. Arraylist strings?
    public boolean checkout(boolean followThrough, Transaction receipt, ArrayList<String> outputMessage) {
        boolean possible = false;
        HashMap<Denomination, Integer> change = coinBank.simulateCheckout(currentTransaction.getCoinCollection(), null, currentTransaction);

        if (change != null) {
            possible = true;
        }
        if (followThrough && possible) {
            currentTransaction.setReceipt(coinBank.checkout(currentTransaction));
            outputMessage = currentTransaction.getMessages();
            receipt = currentTransaction;
            newTransaction();
        } else if (followThrough && !possible) {
            outputMessage.add("Machine does not have the appropriate change for this order");
        }


        return possible;
    }


    public String getSlotName(int index) {
        Item item = itemSlots.get(index).getItem();
        return item.getItemName();
    } //good

    public double getSlotCalories(int index) {
        Item item = itemSlots.get(index).getItem();
        return item.getCalories();
    } //good

    public double getSlotPrice(int index) {
        return itemSlots.get(index).getPrice();
    } //good


    private void newTransaction() {
        currentTransaction = new Transaction(denom, itemSlots);
    }
}
