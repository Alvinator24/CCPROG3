import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
    private HashMap<Integer, Integer> cartedItems;
    private ArrayList<Slot> vendingProducts;
    private HashMap<Denomination, Integer> coinCollection;
    private ArrayList<Denomination> denomList;
    private double totalDispensed;
    private double totalCalories;
    private double totalPrice;

    // constructor
    public Transaction(ArrayList<Denomination> denomList, ArrayList<Slot> vendingProducts) {
        this.denomList = denomList;
        totalDispensed = 0;
        coinCollection = new HashMap<Denomination, Integer>();
        for(Denomination denom : denomList){
            coinCollection.put(denom, 0); //our factory arbitrarily provides the vending machines with 10 coins
        }

        this.vendingProducts = vendingProducts;
        cartedItems = new HashMap<Integer, Integer>();
        for(int i = 0; i < vendingProducts.size(); ++i){
            cartedItems.put(i, 0);
        }

    }

    public int getItemQuantity(int index){
        return cartedItems.get(index);

    }

    public boolean addItem(int index){
        boolean successful = false;
        if(getItemQuantity(index) < vendingProducts.get(index).getQuantity()){
            cartedItems.put(index, cartedItems.get(index) + 1);
            totalCalories += vendingProducts.get(index).getItem().getCalories();
            totalPrice += vendingProducts.get(index).getPrice();
            successful = true;
        }

        return successful;
    }

    public boolean removeItem(int index){
        boolean successfull = false;
        if(getItemQuantity(index) > 0){
            cartedItems.put(index, cartedItems.get(index) - 1);
            totalPrice -= vendingProducts.get(index).getPrice();
            totalCalories -= vendingProducts.get(index).getItem().getCalories();
            successfull = true;
        }
        return successfull;
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






    public double getTotalDispensed(){
        return totalDispensed;
    }

    public HashMap<Denomination, Integer> getCoinCollection(){
        return coinCollection;
    }

    public HashMap<Integer, Integer> getCartedItems() {
        return cartedItems;
    }

    public double getTotalCalories(){
        return totalCalories;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void setReceipt(HashMap<Denomination, Integer> change){
        coinCollection = change;
    }

}
