package com.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is where each Slot of the Vending Machine will be created.
 * It will be used to hold a certain amount of Item classes and is
 * accessed when a user purchases an amount of items or the manager 
 * restocks or removes existing items.
 */
public class Slot {

//SLOT SHOULD NOT STORE QUANTITY, BUT INSTANCES

    private Item item;
    private int itemLimit;
    private double price;
    private int itemType; 
    
    private ArrayList<Item> itemList;
    private ArrayList<Message> messages;

    /**
     * Constructs a slot class for the Vending machine to hold the item
     * for its purchase and restocking.
     * 
     * @param item          Whetever specific item the slot is meant to access.
     * @param itemLimit     The limit as to how many items a slot can hold.
     * @param price         The price of each item in its slot.
     * @param itemType      The value representing the type of item being stored.
     *                      0 - solo
     *                      1 - can be standalone
     *                      2 - allows solo to be bought
     * @param messages      Holds the messages made for the items being held in 
     *                      this slot.
     */
    public Slot(Item item, int itemLimit, double price, int itemType, ArrayList<Message> messages) {
        this.item = item;
        this.itemLimit = itemLimit;
        this.price = price;
        itemList = new ArrayList<Item>();
        this.messages = messages;
        this.itemType = itemType;

        //slot pre-filled for testing purposes (factory management decision, yes)
        for(int i = 0; i < 3; ++i){
            addItem();
        }
        
    }

    /**
     * Allows other classes to access how many items a given slot holds.
     * 
     * @return  Integer variable of the size of the Item ArrayList.
     */
    public int getQuantity() {
        return itemList.size();
    }

    /**
     * Allows other classes to access an item in a given slot.
     * 
     * @return  Item instance class in the slot.
     */
    public Item getItem(){
        return item;
    }

    /**
     * Attempts to add an item intance to its given slot one at a 
     * time if available.
     * 
     * @return  Boolean value if attempt is successful, false if otherwise.
     */
    public boolean addItem(){
        boolean successfull = false;

        if(itemList.size() < itemLimit){
            String name = item.getItemName();
            double calories = item.getCalories();
            itemList.add(new Item(name, calories));
            successfull = true;
        }

        return successfull;
    }

    /**
     * Attempts to remove an item from its given slot one at a time
     * if available.
     * 
     * @return  Boolean value if attempt is successful, false if otherwise.
     */
    public boolean removeItem(){
        boolean successful = false;
        if(itemList.size() > 0){
            itemList.remove(itemList.size() - 1);
            successful = true;
        }
        return successful;
    }

    /**
     * Allows other classes to set the price of the items in their
     * given slot.
     * 
     * @param price Double variable of the new price being set to the items.
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Allows other classes to access the price of items in their 
     * given slot.
     * 
     * @return  Double variable of the current price of the items.
     */
    public double getPrice(){
        return price;
    }

    /**
     * Allows other classes to access the Array List of messages
     * made for the items in their given slot.
     * 
     * @return  ArrayList of all message made for this slot.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Allows other classes to access the type of item being stored
     * in its given slot.
     * 
     * @return  Integer value of the item's type in its slot.
     */
    public int getItemType(){
        return itemType;
    }
}
