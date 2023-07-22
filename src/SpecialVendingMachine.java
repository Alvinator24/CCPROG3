import java.util.ArrayList;

public class SpecialVendingMachine extends VendingMachine{


    SpecialVendingMachine(String name, int slotLimit, int itemLimit, ArrayList<Denomination> denoms, ArrayList<Slot> menu){
        super(name, slotLimit, itemLimit, denoms);

        for(Slot slot: menu){
            addSlot(slot);
        }
    }

    private boolean addSlot(Slot slot) {
        boolean isSuccessful = false;
        if(itemSlots.size() < slotLimit){
            itemSlots.add(slot);
            isSuccessful = true;
        }
        return isSuccessful;
    }

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

    @Override
    public boolean removeItem(int slotNum) {
        boolean isSuccessful = false;
        if(itemSlots.get(slotNum).getItemType() == 2){
            for(int i  = 0; i < itemSlots.size(); ++i){
                int numOfItems = currentTransaction.getCartedItems().get(i);
                if(numOfItems > 0 && itemSlots.get(i).getItemType() == 0){
                    for(int j= 0; j < numOfItems; ++j){
                        isSuccessful = currentTransaction.removeItem(i);
                    }
                }
            }
        }
        return isSuccessful;
    }
}
