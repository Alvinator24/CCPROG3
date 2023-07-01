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

    public boolean checkout(Transaction transaction, HashMap<Denomination, Integer> changeReturned, boolean pushThrough){
        boolean enoughChange = false;
        double remainingChange = transaction.getTotalDispensed();

        HashMap<Denomination, Integer> tempCoinCollection = simulateTemporaryCoinCollection(transaction.getCoinCollection());

        for(int i = denomList.size() - 1; i >= 0; --i ) {
            Denomination denom =  denomList.get(i);
            int numOfCoins = (int) (remainingChange / denom.getValue());
            if (numOfCoins > tempCoinCollection.get(denom)) {
                numOfCoins = tempCoinCollection.get(denom);

            }
            tempCoinCollection.put(denom, tempCoinCollection.get(denom) - numOfCoins);
            changeReturned.put(denom, changeReturned.get(denom) + numOfCoins);

            remainingChange -= numOfCoins * denom.getValue();
            if(remainingChange == 0){
                enoughChange = true;
                break;
            }
            else if(remainingChange < 0){
                break;
            }
        }
        if(pushThrough && enoughChange){
            coinCollection = tempCoinCollection;
        }



        return true;
    }

    public void dispenseCoin(HashMap<Denomination, Integer> coinPouch){
        for(Denomination denom : denomList){
            int number = coinPouch.get(denom);
            for(int i = 0; i < number; ++i){
                dispenseCoin(denom);
            }

        }

    }

    public HashMap<Denomination, Integer> simulateTemporaryCoinCollection(HashMap<Denomination, Integer> givenPouch)
    {
        HashMap<Denomination, Integer> temporaryBank = new HashMap<Denomination, Integer>();
        for(Denomination denom : this.denomList){
            temporaryBank.put(denom, givenPouch.get(denom) + coinCollection.get(denom)); //our factory arbitrarily provides the vending machines with 10 coins
        }
        return temporaryBank;

    }



    public static double countCoins(ArrayList<Denomination> denomList, HashMap<Denomination, Integer> coinPouch){
        double total = 0;

        for(Denomination denom : denomList){
           total += coinPouch.get(denom) * denom.getValue();
        }

        return total;

    }

    // removes coins to the machine
    public float removeCoin() {
        return 0;
    }

}
