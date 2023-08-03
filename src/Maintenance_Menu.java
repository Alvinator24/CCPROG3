import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * This class represents the Maintenance Menu of a vending machine.
 * It provides features for adding items, restocking items and denominations, and emptying the Vending Machine.
 */
public class Maintenance_Menu {

    JFrame mainFrame;
    JFrame addItemFrame;
    JLabel addItem_itemNameLabel;
    JLabel addItem_itemPriceLabel;
    JLabel addItem_itemCaloriesLabel;
    JButton addAddItemButton;
    JButton addItemExitButton;
    JTextField addItem_itemNameField;
    JTextField addItem_itemPriceField;
    JTextField addItem_itemCaloriesField;
    JFrame restockItemFrame;
    JComboBox<String> slotDropDown;
    JLabel restockItem_itemQuantity;
    JLabel restockItem_AddLabel;
    JLabel restockItem_newPriceLabel;
    JTextField restockItem_itemQuantityField;
    JTextField restockItem_Price;
    JButton restockItem_ExitButton;
    JButton restockItem_addButton;
    JFrame restockDenomsFrame;
    JComboBox<String> denomsDropDown;
    JButton restockDenoms_addButton;
    JButton restockDenoms_ExitButton;
    JLabel restockDenoms_AddLabel;
    JLabel restockDenoms_denomQuantity;
    JTextField restockDenom_quantityField;
    JFrame emptyMachineFrame;
    JLabel emptySummary;
    JButton addItemButton;
    JButton restockItemButton;
    JButton restockDenomsButton;
    JButton emptyMachineButton;
    JButton exitButton;

    /**
     * This constructs an instance of the Maintenance_Menu class.
     * Initializes the mainFrame and other components for the
     * maintenance menu.
     */
    public Maintenance_Menu() {

        mainFrame = new JFrame();
        addItemFrame = new JFrame();
        addItem_itemNameLabel = new JLabel("Item Name: ");
        addItem_itemPriceLabel = new JLabel("Item Price: ");
        addItem_itemCaloriesLabel = new JLabel("Item Calories");
        addItem_itemNameField = new JTextField(15);
        addItem_itemPriceField = new JTextField(15);
        addItem_itemCaloriesField = new JTextField(15);
        addAddItemButton = new JButton("Add");
        addItemExitButton = new JButton("Exit");

        restockDenomsFrame = new JFrame();
        restockDenoms_addButton = new JButton("Add");
        restockDenoms_denomQuantity = new JLabel();
        restockDenoms_AddLabel = new JLabel("Quantity: ");
        restockDenom_quantityField = new JTextField(10);
        denomsDropDown = new JComboBox<String>();
        restockDenoms_ExitButton = new JButton("Exit");


        emptyMachineFrame = new JFrame();


        restockItemFrame = new JFrame();
        restockItem_addButton = new JButton("Add");
        restockItem_ExitButton = new JButton("Exit");
        restockItem_itemQuantity = new JLabel();
        restockItem_itemQuantityField = new JTextField(15);
        restockItem_Price = new JTextField(15);
        slotDropDown = new JComboBox<String>();

        addItemButton = new JButton("Add Item");
        restockItemButton = new JButton("Restock Items");
        restockDenomsButton = new JButton("Restock Denominations");
        emptyMachineButton = new JButton("Empty Machine");
        restockItem_AddLabel = new JLabel("Add: ");
        restockItem_newPriceLabel = new JLabel("Set Price: ");
        exitButton = new JButton("Exit");



        setupElements();
        // pack();
        // setLocationRelativeTo(null);

    }
    /**
     * Sets the available slots in the vending machine for restocking items.
     *
     * @param slots An array of strings representing the available slots.
     */

    public void setSlotDropDown(String[] slots) {
        restockItemFrame.remove(slotDropDown);
        restockItemFrame.remove(restockItem_itemQuantity);
        ActionListener[] actns = slotDropDown.getActionListeners();
        slotDropDown = new JComboBox<String>(slots);
        for(ActionListener actn: actns){
            slotDropDown.addActionListener(actn);
        }
        setupRestockItem();

    }

    /**
     * Allows other classes to access the index of the slotDropDown.
     *
     * @return Integer value of slotDropDown.
     */
    public int getSlotDropDown() {
        return slotDropDown.getSelectedIndex();
    }

