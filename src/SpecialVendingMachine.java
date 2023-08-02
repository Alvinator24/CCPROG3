package com.example;

import java.util.ArrayList;

/**
 * This class is where a SpecialVedningMachine class will be created 
 * which will inherit most of its traits from the VendingMachine class.
 * It will also override some methods from the VedningMachine class for
 * its own use.
 */
public class SpecialVendingMachine extends VendingMachine{

    /**
     * Constructs an instance of a Special Vedning Machine and
     * its components.
     * 
     * @param name          The name of the Special Vending Machine.
     * @param slotLimit     The maximum number of slots for each Vending Machine.
     * @param itemLimit     The maximum number of slots for each Slot.
     * @param denoms        Collection of denominations of currency that the 
     *                      Vending Machine accepts.
     * @param menu          A list of slots for presentation.
     */
    SpecialVendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms, ArrayList<Slot> menu){
        super(name, slotLimit, itemLimit, denoms);

        for(Slot slot: menu){
            addSlot(slot);
        }
        newTransaction();
    }

    /**
     * Attempts to add a new slot in the Special Vending Machine
     * 
     * @param slot  The new slot being added.
     * @return  True if the slot is successfully added, false if otherwise.
     */
    private boolean addSlot(Slot slot) {
        boolean isSuccessful = false;
        if(itemSlots.size() < slotLimit){
            itemSlots.add(slot);
            isSuccessful = true;
        }
        return isSuccessful;
    }

    /**
     * Overrides the original purchaseItem method in order to factor in the 
     * type of item being purchased to check if such an item can be purchased.
     * 
     * @param slotNum   The number position of the slot of the item being purchased.
     * @return  True if the item in the slot is purchaseable and is therefore bought,
     *          false if otherwise.
     */
    @Override
    public boolean purchaseItem(int slotNum) {
        boolean isSuccessful = true;
        if(itemSlots.get(slotNum).getItemType() == 0 && !currentTransaction.getIsPackage()){
            isSuccessful = false;
        }
        else if(itemSlots.get(slotNum).getItemType() == 3 && currentTransaction.getItemQuantity(slotNum) == 1){
            isSuccessful = false;
        }
        else{
            isSuccessful = currentTransaction.addItem(slotNum);
        }

        return isSuccessful;
    }

    /**
     * Overrides the original removeItem method in order to factor in the
     * type of item being removed from its slot to check if such item can 
     * be removed.
     * 
     * @param slotNum   The number position of the slot of the item being removed.
     * @return  True if the item in the slot is removable and is therefore removed,
     *          false if otherwise.
     */
    @Override
    public boolean removeItem(int slotNum) {
        boolean isSuccessful = false;
        if(itemSlots.get(slotNum).getItemType() == 3 && currentTransaction.getIsPackage()){
            for(int i  = 0; i < itemSlots.size(); ++i){
                int numOfItems = currentTransaction.getCartedItems().get(i);
                if(numOfItems > 0 && itemSlots.get(i).getItemType() == 0){
                    for(int j= 0; j < numOfItems; ++j){
                        isSuccessful = currentTransaction.removeItem(i);
                    }
                }
            }
            currentTransaction.removeItem(slotNum);
        }
        else{
            isSuccessful = currentTransaction.removeItem(slotNum);
        }
        return isSuccessful;
    }
}
