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

    }

    public boolean addSlot(Item item, double price, int itemType ,ArrayList<Message> messages) {  //change to slot number
        boolean isSuccessful = false;
        if (itemSlots.size() < slotLimit) {
            itemSlots.add(new Slot(item, itemLimit, price, itemType, messages));
            isSuccessful = true;
        }
        newTransaction();;

        if(!inventoryCount.containsKey(itemSlots.size() - 1)){
            inventoryCount.put(itemSlots.size()-1, 0);
        }
        refreshInventoryCount();
        return isSuccessful;
    }

    public boolean removeSlot(int index) {
        boolean isSuccessful = false;
        if (index >= 0 && index < itemSlots.size()) {
            itemSlots.remove(index);
            isSuccessful = true;
        }
        newTransaction();
        refreshInventoryCount();
        return isSuccessful;
    }

    public boolean restockItem(HashMap<Integer, Integer> adjustments) { //change to slot number
        boolean allGood = true;

        for(int i = 0; i < itemSlots.size(); ++i){
            if(adjustments.get(i) > 0){
                for(int j = 0; j < adjustments.get(i); ++j){
                    if (itemSlots.get(i).addItem() == false) {
                        allGood = false;
                    }
                }
            }
            else if(adjustments.get(i) < 0){
                for(int j = 0; j < Math.abs(adjustments.get(i)); ++j){
                    if (itemSlots.get(i).removeItem() == false) {
                        allGood = false;
                    }
                }
            }
        }

        newTransaction();
        inventoryCount = new HashMap<Integer, Integer>();
        for(int i = 0; i < itemSlots.size(); ++i){
            inventoryCount.put(i, 0);
        }

        return allGood;
    }

    //make private
    public void updateInventoryCount(HashMap<Integer, Integer> sold) {
        refreshInventoryCount();

        for(int i = 0; i < itemSlots.size(); ++i) {
            inventoryCount.put(i, inventoryCount.get(i) + sold.get(i));
        }
    }

    public void refreshInventoryCount(){
        for(int i = 0; i < itemSlots.size(); ++i){
            if(!inventoryCount.containsKey(i)){
                inventoryCount.put(i, 0);
            }

        }
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
            updateInventoryCount(currentTransaction.getCartedItems());

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

    public int getItemLimit() {
        return itemLimit;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public HashMap<Integer, Integer> getInventoryCount() {
        return inventoryCount;
    }

    public CoinDispenser getCoinBank() {
        return coinBank;
    }
}
