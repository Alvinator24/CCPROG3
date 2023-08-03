import java.util.*;
/**
 * This class represents a coin dispenser for managing coin collections
 * and dispensing coins in a Vending Machine.
 */
public class CoinDispenser {
    
    private final ArrayList<Denomination> denomList;
    private HashMap<Denomination, Integer> coinCollection;

    /**
     * Constructs a new CoinDispenser with the specified denomination list.
     *
     * @param denomList The list of denominations supported by the coin dispenser.
     */

    public CoinDispenser(ArrayList<Denomination> denomList){
        coinCollection = new HashMap<Denomination, Integer>();
        this.denomList = denomList;

        for(Denomination denom : this.denomList){
            coinCollection.put(denom, 100); //our factory arbitrarily provides the vending machines with 10 coins
        }

    }


    /**
     * Retrieves the denomination object corresponding to the given coin value.
     *
     * @param value The value of the coin for which the denomination is sought.
     * @return  The denomination object for the given coin value, or null if not found.
     */
    public Denomination getDenom(double value){
        Denomination denomFound = null;

        for(Denomination denom : denomList){
            if(denom.getValue() == value){
                denomFound = denom;
            }
        }
        return denomFound;
    }



    /**
     * Dispenses a coin of the specified denomination and updates the coin collection.
     *
     * @param denom The denomination of the coin to be dispensed.
     */
    public void dispenseCoin(Denomination denom){
        int coinCount = coinCollection.get(denom) + 1;
        coinCollection.put(denom, coinCount);
    }

    /**
     * Dispenses a coin based on the given coin value and updates the coin collection.
     *
     * @param coin  The value of the coin to be dispensed.
     */
    public void dispenseCoin(double coin){
        dispenseCoin(getDenom(coin));
    }

    /**
     * Calculates and returns the change to be dispensed based on the provided
     * transaction details.
     *
     * @param transaction   The transaction for which the change is to be calculated.
     * @return  A HashMap containing the denominations of coins and their quantities
     *          as change.
     */
    public HashMap<Denomination, Integer> checkout(Transaction transaction){
        HashMap<Denomination, Integer> change = new HashMap<Denomination, Integer>();
        HashMap<Denomination, Integer> newBank = simulateTemporaryCoinCollection(transaction.getCoinCollection());

        for(Denomination denom : denomList){
            change.put(denom, 0);
        }

        //change = simulateCheckout(transaction.getCoinCollection(), newBank, transaction);
        coinCollection = newBank;

        return change;
    }

    /**
     * Simulates the temporary coin collection after the transaction
     * based on the given pouch.
     *
     * @param givenPouch    The original coin pouch from the transaction.
     * @return  A HashMap representing the temporary coin collection.
     */
    public HashMap<Denomination, Integer> simulateTemporaryCoinCollection(HashMap<Denomination, Integer> givenPouch)
    {
        HashMap<Denomination, Integer> temporaryBank = new HashMap<Denomination, Integer>();
        for(Denomination denom : this.denomList){
            temporaryBank.put(denom, givenPouch.get(denom) + coinCollection.get(denom));
        }
        return temporaryBank;

    }

    /**
     * Simulates the checkout process to calculate the possible change for the transaction.
     *
     * @param givenPouch    The original coin pouch from the transaction.
     * @param simulatedBank The simulated temporary coin collection.
     * @param transaction   The transaction for which change is being calculated.
     * @return  A HashMap containing the denominations of coins and their quantities
     *          as possible change.
     */

    public HashMap<Denomination, Integer> simulateCheckout(HashMap<Denomination, Integer> givenPouch, HashMap<Denomination, Integer> simulatedBank, Transaction transaction) {
        boolean enoughChange = true;
        HashMap<Denomination, Integer> possibleChange = new HashMap<Denomination, Integer>();
        for(Denomination denom: denomList){
            possibleChange.put(denom, 0);
        }

        if(simulatedBank == null){
            simulatedBank = simulateTemporaryCoinCollection(givenPouch);
        }


        double remainingChange =  transaction.getTotalDispensed() - transaction.getTotalPrice();
        if(remainingChange < 0){
            enoughChange = false;
            possibleChange = null;
        }

        int denomCounter = denomList.size() - 1, numOfCoins;
        while(remainingChange > 0 && enoughChange){
            numOfCoins = 0;
            if(denomCounter >= 0){
                numOfCoins = (int)(remainingChange / denomList.get(denomCounter).getValue());
                if(numOfCoins > simulatedBank.get(denomList.get(denomCounter))){
                    numOfCoins = simulatedBank.get(denomList.get(denomCounter));
                }
                possibleChange.put(denomList.get(denomCounter), numOfCoins);
                remainingChange -= numOfCoins * denomList.get(denomCounter).getValue();

                simulatedBank.put(denomList.get(denomCounter), simulatedBank.get(denomList.get(denomCounter)) - numOfCoins);
                --denomCounter;
            }
            else{
                enoughChange = false;
                possibleChange = null;
            }
        }


        return possibleChange;
    }

    /**
     * Dispenses all of the coins based on the given coin pouch
     * and updates the coin collection.
     *
     * @param coinPouch A HashMap containing the denominations of coins
     *                  and their quantities.
     */
    public void dispenseCoin(HashMap<Denomination, Integer> coinPouch){
        for(Denomination denom : denomList){
            int number = coinPouch.get(denom);
            for(int i = 0; i < number; ++i){
                dispenseCoin(denom);
            }

        }

    }

    /**
     * Counts and returns the total value of coins based on the given coin pouch
     * and denomination list.
     *
     * @param denomList The list of denominations used to calculate the total value.
     * @param coinPouch A HashMap containing the denominations of coins and their quantities.
     * @return The total value of coins in the coin pouch.
     */
    public static double countCoins(ArrayList<Denomination> denomList, HashMap<Denomination, Integer> coinPouch){
        double total = 0;

        if(coinPouch != null){
            for(Denomination denom : denomList){
                total += coinPouch.get(denom) * denom.getValue();



            }
        }


        return total;

    }

    /**
     * Removes excess coins from denominations until 10 coins are left
     * in the coin collection.
     *
     * @return  A HashMap representing the change with removed
     *          excess coins from denominations.
     */
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

    /**
     * Allows other classes to access the vending machine's coin
     * collection
     *
     * @return  the vending machine's coin collection
     */
    public HashMap<Denomination, Integer> getCoinCollection() {
        return coinCollection;
    }
}
