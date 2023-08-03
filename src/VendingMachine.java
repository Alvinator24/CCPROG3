import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;
/**
 * This class is where a regular VendingMachine class will be created
 * and where the SpecialVendingMachine class will inherit most of its traits.
 */
public class VendingMachine {

    private String name;
    protected int slotLimit;
    private int itemLimit;
    protected Transaction currentTransaction; //make private

    private CoinDispenser coinBank;

    protected ArrayList<Slot> itemSlots; //make private
    private ArrayList<Denomination> denom;

     private HashMap<Integer, Integer> inventoryCount;

    /**
     * Constructs an instance of a regular Vending Machine and its
     * components.
     *
     * @param name      Name of the Vending Machine being created.
     * @param slotLimit Limit of slots that each Vending Machine can have.
     * @param itemLimit Limit of items that each slot can hold.
     * @param denoms    Collection of denominations for the currency the
     *                  Vending Machine accepts.
     */

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

    /**
     * Attempts to add a new slot to the Vending Machine if such
     * a move is possible.
     *
     * @param item      Name of the item that the slot is meant to hold.
     * @param price     Price of the item that is being added.
     * @param itemType  Identifies the type of item being added to the slot.
     * @param messages  The messages created for the items in the given slot.
     *
     * @return  True if the attempt is successful and the slot is added,
     *          false if otherwise.
     */
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

    /**
     * Attempts to remove an existing slot to the Vending Machine
     * if such a move is possible.
     *
     * @param index Index of the slot that is being removed.
     *
     * @return  True if the slot exists and is successfully removed,
     *          false if othewise.
     */
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

    /**
     * Attempts to restock an item in the Vending Machine by accessing
     * its corresponding slot.
     *
     * @param adjustments   The values for the new stock of items being added.
     *
     * @return Whether the algorithm successfully restocked the items
     */
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


    /**
     * Updates how much was in inventory since last restock
     *
     * @param sold   Contains what has been sold and how much of it.
     **/
    private void updateInventoryCount(HashMap<Integer, Integer> sold) {
        refreshInventoryCount();

        for(int i = 0; i < itemSlots.size(); ++i) {
            inventoryCount.put(i, inventoryCount.get(i) + sold.get(i));
        }
    }

    /**
     * In case of unaccounted new slots, this updates
     * how much was in inventory since last restock.
     **/
    public void refreshInventoryCount(){
        for(int i = 0; i < itemSlots.size(); ++i){
            if(!inventoryCount.containsKey(i)){
                inventoryCount.put(i, 0);
            }

        }
    }

    /**
     * Restocks the cash inside a Vending Machine whenever cash is
     * needed.
     *
     * @param coinPouch The collection of denominations and their
     *                  quantities to be added.
     */
    public void restockCash(HashMap<Denomination, Integer> coinPouch) {
        coinBank.dispenseCoin(coinPouch);
    }


    /**
     * Empties the cash inside a Vending Machine.
     *
     * @return The emptied Hashmap holding the cash in the
     *         Vending Machine.
     */
    public HashMap<Denomination, Integer> emptyCoins() {
        return coinBank.emptyMachine();
    }


    //BELOW ARE FOR ITEM PURCHASING

    /**
     * This method is used ot purchase items in the vending machine
     *
     * @param slotNum   The slot contaning the item that is being purchased.
     * @return  The item that is being purchased.
     */
    public boolean purchaseItem(int slotNum) {
        return currentTransaction.addItem(slotNum);
    }

    /**
     * This method is used to remove items from the user's transaction
     *
     * @param slotNum   The slot contaning the item that is being returned.
     * @return  The item that is being returned..
     */
    public boolean removeItem(int slotNum) {
        return currentTransaction.removeItem(slotNum);
    }

    /**
     * This method is used to simulate the user inserting
     * a coin/bill
     *
     * @param valueInserted The value of the money being inserted.
     * @return The money being inserted.
     */
    public boolean dispenseCoin(double valueInserted) {
        return currentTransaction.dispenseCoin(valueInserted);
    }


    /**
     * Attempts to perform a checkout for the user in order to finish
     * the transaction process and provide the change to the user.
     *
     * @param followThrough     The truth value of the prior process to see if
     *                          this method can be carried out.
     * @param receipt           The reciept made for the order.
     * @param outputMessage     The collection of messages made for the user
     *                          while purchasing the items.
     * @param change            The change the user receives after the transaction.
     * @return  True if the method is possible and therefore carried out,
     *          false if otherwise.
     */
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


        return possible;
    }

    /**
     * Allows other classes to access the name of an item given its slot
     * index.
     *
     * @param index The index of the slot.
     * @return  The name of the item being accessed.
     */
    public String getSlotName(int index) {
        Item item = itemSlots.get(index).getItem();
        return item.getItemName();
    }
    /**
     * Allows other classes to access the calorie amount of an item
     * given its slot index.
     *
     * @param index The index of the slot.
     * @return  The calorie amount of the item being accessed.
     */

    public double getSlotCalories(int index) {
        Item item = itemSlots.get(index).getItem();
        return item.getCalories();
    } //good

    /**
     * Allows other classes to access the price of an item given its slot
     * index.
     *
     * @param index The index of the slot.
     * @return  The price of the item being accessed.
     */
    public double getSlotPrice(int index) {
        return itemSlots.get(index).getPrice();
    } //good

    /**
     * Creates a new Transaction class from the Vending Machine.
     */
    public void newTransaction() {
        currentTransaction = new Transaction(denom, itemSlots);
    }

    /**
     * Allows other classes to access the list of slots of a
     * Vending Machine.
     *
     * @return  The list of slots of the Vending Machine.
     */
    public ArrayList<Slot> getItemSlots() {
        return itemSlots;
    }

    /**
     * Allows other classes to access the name of the
     * Vending Machine.
     *
     * @return The name of the Vending Machine.
     */
    public String getName() {
        return name;
    }


    /**
     * Allows other classes to access the current Transaction
     * being made to their corresponding Vending Machine.
     *
     * @return  The current transaction being made.
     */
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    /**
     * Allows other classes to access inventoryCount,
     * which stores the items sold since last restock
     *
     * @return the items sold since last restock
     */
    public HashMap<Integer, Integer> getInventoryCount() {
        return inventoryCount;
    }

    /**
     * Allows other classes to access the vending machine's
     * coinDispense.
     *
     * @return the vending machine's coin dispenser
     */
    public CoinDispenser getCoinBank() {
        return coinBank;
    }
}
