import java.util.*;
public class CoinDispenser {
    
    private ArrayList<Denomination> denomList;
    private HashMap<Denomination, Integer> coinCollection; 
    
    public CoinDispenser(ArrayList<Denomination> denomList){
        coinCollection = new HashMap<Denomination, Integer>();
        this.denomList = denomList;

        for(Denomination denom : this.denomList){
            coinCollection.put(denom, 10); //our factory arbitrarily provides the vending machines with 10 coins
        }

    }


    public Denomination getDenom(double value){
        Denomination denomFound = null;

        for(Denomination denom : denomList){
            if(denom.getValue() == value){
                denomFound = denom;
            }
        }
        return denomFound;
    }



    // adds coins to the machine
    public void dispenseCoin(Denomination denom){
        int coinCount = coinCollection.get(denom) + 1;
        coinCollection.put(denom, coinCount);
    }

    public void dispenseCoin(double coin){
        dispenseCoin(getDenom(coin));
    }

    public float dispenseCoin(Transaction transaction){

        
        return 0;
    }

    // removes coins to the machine
    public float removeCoin() {
        return 0;
    }

}
