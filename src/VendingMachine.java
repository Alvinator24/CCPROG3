import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;

public class VendingMachine {

    private String name;
    protected int slotLimit;
    private int itemLimit;
    protected Transaction currentTransaction; //make private

     CoinDispenser coinBank;

    ArrayList<Slot> itemSlots; //make private
    private ArrayList<Denomination> denom;

     HashMap<Integer, Integer> inventoryCount;

    // AND ITEMS
    VendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms) {
        this.name = name;
        this.slotLimit = slotLimit;
        this.itemLimit = itemLimit;
        itemSlots = new ArrayList<Slot>();
        coinBank = new CoinDispenser(denoms);
        inventoryCount = new HashMap<Integer, Integer>();

        denom = denoms;
        newTransaction();
        updateInventoryCount();
    }

    public boolean addSlot(Item item, double price, int itemType ,ArrayList<Message> messages) {  //change to slot number
        boolean isSuccessful = false;
        if (itemSlots.size() < slotLimit) {
            itemSlots.add(new Slot(item, itemLimit, price, itemType, messages));
            isSuccessful = true;
        }
        newTransaction();;
        return isSuccessful;
    }

    public boolean removeSlot(int index) {
        boolean isSuccessful = false;
        if (index >= 0 && index < itemSlots.size()) {
            itemSlots.remove(index);
            isSuccessful = true;
        }
        newTransaction();
        return isSuccessful;
    }

    public boolean restockItem(HashMap<Integer, Integer> adjustments, HashMap<Integer, Integer> previousStock) { //change to slot number
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
        newTransaction();

        return allGood;
    }

    //make private
    public void updateInventoryCount() {
        HashMap<Integer, Integer> newCount = new HashMap<Integer, Integer>();
        for(int i = 0; i < itemSlots.size(); ++i){
            newCount.put(i, itemSlots.get(i).getQuantity());
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
        return currentTransaction.addItem(slotNum);
    }

    public boolean removeItem(int slotNum) {
        return currentTransaction.removeItem(slotNum);
    }


    public boolean dispenseCoin(double valueInserted) {
        return currentTransaction.dispenseCoin(valueInserted);
    }


    //add messages. Arraylist strings?
    public boolean checkout(boolean followThrough, Transaction receipt, ArrayList<String> outputMessage, HashMap<Denomination, Integer> change) {
        boolean possible = false;
        HashMap<Denomination,Integer> tempHash = coinBank.simulateCheckout(currentTransaction.getCoinCollection(), null, currentTransaction);
        if(tempHash != null){
            for(Denomination denoms: denom){
                change.put(denoms, tempHash.get(denoms));
            }
        }



        if (tempHash != null) {
            possible = true;
        }
        if (followThrough && possible) {
            coinBank.checkout(currentTransaction);
            outputMessage.addAll(currentTransaction.getMessages());
            for(String str: outputMessage){
                System.out.println(str);
            }
            currentTransaction.transferDetails(receipt);

            int bought;
            for(int i = 0; i < getItemSlots().size(); ++i){
                bought = receipt.getCartedItems().get(i);
                for(int j = 0 ; j < bought; ++j){
                    itemSlots.get(i).removeItem();
                }
            }



            newTransaction();
        } else if (followThrough && !possible) {
            outputMessage.add("Machine does not have the appropriate change for this order");
        }
        System.out.println(String.valueOf(possible));


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


    public void newTransaction() {
        currentTransaction = new Transaction(denom, itemSlots);
    }

    public ArrayList<Slot> getItemSlots() {
        return itemSlots;
    }

    public String getName() {
        return name;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
}
