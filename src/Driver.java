
import java.util.ArrayList;

/**
 * Starts the whole program
 */
public class Driver {

    public static void main(String[] args) {

        ArrayList<Denomination> denoms = new ArrayList<Denomination>();
        denoms.add(new Denomination(.25));
        denoms.add(new Denomination(1));
        denoms.add(new Denomination(5));
        denoms.add(new Denomination(10));
        denoms.add(new Denomination(20));
        denoms.add(new Denomination(50));
        denoms.add(new Denomination(100));
        denoms.add(new Denomination(200));
        denoms.add(new Denomination(500));
        denoms.add(new Denomination(1000));

        Factory vendingFactory = new Factory(denoms);
    }
}