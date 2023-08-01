// import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Driver {


    
    // we only need to store the data (vending machines), so ArrayList is optimal
    // if we needed to manipulate the data, LinkedLists are better (internally doubly linked lists)

//    public static void main(String[] args) {
//        //pre-defined objects (to make demo easy to explain)
//        ArrayList<Denomination> denoms = new ArrayList<Denomination>();
//        denoms.add(new Denomination(.01));
//        denoms.add(new Denomination(.05));
//        denoms.add(new Denomination(.1));
//        denoms.add(new Denomination(.25));
//        denoms.add(new Denomination(1));
//        denoms.add(new Denomination(5));
//        denoms.add(new Denomination(10));
//        denoms.add(new Denomination(20));
//        denoms.add(new Denomination(50));
//        denoms.add(new Denomination(100));
//        denoms.add(new Denomination(200));
//        denoms.add(new Denomination(500));
//        denoms.add(new Denomination(1000));
//
//        ArrayList<VendingMachine> vendingList = new ArrayList<VendingMachine>();
////        View_MainMenu guiTest = new View_MainMenu();
////        guiTest.setupViewElements();

////
////        guiTest.setButton_CreateVending(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                String name = "yes";
////                int slotLimit = 0, itemLimit = 0;
////
////                try{
////                    name = guiTest.getTextField_VendingName();
////                    slotLimit = Integer.parseInt(guiTest.getTextField_VendingSlotLimit());
////                    itemLimit = Integer.parseInt(guiTest.getTextField_VendingItemLimit());
////                    if(slotLimit >= 8 && itemLimit >= 10){
////                        vendingList.add(new VendingMachine(name, slotLimit,itemLimit,denoms));
////
////                        guiTest.setMenu(View_MainMenu.MAIN_MENU);
////                        guiTest.setLabel_Errors("Vending Machine Created!");
////                        guiTest.resetCreateVendingLabels();
////
////                    }
////                    else{
////                        guiTest.setLabel_Errors("Slot limit and item limit must at least be 8 and 10!");
////                    }
////
////                }catch(NumberFormatException error){
////                    guiTest.setLabel_Errors("Invalid Input!");
////                }
////
////
////            }
////        });
//
//
//        VendingMachine testy = new VendingMachine("Fruit Machine", 8, 10, denoms);
//
//        VendingMachine_Menu testdisplay = new VendingMachine_Menu(13,denoms);
//        testdisplay.setupElements();
//
//
//        SlotGui slotTest = new SlotGui("Apple", 50.21, 35 ,10);
//        slotTest.setupElements();
//        testdisplay.addSlot(slotTest);
//
//        slotTest = new SlotGui("Apple", 50.21, 35 ,10);
//        slotTest.setupElements();
//        testdisplay.addSlot(slotTest);
//
//        slotTest = new SlotGui("Apple", 50.21, 35 ,10);
//        slotTest.setupElements();
//        testdisplay.addSlot(slotTest);
//
//        slotTest = new SlotGui("Apple", 50.21, 35 ,10);
//        slotTest.setupElements();
//        testdisplay.addSlot(slotTest);
//
//
//        testdisplay.setInsertCoin(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(testdisplay.getSelectedDenom());
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }

    public static void main(String[] args){

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

        Factory vendingFactory = new Factory(denoms);


    }




}