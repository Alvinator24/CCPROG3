import java.util.ArrayList;

public class Slot {

//SLOT SHOULD NOT STORE QUANTITY, BUT INSTANCES

    private Item item;
    private int itemLimit;
    
    private ArrayList<Item> itemList;


    public Slot(Item item, int itemLimit) {
        this.item = item;
        this.itemLimit = itemLimit;
        itemList = new ArrayList<Item>();
        
    }

    public int getQuantity() {
        return itemList.size();
    }

    public Item getItem(){
        return item;

    }

    public boolean addItem(Item item){
        boolean isSuccessful = false;
        if(itemList.size() < itemLimit){
            itemList.add(item);
            isSuccessful = true;
        }
        
        return isSuccessful;

    }

    public void removeItem(){
        if(itemList.size() > 0){
            itemList.remove(itemList.size() - 1);
        }
    }



    
    // public Item

   
    

}
