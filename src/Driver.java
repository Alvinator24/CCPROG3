// import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {


    
    // we only need to store the data (vending machines), so ArrayList is optimal
    // if we needed to manipulate the data, LinkedLists are better (internally doubly linked lists)

    public static void main(String[] args) {
        //pre-defined objects (to make demo easy to explain)
        ArrayList<Denomination> denoms = new ArrayList<Denomination>();
        denoms.add(new Denomination(.01));
        denoms.add(new Denomination(.05));
        denoms.add(new Denomination(.1));
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

        Item pineapple = new Item("pineapple", 75, 50);
        Item apple = new Item("apple", 50, 40);
        Item strawberry = new Item("strawberry", 60, 40);
        Item orange = new Item("orange", 45, 70);
        Item banana = new Item("banana", 35, 60);
        Item dragonfruit = new Item("dragon fruit", 100, 90);


        Scanner input = new Scanner(System.in);

        String name;
        int itemLimit;
        int slotLimit;

        System.out.print("Enter vending machine name: ");
        name = input.nextLine();
        do{
            System.out.print("Enter vending slot limit: ");
            slotLimit = input.nextInt();
        }while(slotLimit < 8);

        do{
            System.out.print("Enter slot item limit: ");
            itemLimit = input.nextInt();
        }while(itemLimit < 10);


        VendingMachine testVending = new VendingMachine(name, slotLimit, itemLimit, denoms);
        testVending.addItem(pineapple);
        testVending.addItem(apple);
        testVending.addItem(orange);
        testVending.addItem(banana);
        testVending.addItem(dragonfruit);
        testVending.addItem(strawberry);

        testVending.restockItem(new Item("pineapple", 75, 50));
        testVending.restockItem(new Item("pineapple", 75, 50));
        testVending.restockItem(new Item("pineapple", 75, 50));

        testVending.restockItem(new Item("apple", 50, 40));
        testVending.restockItem(new Item("apple", 50, 40));

        testVending.restockItem(new Item("orange", 45, 70));
        testVending.restockItem(new Item("orange", 45, 70));

        testVending.restockItem(new Item("strawberry", 60, 40));
        testVending.restockItem(new Item("strawberry", 60, 40));

        testVending.restockItem(new Item("banana", 35, 60));

        testVending.restockItem(new Item("dragon fruit", 100, 90));
        testVending.restockItem(new Item("dragon fruit", 100, 90));



        int choice1, choice2, choice3, choice4;
        boolean validChoice;
        String strchoice;

        testVending.coinBank.printMoney();
        do{
            System.out.println("[1]. Test customer-side");
            System.out.println("[2]. Test maintenance");
            System.out.println("[3]. Exit");

            System.out.print(": ");
            choice1 = input.nextInt();


            switch(choice1){
                case 1:{
                    do{
                        testVending.displayItems();
                        System.out.println("===============");
                        System.out.println("In cart:");
                        testVending.displayTransaction();
                        System.out.println("[1]. Add to cart");
                        System.out.println("[2]. Remove from cart");
                        System.out.println("[3]. Checkout");
                        System.out.println("[4]. Exit (change mind)");
                        System.out.println("[5]. Insert denomination");

                        System.out.println(": ");
                        choice2 = input.nextInt();

                        switch(choice2){
                            case 1:{
                                do{
                                    validChoice = false;
                                    System.out.println("Enter slot number or 'x' to exit");
                                    System.out.print(": ");
                                    if(input.hasNextInt()){
                                        choice2 = input.nextInt();
                                        validChoice = testVending.purchaseItem(choice2);
                                        choice2 = 1;
                                    }
                                    else{
                                        strchoice = input.nextLine();
                                        if(strchoice.equals("x")){
                                            validChoice = true;
                                        }
                                    }


                                }while(!validChoice);
                                break;
                            }

                            case 2:{
                                do{
                                    validChoice = false;
                                    System.out.println("Enter slot number or 'x' to exit");
                                    System.out.print(": ");
                                    if(input.hasNextInt()){
                                        choice2 = input.nextInt();
                                        testVending.currentTransaction.removeItem(choice2);
                                        validChoice = true;
                                        choice2 = 1;
                                    }
                                    else{
                                        strchoice = input.nextLine();
                                        if(strchoice.equals("x")){
                                            validChoice = true;
                                        }
                                    }


                                }while(!validChoice);
                            }
                            case 5:{
                                do{
                                    validChoice = false;
                                    System.out.println("Insert value of denomination or 'x' to exit");
                                    System.out.print(": ");
                                    if(input.hasNextInt()){
                                        choice2 = input.nextInt();
                                        validChoice = testVending.dispenseCoin(choice2);
                                        choice2 = 1;
                                    }
                                    else{
                                        strchoice = input.nextLine();
                                        if(strchoice.equals("x")){
                                            validChoice = true;
                                        }
                                    }


                                }while(!validChoice);
                                break;
                            }

                        }


                    }while(!(choice2 == 4 || choice2 == 3));

                    switch(choice2){
                        case 3:
                            testVending.checkout(true);
                            break;
                        case 4:
                            testVending.checkout(false);
                            break;
                    }
                    testVending.coinBank.printMoney();

                    break;
                }
                case 2:{
                    input.nextLine();
                    do{

                        System.out.println("Enter Password or 'x' to exit:  ");
                        strchoice = input.nextLine();
                        testVending.maintenance(strchoice);

                    }while(!strchoice.equals("x"));
                }


            }

        }while(choice1 != 3);






    }



    // MCO2 (Pizza Ingredients)
    // Thin or thick crust
    // Sauce
    // Toppings
    // cannot buy alone: dough, sauce

}