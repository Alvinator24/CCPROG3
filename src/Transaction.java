import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
    ArrayList<Item> cartedItems;
    private HashMap<Denomination, Integer> coinCollection;
    private ArrayList<Denomination> denomList;
    private double totalDispensed;

    // constructor
    public Transaction(ArrayList<Denomination> denomList) {
        cartedItems = new ArrayList<Item>();
        this.denomList = denomList;
        totalDispensed = 0;
        coinCollection = new HashMap<Denomination, Integer>();
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




    public void displayTransaction() {
        double totalPrice = 0;
        double totalCalories = 0;
        int index = 0;

        for(Item item: cartedItems){
            ++index;
            double itemPrice = item.getPrice();
            double itemCalories = item.getCalories();


            totalPrice += itemPrice;
            totalCalories += itemCalories;
            System.out.println("["+index+ "]" + item.getItemName() + "(" + itemCalories + " cals)");
            System.out.println(itemPrice);
        }

        System.out.println("----------------");
        System.out.println("Total Price: " + totalPrice + " | Total Calories : " + totalCalories);

        System.out.println("Amount inserted: " + totalDispensed);
        if(totalDispensed >= totalPrice){
            //print change

            //check if change is possible
            //if so let user know

        }

    }

    public double getTotalDispensed(){
        return totalDispensed;
    }

    public HashMap<Denomination, Integer> getCoinCollection(){
        return coinCollection;
    }

}
