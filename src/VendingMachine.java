package com.example;

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

     CoinDispenser coinBank;

    ArrayList<Slot> itemSlots; //make private
    private ArrayList<Denomination> denom;

     HashMap<Integer, Integer> inventoryCount;

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
        updateInventoryCount();
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
        return isSuccessful;
    }

    /**
     * Attempts to restock an item in the Vending Machine by accessing 
     * its corresponding slot.
     * 
     * @param adjustments   The values for the new stock of items being added.
     * @param previousStock The values for the old stock of items that are
     *                      being added by the new stock.
     * @return
     */
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

    /**
     * Updates the Inventory Count of a Vending Machine's slots and
     * items.
     */
    private void updateInventoryCount() {
        HashMap<Integer, Integer> newCount = new HashMap<Integer, Integer>();
        for(int i = 0; i < itemSlots.size(); ++i){
            newCount.put(i, itemSlots.get(i).getQuantity());
        }
        inventoryCount = newCount;
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
     * Allows other classes to access a Slot in the Vending Machine
     * to select the item/s it's holding.
     * 
     * @param slotNum   The slot contaning the item that is being purchased.
     * @return  The item that is being purchased.
     */
    public boolean purchaseItem(int slotNum) {
        return currentTransaction.addItem(slotNum);
    }

    /**
     * Allows other classes to access a Slot in the Vending Machine
     * to return the item/s the user selected back to its slot.
     * 
     * @param slotNum   The slot contaning the item that is being returned.
     * @return  The item that is being returned..
     */
    public boolean removeItem(int slotNum) {
        return currentTransaction.removeItem(slotNum);
    }

    /**
     * Allows other classes to acces the coin pouch of the Vending
     * Machine in order to collect the money the user is using to
     * pay for the items it is purchasing.
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
            currentTransaction.setReceipt(coinBank.checkout(currentTransaction));
            outputMessage = currentTransaction.getMessages();
            receipt = currentTransaction;
            newTransaction();
        } else if (followThrough && !possible) {
            outputMessage.add("Machine does not have the appropriate change for this order");
        }
        System.out.println(String.valueOf(possible));


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
    } 

    /**
     * Allows other classes to access the price of an item given its slot 
     * index.
     * 
     * @param index The index of the slot.
     * @return  The price of the item being accessed.
     */
    public double getSlotPrice(int index) {
        return itemSlots.get(index).getPrice();
    }

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
}
