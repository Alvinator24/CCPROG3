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

    private HashMap<Item, Integer> inventoryCount;

    // AND ITEMS
    VendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms) {
        this.name = name;
        this.slotLimit = slotLimit;
        this.itemLimit = itemLimit;
        password = "admin";
        itemSlots = new ArrayList<Slot>();
        coinBank = new CoinDispenser(denoms);
        inventoryCount = new HashMap<Item, Integer>();

        denom = denoms;
        newTransaction();
    }

    VendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms, String password) {
        this.name = name;
        this.slotLimit = slotLimit;
        this.itemLimit = itemLimit;
        this.password = password;
        itemSlots = new ArrayList<Slot>();
        coinBank = new CoinDispenser(denoms);
        inventoryCount = new HashMap<Item, Integer>();

        denom = new ArrayList<Denomination>();

        newTransaction();
    }

    public boolean addSlot(Item item, double price) {  //change to slot number
        boolean isSuccessful = false;
        if (itemSlots.size() < slotLimit) {
            itemSlots.add(new Slot(item, itemLimit, price));
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


    public boolean checkout(boolean followThrough, Transaction receipt) {
        boolean possible = false;
        HashMap<Denomination, Integer> change = coinBank.simulateCheckout(currentTransaction.getCoinCollection(), null, currentTransaction);

        if (change != null) {
            possible = true;
        }
        if (followThrough && possible) {
            currentTransaction.setReceipt(coinBank.checkout(currentTransaction));
            receipt = currentTransaction;
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
