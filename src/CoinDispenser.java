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

    public HashMap<Denomination, Integer> checkout(Transaction transaction){
        HashMap<Denomination, Integer> change = new HashMap<Denomination, Integer>();
        HashMap<Denomination, Integer> newBank = simulateTemporaryCoinCollection(transaction.getCoinCollection());

        for(Denomination denom : denomList){
            change.put(denom, 0);
        }

        change = simulateCheckout(transaction.getCoinCollection(), newBank, transaction);
        coinCollection = newBank;

        return change;
    }

    public HashMap<Denomination, Integer> simulateTemporaryCoinCollection(HashMap<Denomination, Integer> givenPouch)
    {
        HashMap<Denomination, Integer> temporaryBank = new HashMap<Denomination, Integer>();
        for(Denomination denom : this.denomList){
            temporaryBank.put(denom, givenPouch.get(denom) + coinCollection.get(denom)); //our factory arbitrarily provides the vending machines with 10 coins
        }
        return temporaryBank;

    }

    public HashMap<Denomination, Integer> simulateCheckout(HashMap<Denomination, Integer> givenPouch, HashMap<Denomination, Integer> simulatedBank, Transaction transaction) {
        boolean enoughChange = true;
        HashMap<Denomination, Integer> possibleChange = new HashMap<Denomination, Integer>();
        for(Denomination denom : denomList){
            possibleChange.put(denom, 0);
        }
        if(simulatedBank == null){
            simulatedBank = simulateTemporaryCoinCollection(givenPouch);
        }


        double remainingChange = transaction.getTotalPrice() - transaction.getTotalDispensed();

        int denomCounter = 0, numOfCoins;
        while(remainingChange > 0 && enoughChange){
            numOfCoins = 0;
            if(denomCounter < denomList.size()){
                numOfCoins = (int)(remainingChange / denomList.get(denomCounter).getValue());
                if(numOfCoins > simulatedBank.get(denomList.get(denomCounter))){
                    numOfCoins = simulatedBank.get(denomList.get(denomCounter));
                }
                possibleChange.put(denomList.get(denomCounter), numOfCoins);
                remainingChange -= numOfCoins * denomList.get(denomCounter).getValue();

                simulatedBank.put(denomList.get(denomCounter), simulatedBank.get(denomList.get(denomCounter)) - numOfCoins);
                ++denomCounter;
            }
            else{
                enoughChange = false;
                possibleChange = null;
            }
        }

        return possibleChange;
    }

    public void dispenseCoin(HashMap<Denomination, Integer> coinPouch){
        for(Denomination denom : denomList){
            int number = coinPouch.get(denom);
            for(int i = 0; i < number; ++i){
                dispenseCoin(denom);
            }

        }

    }





    public static double countCoins(ArrayList<Denomination> denomList, HashMap<Denomination, Integer> coinPouch){
        double total = 0;

        for(Denomination denom : denomList){
           total += coinPouch.get(denom) * denom.getValue();
        }

        return total;

    }

    // removed coins from denominations until 10 is left;
    public HashMap<Denomination, Integer> emptyMachine(){
        HashMap<Denomination, Integer> change = new HashMap<Denomination, Integer>();
        int numOfCoins;
        for(Denomination denom: denomList){
            change.put(denom, 0);
            numOfCoins = coinCollection.get(denom);
            if(numOfCoins > 10){
                numOfCoins -= 10;
                change.put(denom, numOfCoins);
                coinCollection.put(denom, 10);
            }
        }
        return change;
    }

    public void printMoney(){
        System.out.println("Denomination : Count");
        for(Denomination d : denomList){
            System.out.println(d.getValue() +" : "+ coinCollection.get(d));

        }

    }

}
