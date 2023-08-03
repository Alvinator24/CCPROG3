/**
 * This class is where each denomination of a currency will be
 * instantiated and recorded for use during transaction.
 */
public class Denomination {

    private final double value;

    /**
     * Constructs an instance of the Denomination class to record
     * each denomination of a currency.
     *
     * @param value The monetary value of the denomination.
     */
    public Denomination(double value) {
        this.value = value;
    }

    /**
     * Allows other classes to access the value of the denomination.
     *
     * @return  Double variable of the value of the denomination.
     */
    public double getValue(){
        return value;
    }

}