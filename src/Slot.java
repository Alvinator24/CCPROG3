import java.util.ArrayList;

public class Slot {

//SLOT SHOULD NOT STORE QUANTITY, BUT INSTANCES

    private Item item;
    private int itemLimit;
    private double price;
    
    private ArrayList<Item> itemList;


    public Slot(Item item, int itemLimit, double price) {
        this.item = item;
        this.itemLimit = itemLimit;
        this.price = price;
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
        System.out.println(itemList.size());
        if(itemList.size() > 0){
            itemList.remove(itemList.size() - 1);
        }
        System.out.println(itemList.size());
    }

    public void setPrice(double price){
        this.price = price;
    }



    
    // public Item

   
    

}