    /**
     * Changes the price of an item used for restocking
     *
     * @param price New price of the item used for restocking.
     */
    public void setRestockItem_Price(double price) {
        restockItem_Price.setText(String.valueOf(price));
    }
    /**
     * Allows other classes to get the price of the
     * restocking item/s.
     *
     * @return String of the restocking item's price
     */
    public String getRestockItem_Price() {
        return restockItem_Price.getText();
    }
    /**
     * Allows other classes to access the item quantity field
     * of restockItem.
     *
     * @return String of the itemQuantityField.
     */
    public String getRestockItem_itemQuantityField() {
        return restockItem_itemQuantityField.getText();
    }
    /**
     * Changes the quantity of items being used for restocking.
     *
     * @param quantity New Integer quanity of items for restocking.
     */
    public void setRestockItem_itemQuantity(int quantity) {
        restockItem_itemQuantity.setText(String.valueOf(quantity) + "pcs");
    }
    /**
     * Starts the process of performing a slotDropDown when the
     * corresponding action is taken.
     *
     * @param actn ActionListener for this method.
     */
    public void setSlotDropDown(ActionListener actn) {
        ActionListener[] actns = slotDropDown.getActionListeners();
        if(actns.length > 1){
            slotDropDown.removeActionListener((ActionListener)Array.get(actns, 0));
        }
        slotDropDown.addActionListener(actn);
    }
    /**
     * Starts the process of adding a button to the setRestock item
     * menu when the corresponding action is taken.
     *
     * @param actn ActionListener for this method.
     */
    public void setRestockItem_addButton(ActionListener actn) {
        ActionListener[] actns = restockItem_addButton.getActionListeners();
        if(actns.length > 1){
            restockItem_addButton.removeActionListener((ActionListener)Array.get(actns, 0));
        }
        restockItem_addButton.addActionListener(actn);
    }
    /**
     * Changes the set of Denominations for the DropDown
     * given a string array of new denoms.
     *
     * @param str set of new denominations as a string array.
     */
    public void setDenomsDropDown(String[] str) {
        restockDenomsFrame.remove(denomsDropDown);
        denomsDropDown = new JComboBox<String>(str);
        setupRestockDenoms();

    }
    /**
     * Changes the set of Denominations for the DropDown
     * given an input from an ActionListener.
     *
     * @param actn ActionListener for this method.
     */
    public void setDenomsDropDown(ActionListener actn) {
        ActionListener[] num = denomsDropDown.getActionListeners();
        if(num.length > 0){
            denomsDropDown.removeActionListener((ActionListener)Array.get(num, 0));
        }
        denomsDropDown.addActionListener(actn);
    }
    /**
     * Allows other classes to access the index of the
     * DenomsDropDown.
     *
     * @return integer index of DenomsDropDown
     */
    public int getDenomsDropDown() {
        return denomsDropDown.getSelectedIndex();
    }
    /**
     * Changes the quantity of Denoms during restocking given a
     * string input
     *
     * @param quantity New quantity of denoms for RestockDenoms.
     */
    public void setRestockDenoms_denomQuantity(String quantity) {
        restockDenoms_denomQuantity.setText("Stored: " + quantity);
    }
    /**
     * Changes the quantity of Denoms during restocking given an
     * ActionListener.
     *
     * @param actn ActionListener for the method.
     */
    public void setRestockDenomsButton(ActionListener actn){
        ActionListener[] num = restockDenomsButton.getActionListeners();
        if(num.length > 1){
            restockDenomsButton.removeActionListener((ActionListener)Array.get(num, 0));
        }
        restockDenomsButton.addActionListener(actn);
    }
    /**
     * Allows other classes to access the quantity field of
     * RestockDenoms.
     *
     * @return The string variable of the quantity field.
    */
    public String getRestockDenom_quantityField() {
        return restockDenom_quantityField.getText();
    }
    /**
     * Sets up the elements for the mainFrame of the Maintenance_Menu.
     */
    public void setupElements(){
        mainFrame.setTitle("Maintenance Features");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);


        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainFrame.add(addItemButton, gbc);

        gbc.gridy = 1;
        mainFrame.add(addItemButton, gbc);

        gbc.gridy = 2;
        mainFrame.add(restockItemButton, gbc);

        gbc.gridy = 3;
        mainFrame.add(restockDenomsButton, gbc);

        gbc.gridy = 4;
        mainFrame.add(emptyMachineButton, gbc);

        gbc.gridy = 5;
        mainFrame.add(exitButton, gbc);

