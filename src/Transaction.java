import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
    ArrayList<Item> cartedItems;
    private HashMap<Denomination, Integer> coinCollection;

    // constructor
    public Transaction(ArrayList<Denomination> denomList) {
            cartedItems = new ArrayList<Item>();

        for(Denomination denom : denomList){
            coinCollection.put(denom, 0); //our factory arbitrarily provides the vending machines with 10 coins
        }

    }

    public int getItemQuantity(Item item){
        int total = 0;
        for(Item i : cartedItems){
            if(i.getItemName().equals(item.getItemName()) && i.getCalories() == item.getCalories() && i.getPrice() == item.getPrice()){
                ++total;
            }
        }
        return total;
    }

    public void addItem(Item item){
        cartedItems.add(item);

    }

    public void removeItem(int index){
        --index;
        cartedItems.remove(index);

    }




    public void displayTransaction() {
        // 
    }

}
