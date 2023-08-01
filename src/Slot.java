import java.util.ArrayList;
import java.util.HashMap;

public class Slot {

//SLOT SHOULD NOT STORE QUANTITY, BUT INSTANCES

    private Item item;
    private int itemLimit;
    private double price;
    private int itemType; //0 - solo | 1 - can be standalone | 2 - allows solo to be bought
    
    private ArrayList<Item> itemList;
    private ArrayList<Message> messages;

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

    public int getQuantity() {
        return itemList.size();
    }

    public Item getItem(){
        return item;
    }

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

    public boolean removeItem(){
        boolean successfull = false;
        if(itemList.size() > 0){
            itemList.remove(itemList.size() - 1);
            successfull = true;
        }
        return successfull;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public int getItemType(){
        return itemType;
    }

   
    

}
