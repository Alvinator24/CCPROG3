import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Factory {

    ArrayList<Denomination> denomList;
    ArrayList<VendingMachine> vendingList;
    ArrayList<SlotGui> slotViews;

    View_MainMenu mainMenu;
    VendingMachine_Menu vendingMenu;
    //Maintenance_Menu maintenanceMenu

     Factory(ArrayList<Denomination> denomList){
        this.denomList = denomList;
        this.vendingList = new ArrayList<VendingMachine>();
        mainMenu = new View_MainMenu();
        vendingMenu = new VendingMachine_Menu(denomList);

        //start maintenance menu

        startFactory();
    }

    private void startFactory(){
         createPreSetVendingMachines();
        System.out.println("bug" + vendingList.get(1).getCurrentTransaction().cartedItems.size());
         mainMenu.setupViewElements();

         setupButtonLogic();
         //vendingMenu.refreshElements();



    }

    private void setupButtonLogic(){
        System.out.println(vendingList.size());
         //mainMenu buttons
         mainMenu.setButton_CreateVending(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                String name = "yes";
                int slotLimit = 0, itemLimit = 0;

                try{
                    name = mainMenu.getTextField_VendingName();
                    slotLimit = Integer.parseInt(mainMenu.getTextField_VendingSlotLimit());
                    itemLimit = Integer.parseInt(mainMenu.getTextField_VendingItemLimit());
                    if(slotLimit >= 8 && itemLimit >= 10){
                        vendingList.add(new VendingMachine(name, slotLimit,itemLimit,denomList));

                        mainMenu.setMenu(View_MainMenu.MAIN_MENU);
                        mainMenu.setLabel_Errors("Vending Machine Created!");
                        mainMenu.resetCreateVendingLabels();

                    }
                    else{
                        mainMenu.setLabel_Errors("Slot limit and item limit must at least be 8 and 10!");
                    }

                }catch(NumberFormatException error){
                    mainMenu.setLabel_Errors("Invalid Input!");
                }
                for(VendingMachine vends: vendingList){
                    System.out.println(vends.getName());
                }

            }

         });
         mainMenu.setButton_Test(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 mainMenu.setMenu(3);
                vendingMenu.setMenu(1);
                setVending(0);

                updateListDropDown();
             }
         });

         //vendingMenu
        vendingMenu.setVendingDropDown(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingList.get(vendingMenu.getVendingDropDown().getSelectedIndex()).newTransaction();
                setVending(vendingMenu.getVendingDropDown().getSelectedIndex());
            }
        });


    }

    private void setVending(int index){
         vendingMenu.refreshSlotPanel();
         resetSlotView();

        vendingMenu.setMachineName(vendingList.get(index).getName());
        for(int i = 0; i < vendingList.get(index).getItemSlots().size(); ++i){
            Slot currentSlot = vendingList.get(index).getItemSlots().get(i);
            Item currentItem= currentSlot.getItem();
            SlotGui newSlot = new SlotGui(currentItem.getItemName(), currentSlot.getPrice(), currentItem.getCalories(), vendingList.get(index).getItemSlots().get(i).getQuantity());
            int finalI = i;
            newSlot.setIncrement(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vendingList.get(index).purchaseItem(finalI);
                    updatePurchased(finalI, index);
                }
            });
            newSlot.setDecrement(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vendingList.get(index).removeItem(finalI);
                    if(vendingList.get(index).getItemSlots().get(finalI).getItemType() == 3){
                        updatePurchased(index);
                    }
                    updatePurchased(finalI, index);
                }
            });

            slotViews.add(newSlot);
            vendingMenu.addSlot(newSlot);
        }
        updatePurchased(index);


        vendingMenu.setInsertCoin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingList.get(index).dispenseCoin(vendingMenu.getSelectedDenom());
                updatePurchased(index);
            }
        });
        vendingMenu.setResetButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingList.get(index).newTransaction();
                updatePurchased(index);
            }
        });

    }
    public void updatePurchased(int vendingIndex){
         for(int i = 0; i < slotViews.size(); ++i){
             updatePurchased(i, vendingIndex);
         }
    }
    public void updatePurchased(int index, int vendingIndex){
         System.out.println("Slot " + index + "vending " + vendingIndex);
         slotViews.get(index).setQuantity(vendingList.get(vendingIndex).getCurrentTransaction().cartedItems.get(index), vendingList.get(vendingIndex).getItemSlots().get(index).getQuantity());

         ArrayList<String> ordered = new ArrayList<String>();
         ArrayList<Slot> products = vendingList.get(vendingIndex).getItemSlots();
         HashMap<Integer, Integer> cartedItems = vendingList.get(vendingIndex).getCurrentTransaction().getCartedItems();
         Item currentItem;

         for(int i = 0; i < cartedItems.size(); ++i){
             int number = cartedItems.get(i);
             if(number > 0){

                 currentItem = products.get(i).getItem();
                 ordered.add("•" + " " + currentItem.getItemName() + "(" + number + ")");
                 ordered.add("PHP" + products.get(i).getPrice() * number + "|" + currentItem.getCalories() * number + "kcals");
             }
         }

         ArrayList<String> total = new ArrayList<String>();
         total.add("Inserted: " + String.valueOf(vendingList.get(vendingIndex).getCurrentTransaction().getTotalDispensed()) + "PHP");
        total.add("Total: " + String.valueOf(vendingList.get(vendingIndex).getCurrentTransaction().getTotalPrice()) + "PHP");
         HashMap<Denomination, Integer> change = new HashMap<Denomination, Integer>();
         change.put(denomList.get(0), 1000);
         if(vendingList.get(vendingIndex).checkout(false, null, null, change) && CoinDispenser.countCoins(denomList,change) >= 0) {
             System.out.println("This much: " + CoinDispenser.countCoins(denomList, change));
             total.add("Change" + CoinDispenser.countCoins(denomList, change) + "PHP");
         }
         else{
             total.add("");
         }
        total.add("Total: " + String.valueOf(vendingList.get(vendingIndex).getCurrentTransaction().getTotalCalories()) + "kcals");

         vendingMenu.updateTransaction(ordered, total);
    }

    public void updateListDropDown(){
         ArrayList<String> vendingNames = new ArrayList<String>();
         for(VendingMachine vending: vendingList){
             vendingNames.add(vending.getName());
         }
         vendingMenu.setVendingDropDown(vendingNames);
    }
    private void resetSlotView(){
         slotViews = new ArrayList<SlotGui>();
    }
    private void createPreSetVendingMachines(){
        vendingList.add(new VendingMachine("Fruit Machine", 8, 10, denomList));
        VendingMachine regular = vendingList.get(0);

        Item apple = new Item("Apple Slices", 104);
        ArrayList<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Slicing apples...", 4));
        messages.add(new Message("Placing apple slices...", 1));
        regular.addSlot(apple, 40, 1, messages);

        Item pineapple = new Item("Pineapple Cubes", 84);
        messages = new ArrayList<Message>();
        messages.add(new Message("Slicing pineapple into cubes...", 4));
        messages.add(new Message("Placing pineapple cubes...", 1));
        regular.addSlot(pineapple, 60, 1, messages);

        Item watermelon = new Item("Watermelon Shavings", 60);
        messages.add(new Message("Shaving watermelon...", 4));
        messages.add(new Message("Placing watermelon shavings...", 1));
        regular.addSlot(watermelon, 30, 1, messages);

        Item orange = new Item("Orange Slices", 94);
        messages.add(new Message("Slicing oranges...", 4));
        messages.add(new Message("Placing orange slices...", 1));
        regular.addSlot(orange, 50, 1, messages);

        Item grape = new Item("Grape Skin", 30);
        messages.add(new Message("Peeling grapes...", 4));
        messages.add(new Message("Placing grape skin...", 1));
        regular.addSlot(grape, 20, 1, messages);

        Item melon = new Item("Melon Cubes", 75);
        messages.add(new Message("Slicing melon into cubes...", 4));
        messages.add(new Message("Placing melon cubes...", 1));
        regular.addSlot(melon, 75, 1, messages);


        //special vending
        ArrayList<Slot> specialSlots = new ArrayList<Slot>();


        messages = new ArrayList<Message>();
        messages.add(new Message("Slicing apples...", 4));
        messages.add(new Message("Placing apple slices...", 1));
        specialSlots.add(new Slot(apple,10, 40, 1, messages));

        messages = new ArrayList<Message>();
        messages.add(new Message("Slicing pineapple into cubes...", 4));
        messages.add(new Message("Placing pineapple cubes...", 1));
        specialSlots.add(new Slot(pineapple,10, 60, 1, messages));

        messages = new ArrayList<Message>();
        messages.add(new Message("Shaving watermelon...", 4));
        messages.add(new Message("Placing watermelon shavings...", 1));
        specialSlots.add(new Slot(watermelon,10, 30, 1, messages));

        messages = new ArrayList<Message>();
        messages.add(new Message("Slicing oranges...", 4));
        messages.add(new Message("Placing orange slices...", 1));
        specialSlots.add(new Slot(orange,10, 50, 1, messages));

        messages = new ArrayList<Message>();
        messages.add(new Message("Peeling grapes...", 4));
        messages.add(new Message("Placing grape skin...", 1));
        specialSlots.add(new Slot(grape,10, 20, 1, messages));

        messages = new ArrayList<Message>();
        messages.add(new Message("Slicing melon into cubes...", 4));
        messages.add(new Message("Placing melon cubes...", 1));
        specialSlots.add(new Slot(melon,10, 70, 1, messages));


        Item pepperoni = new Item("Pepperoni (pizza add-on)", 95);
        messages = new ArrayList<Message>();
        messages.add(new Message("Spreading pepperoni...", 5));
        specialSlots.add(new Slot(pepperoni,10, 35, 0, messages));


        Item olives = new Item("Olives (pizza add-on)", 35);
        messages = new ArrayList<Message>();
        messages.add(new Message("Spreading olives...", 5));
        specialSlots.add(new Slot(olives,10, 25, 0, messages));

        Item sausage = new Item("Sausage (pizza add-on)", 95);
        messages = new ArrayList<Message>();
        messages.add(new Message("Spreading sausage...", 5));
        specialSlots.add(new Slot(sausage,10, 45, 0, messages));

        Item pizza = new Item("Transfrom to Pizza", 500);
        messages = new ArrayList<Message>();
        messages.add(new Message("Pizza Served...", 0));
        messages.add(new Message("Letting pizza cool a bit...", 2));
        messages.add(new Message("Baking Pizza...", 3));
        messages.add(new Message("Topping cheese...", 6));
        messages.add(new Message("Applying tomato sauce...", 7));
        specialSlots.add(new Slot(pizza,10, 45, 3, messages));

         vendingList.add(new SpecialVendingMachine("Fruit Pizza!", 10, 10, denomList, specialSlots));


    }
}
