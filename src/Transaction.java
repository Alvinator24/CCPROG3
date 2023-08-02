package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents a transaction a user performs for a vending machine.
 */
public class Transaction {
    HashMap<Integer, Integer> cartedItems; 
    private ArrayList<Slot> vendingProducts; 
    private HashMap<Denomination, Integer> coinCollection; 
    private ArrayList<Denomination> denomList;
    private double totalDispensed;
    private double totalCalories;
    private double totalPrice;

    private boolean isPackage; //if true, solo-only items can be added.

    /**
     * Constructs an instance of the Transaction class where the user 
     * prepares for the purchase of their items from the Vending Machine.
     * 
     * @param denomList         The list of denominations of the currency 
     *                          supported by the vending machine.
     * @param vendingProducts   The list of slots representing available 
     *                          products in the vending machine.
     */
    public Transaction(ArrayList<Denomination> denomList, ArrayList<Slot> vendingProducts) {
        this.denomList = denomList;
        totalDispensed = 0;
        isPackage = false;
        coinCollection = new HashMap<Denomination, Integer>();
        for(Denomination denom : denomList){
            coinCollection.put(denom, 0); //our factory arbitrarily provides the vending machines with 10 coins
        }

        this.vendingProducts = vendingProducts;
        cartedItems = new HashMap<Integer, Integer>();
        for(int i = 0; i < this.vendingProducts.size(); ++i){
            System.out.println(vendingProducts.get(i).getItem().getItemName());
            cartedItems.put(i, 0);
        }

    }

    /**
     * Allows other classes to access the quantity of a specific item 
     * in the cart.
     *
     * @param index The index of the item in the cart.
     * @return The quantity of the item in the cart.
     */
    public int getItemQuantity(int index){
        return cartedItems.get(index);

    }

    /**
     * Attempts to add an item to the user's cart for purchase.
     *
     * @param index The index of the item to add to the cart.
     * @return  True if the item is available and is successfully added, 
     *          false if otherwise.
     */
    public boolean addItem(int index){
        boolean successful = false;
        if(vendingProducts.get(index).getItemType() == 3){
            isPackage = true;
        }
        if(getItemQuantity(index) < vendingProducts.get(index).getQuantity()){
            cartedItems.put(index, cartedItems.get(index) + 1);
            totalCalories += vendingProducts.get(index).getItem().getCalories();
            totalPrice += vendingProducts.get(index).getPrice();
            successful = true;
        }

        return successful;
    }

    /**
     * Attempts to remove item from the user's cart.
     *
     * @param index The index of the item to remove from the cart.
     * @return  True if the item can be removed and is therefore successfully removed, 
     *          false otherwise.
     */
    public boolean removeItem(int index){
        boolean successfull = false;
        if(vendingProducts.get(index).getItemType() == 3){
            isPackage = false;
        }
        if(getItemQuantity(index) > 0){
            cartedItems.put(index, cartedItems.get(index) - 1);
            totalPrice -= vendingProducts.get(index).getPrice();
            totalCalories -= vendingProducts.get(index).getItem().getCalories();
            successfull = true;
        }
        return successfull;
    }

    /**
     * Attmepts to dispense a coin of the specified value and 
     * updates the coin collection.
     *
     * @param value The value of the denomination of currency to dispense.
     * @return  True if such denomination of coin is successfully dispensed, 
     *          false otherwise.
     */
    public boolean dispenseCoin(double value){
        boolean isSuccesfull = false;
        Denomination denomFound = null;
        for(Denomination denom : denomList){
            if(denom.getValue() == value){
                denomFound = denom;
                isSuccesfull = true;
                break;
            }
        }

        if(isSuccesfull){
            coinCollection.put(denomFound, coinCollection.get(denomFound) + 1);
            totalDispensed += denomFound.getValue();

        }

        return isSuccesfull;
    }

    /**
     * Allows other classes to access the total amount of money 
     * dispensed in this transaction.
     *
     * @return  The total amount of money dispensed.
     */
    public double getTotalDispensed(){
        return totalDispensed;
    }

    /**
     * Allows other classes to access the coin collection Hash Map 
     * representing the coins used in the transaction.
     *
     * @return  The coin collection map.
     */
    public HashMap<Denomination, Integer> getCoinCollection(){
        return coinCollection;
    }

    /**
     * Allows other classes to access the carted items map 
     * representing the items added to the cart for purchase.
     *
     * @return  The carted items map.
     */
    public HashMap<Integer, Integer> getCartedItems() {
        return cartedItems;
    }

    /**
     * Allows other classes to access the total calories 
     * of all items in the user's cart.
     *
     * @return The total calories of the carted items.
     */
    public double getTotalCalories(){
        return totalCalories;
    }

    /**
     * Allows other classes to access the total price 
     * of all items in the user's cart.
     *
     * @return The total price of the carted items.
     */
    public double getTotalPrice(){
        return totalPrice;
    }

    /**
     * Sets the coin collection map representing the change 
     * given to the user in this transaction.
     *
     * @param change The coin collection map representing the change given.
     */
    public void setReceipt(HashMap<Denomination, Integer> change){
        coinCollection = change;
    }

    /**
     * Sets the truth value over whether or not the current 
     * transaction includes a package.
     * 
     * @param set   The truth value of whether or not an item in the
     *              transaction is a package.
     */
    public void setIsPackage(boolean set){
        isPackage = set;
    }

    /**
     * Checks if the transaction includes a package.
     *
     * @return True if the transaction includes a package, false if otherwise.
     */
    public boolean getIsPackage(){
        return isPackage;
    }

    /**
     * Allows other classes to access a list of messages 
     * related to the carted items in this transaction.
     *
     * @return A list of messages.
     */
    public ArrayList<String> getMessages() {
        ArrayList<String> string_outputMessages = new ArrayList<String>();
        ArrayList<Message> object_outputMessages = new ArrayList<Message>();

        for(int i = 0; i < vendingProducts.size(); ++i){
            if(cartedItems.get(i) > 0){
                ArrayList<Message> tempMessages = vendingProducts.get(i).getMessages();
                for(Message tempMessage: tempMessages){
                    if(object_outputMessages.size() == 0){
                        object_outputMessages.add(tempMessage);
                    }
                    else{
                        for(int j = 0; j < object_outputMessages.size(); ++j){
                            if(tempMessage.getPrecedence() <= object_outputMessages.get(j).getPrecedence()) {
                                object_outputMessages.add(j, tempMessage);
                                break;
                            }
                            if(j == object_outputMessages.size() - 1 && tempMessage.getPrecedence() > object_outputMessages.get(j).getPrecedence()){
                                    object_outputMessages.add(tempMessage);
                                    break;
                                }
                        }
                    }
                }
            }
        }

        for(Message message: object_outputMessages){
            string_outputMessages.add(message.getMessage());
        }

        return string_outputMessages;
    }
}