        addItemFrame.setLayout(new MigLayout("wrap 2"));
        restockItemFrame.setLayout(new MigLayout());
        restockDenomsFrame.setLayout(new MigLayout());
        emptyMachineFrame.setLayout(new MigLayout());

        setupButtons();
        setupAddItem();
        setupRestockItem();
        setupRestockDenoms();

    }
    /**
     * Sets up the RestockDenoms menu for Maintenance_Menu.
     */
    private void setupRestockDenoms(){
        restockDenomsFrame.setTitle("Restock Denominations");
        restockDenomsFrame.setLayout(new MigLayout("wrap 2"));
        restockDenomsFrame.add(denomsDropDown);
        restockDenomsFrame.add(restockDenoms_denomQuantity,"wrap");
        restockDenomsFrame.add(restockDenoms_AddLabel);
        restockDenomsFrame.add(restockDenom_quantityField);
        restockDenomsFrame.add(restockDenoms_addButton);
        restockDenomsFrame.add(restockDenoms_ExitButton);



        restockDenomsFrame.pack();
    }
    /**
     * Sets up the AddItem menu for Maintenance_Menu.
     */
    private void setupAddItem(){
        addItemFrame.setTitle("Add Item");
        addItemFrame.add(addItem_itemNameLabel);
        addItemFrame.add(addItem_itemNameField);
        addItemFrame.add(addItem_itemCaloriesLabel);
        addItemFrame.add(addItem_itemCaloriesField);
        addItemFrame.add(addItem_itemPriceLabel);
        addItemFrame.add(addItem_itemPriceField);
        addItemFrame.add(addItemExitButton);
        addItemFrame.add(addAddItemButton, "wrap");

        addItemFrame.pack();
    }
    /**
     * Sets up the RestockItem menu for Maintenance_Menu.
     */
    private void setupRestockItem(){
        restockItemFrame.setTitle("Restock Item");
        restockItemFrame.add(slotDropDown, "span 2");
        restockItemFrame.add(restockItem_itemQuantity, "wrap");
        restockItemFrame.add(restockItem_AddLabel);
        restockItemFrame.add(restockItem_itemQuantityField, "wrap");
        restockItemFrame.add(restockItem_newPriceLabel);
        restockItemFrame.add(restockItem_Price, "wrap");
        restockItemFrame.add(restockItem_addButton);
        restockItemFrame.add(restockItem_ExitButton);

        restockItemFrame.pack();


    }

    /**
     * Displays the empty machine window
     *
     * @param returned information about the denomination counts
     * @param total information about the total amount of money emptied
     */
    public void displayEmpty(ArrayList<String> returned, double total){
        JFrame moneyReturned = new JFrame();
        moneyReturned.setTitle("Money Returned");
        moneyReturned.setLayout(new MigLayout("wrap 2, fillx", "[]15[]"));

        moneyReturned.add(new JLabel("Money from machine"),"wrap");
        moneyReturned.add(new JLabel("Total Revenue: " + String.valueOf(total)), "wrap");


        for(int i = 2; i < returned.size(); ++i){
            if(i % 2 == 0){
                moneyReturned.add(new JLabel(returned.get(i) + " PHP"));
            }
            else{
                moneyReturned.add(new JLabel(returned.get(i)), "align right");
            }
        }


        moneyReturned.pack();
        moneyReturned.setVisible(true);


    }
    /**
     * Displays the Stock of previous transactions based on the items
     * that are sold.
     *
     * @param sold      List of all items sold from previous transacitons.
     * @param revenue   Overall revenue from past transactions.
     * @param calories  Overall calories from items of past transactions.
     */
    public void displayStock(ArrayList<String> sold, double revenue, double calories){
        JFrame pastTransacts = new JFrame();
        pastTransacts.setTitle("Stats since last re-stock");
        pastTransacts.setLayout(new MigLayout("wrap 2, fillx", "[]15[]"));

        pastTransacts.add(new JLabel("Stats since last re-stock"),"wrap");
        pastTransacts.add(new JLabel("Total Revenue: " + String.valueOf(revenue)));
        pastTransacts.add(new JLabel("Total Calories: " + String.valueOf(calories)), "align right");

        for(int i = 2; i < sold.size(); ++i){
            if(i % 2 == 0){
                pastTransacts.add(new JLabel(sold.get(i)));
            }
            else{
                pastTransacts.add(new JLabel(sold.get(i)), "align right");
            }
        }


        pastTransacts.pack();
        pastTransacts.setVisible(true);


    }
    /**
     * Sets up the buttons and ActionListeners for the various menus in Maintenance_Menu.
     */
    private void setupButtons(){
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(1);
            }
        });

        restockItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(2);
            }
        });

        restockDenomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(3);
            }
        });

        emptyMachineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(4);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(5);
            }
        });
        addItemExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(0);
                resetFields();
            }
        });
        addAddItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(0);
                resetFields();
            }
        });

        restockItem_ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(0);
                resetFields();
            }
        });

        restockItem_addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(0);
                resetFields();
            }
        });

        restockDenoms_addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(0);
                resetFields();
            }
        });
        restockDenoms_ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(0);
                resetFields();
            }
        });

    }

    /**
     * Sets up the current menu being opened to be visible
     * based on the menu selected.
     *
     * @param menu Integer value of menu being selected.
     */
    public void setMenu(int menu){

        switch(menu){
            case 0->{
                mainFrame.setVisible(true);
                addItemFrame.setVisible(false);
                restockItemFrame.setVisible(false);
                restockDenomsFrame.setVisible(false);
                emptyMachineFrame.setVisible(false);
            }

            case 1->{
                mainFrame.setVisible(false);
                addItemFrame.setVisible(true);
            }

            case 2->{
                mainFrame.setVisible(false);
                restockItemFrame.setVisible(true);
            }

            case 3->{
                mainFrame.setVisible(false);
                restockDenomsFrame.setVisible(true);
            }

            case 4->{
                mainFrame.setVisible(false);
                emptyMachineButton.setVisible(false);
            }

            case 5->{
                mainFrame.setVisible(false);
            }
        }
    }
    /**
     * Adds an AddItemButton and their corresponding ActionListeners.
     *
     * @param actn The ActionListener for this method.
     */
    public void setAddAddItemButton(ActionListener actn) {
        ActionListener[] actnlstnrs;
        actnlstnrs = addAddItemButton.getActionListeners();
        if(actnlstnrs.length == 2){
            addAddItemButton.removeActionListener((ActionListener)Array.get(actnlstnrs, 0));
        }
        addAddItemButton.addActionListener(actn);
    }
    /**
     * Sets up an exit button and its ActionListener.
     *
     * @param actn The ActionListener for this method.
     */
    public void setExitButton(ActionListener actn) {
        exitButton.addActionListener(actn);
    }
    /**
     * Allows other classes to access the itemNameField.
     *
     * @return String variable of the itemNameField.
     */
    public String getAddItem_itemNameField() {
        return addItem_itemNameField.getText();
    }
    /**
     * Allows other classes to access the itemCaloriesField.
     *
     * @return String variable of the itemCaloriesField.
     */
    public String getAddItem_itemCaloriesField() {
        return addItem_itemCaloriesField.getText();
    }
    /**
     * Allows other classes to access the itemPriceField.
     *
     * @return String variable of the itemPriceField.
     */
    public String getAddItem_itemPriceField() {
        return addItem_itemPriceField.getText();
    }

    /**
     * Sets the action listener for the add button in
     * the Restock Denominations menu
     * @param actn the actionlistener to be added
     */
    public void setRestockDenoms_addButton(ActionListener actn) {
        ActionListener[] actnlstnrs;
        actnlstnrs = restockDenoms_addButton.getActionListeners();
        if(actnlstnrs.length == 2){
            restockDenoms_addButton.removeActionListener((ActionListener)Array.get(actnlstnrs, 0));
        }
        restockDenoms_addButton.addActionListener(actn);
    }
    /**
     * Sets up buttons and their ActionListeners for the RestockDenoms
     * menu.
     *
     * @param actn The ActionListener for this method.
     */
    public void setEmptyMachineButton(ActionListener actn) {
        ActionListener[] actnlstnrs;
        actnlstnrs = emptyMachineButton.getActionListeners();
        if(actnlstnrs.length == 1){
            emptyMachineButton.removeActionListener((ActionListener)Array.get(actnlstnrs, 0));
        }
        emptyMachineButton.addActionListener(actn);
    }
    /**
     * Clears and Resets all itemFields in Maintenance_Menu
     */
    public void resetFields(){
        addItem_itemCaloriesField.setText("");
        addItem_itemNameField.setText("");
        addItem_itemPriceField.setText("");
        restockItem_itemQuantityField.setText("");
    }
}

