// import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {


    
    // we only need to store the data (vending machines), so ArrayList is optimal
    // if we needed to manipulate the data, LinkedLists are better (internally doubly linked lists)

    public static void homepage() {

        System.out.println("===== Vending Machine Factory =====");
        System.out.println("1. Create a Regular Vending Machine");
        System.out.println("2. Create a Special Vending Machine");
        System.out.println("3. Exit");

    }

    ArrayList<VendingMachine> vendingMachineList = new ArrayList<VendingMachine>();
    // make two separate classes, rvc and svc

    public static void main(String[] args) {

        homepage();

        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();

        sc.close();

        // MVC architecture
        // error checking (controller)

        switch(choice) {
            
            case 1:
                vendingMachineList.add(new VendingMachine("Vending1", 8, 10));
                // make an arraylist of vendingmachine objects
                // new obj then add.
                // use MVC here
                // controller is like a gate
                // VendingMachine vendingMachine = new VendingMachine(null, choice, choice);
                // ask user how many slots, then instantiate slot classes
                // ask user how many items each slot
                // create a new VendingMachine object (will all its non-customizable features) and store it in the ArrayList
                // once the vending machine is done building, allow the customer to buy stuff from it
            case 2:

            case 3:
                homepage();

        }


    }

    // MCO2 (Pizza Ingredients)
    // Thin or thick crust
    // Sauce
    // Toppings
    // cannot buy alone: dough, sauce

}