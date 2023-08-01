import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Transaction {
    HashMap<Integer, Integer> cartedItems; //make private
    ArrayList<Slot> vendingProducts; //make private
     HashMap<Denomination, Integer> coinCollection; //make private
    private ArrayList<Denomination> denomList;
    private double totalDispensed;
    private double totalCalories;
    private double totalPrice;

    private boolean isPackage; //if true, solo-only items can be added.

    // constructor
    public Transaction(ArrayList<Denomination> denomList, ArrayList<Slot> vendingProducts) {
        this.denomList = denomList;
        totalDispensed = 0;
        isPackage = false;
        coinCollection = new HashMap<Denomination, Integer>();
        for(Denomination denom : denomList){
            coinCollection.put(denom, 0); //our factory arbitrarily provides the vending machines with 10 coins
        }

        this.vendingProducts = vendingProducts;
        cartedItems = new HashMap<Integer, Integer>();
        for(int i = 0; i < this.vendingProducts.size(); ++i){
            System.out.println(vendingProducts.get(i).getItem().getItemName());
            cartedItems.put(i, 0);
        }

    }

    public int getItemQuantity(int index){
        return cartedItems.get(index);

    }

    public boolean addItem(int index){
        boolean successful = false;
        if(vendingProducts.get(index).getItemType() == 3){
            isPackage = true;
        }
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
        if(vendingProducts.get(index).getItemType() == 3){
            isPackage = false;
        }
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

    public void setIsPackage(boolean set){
        isPackage = set;
    }

    public boolean getIsPackage(){
        return isPackage;
    }


    public ArrayList<String> getMessages() {
        ArrayList<String> string_outputMessages = new ArrayList<String>();
        ArrayList<Message> object_outputMessages = new ArrayList<Message>();

        for(int i = 0; i < vendingProducts.size(); ++i){
            if(cartedItems.get(i) > 0){
                ArrayList<Message> tempMessages = vendingProducts.get(i).getMessages();
                for(Message tempMessage: tempMessages){
                    if(object_outputMessages.size() == 0){
                        object_outputMessages.add(tempMessage);
                    }
                    else{
                        for(int j = 0; j < object_outputMessages.size(); ++j){
                            if(tempMessage.getPrecedence() <= object_outputMessages.get(j).getPrecedence()) {
                                object_outputMessages.add(j, tempMessage);
                                break;
                            }
                            if(j == object_outputMessages.size() - 1 && tempMessage.getPrecedence() > object_outputMessages.get(j).getPrecedence()){
                                    object_outputMessages.add(tempMessage);
                                    break;
                                }
                        }
                    }
                }
            }
        }

        for(Message message: object_outputMessages){
            string_outputMessages.add(message.getMessage());
        }

        return string_outputMessages;
    }
}
