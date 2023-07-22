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



    
    // public Item

   
    

}
