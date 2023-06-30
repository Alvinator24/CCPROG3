import java.util.*;
public class CoinDispenser {
    
    private ArrayList<Denomination> denomList;
    private HashMap<Denomination, Integer> coinCollection; 
    
    public CoinDispenser(ArrayList<Denomination> denomList){
        coinCollection = new HashMap<Denomination, Integer>();
        this.denomList = denomList;

        for(Denomination denom : this.denomList){
            coinCollection.put(denom, 0);
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
    public float dispenseCoin(denom){

        
    
    }

    public float dispenseCoin(double){

        
    
    }

    public float dispenseCoin(transaction){

        
    
    }

    // removes coins to the machine
    public float removeCoin() {

    }

}
