import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public int getSlotDropDown() {
        return slotDropDown.getSelectedIndex();
    }

    public void setRestockItem_Price(double price) {
        restockItem_Price.setText(String.valueOf(price));
    }

    public String getRestockItem_Price() {
        return restockItem_Price.getText();
    }

    public String getRestockItem_itemQuantityField() {
        return restockItem_itemQuantityField.getText();
    }

    public void setRestockItem_itemQuantity(int quantity) {
        restockItem_itemQuantity.setText(String.valueOf(quantity) + "pcs");
    }

    public void setSlotDropDown(ActionListener actn) {
        ActionListener[] actns = slotDropDown.getActionListeners();
        if(actns.length > 1){
            slotDropDown.removeActionListener((ActionListener)Array.get(actns, 0));
        }
        slotDropDown.addActionListener(actn);
    }

    public void setRestockItem_addButton(ActionListener actn) {
        ActionListener[] actns = restockItem_addButton.getActionListeners();
        if(actns.length > 1){
            restockItem_addButton.removeActionListener((ActionListener)Array.get(actns, 0));
        }
        restockItem_addButton.addActionListener(actn);
    }

    public void setDenomsDropDown(String[] str) {
        restockDenomsFrame.remove(denomsDropDown);
        denomsDropDown = new JComboBox<String>(str);
        setupRestockDenoms();

    }

    public void setDenomsDropDown(ActionListener actn) {
        ActionListener[] num = denomsDropDown.getActionListeners();
        if(num.length > 0){
            denomsDropDown.removeActionListener((ActionListener)Array.get(num, 0));
        }
        denomsDropDown.addActionListener(actn);
    }

    public int getDenomsDropDown() {
        return denomsDropDown.getSelectedIndex();
    }

    public void setRestockDenoms_denomQuantity(String quantity) {
        restockDenoms_denomQuantity.setText("Stored: " + quantity);
    }

    public void setRestockDenomsButton(ActionListener actn){
        ActionListener[] num = restockDenomsButton.getActionListeners();
        if(num.length > 1){
            restockDenomsButton.removeActionListener((ActionListener)Array.get(num, 0));
        }
        restockDenomsButton.addActionListener(actn);
    }

    public String getRestockDenom_quantityField() {
        return restockDenom_quantityField.getText();
    }

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

    public void displayStock(ArrayList<String> sold, double revenue, double calories){
        JFrame pastTransacts = new JFrame();
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

    public void setAddAddItemButton(ActionListener actn) {
        ActionListener[] actnlstnrs;
        actnlstnrs = addAddItemButton.getActionListeners();
        if(actnlstnrs.length == 2){
            addAddItemButton.removeActionListener((ActionListener)Array.get(actnlstnrs, 0));
        }
        addAddItemButton.addActionListener(actn);
    }

    public void setExitButton(ActionListener actn) {
        exitButton.addActionListener(actn);
    }

    public String getAddItem_itemNameField() {
        return addItem_itemNameField.getText();
    }

    public String getAddItem_itemCaloriesField() {
        return addItem_itemCaloriesField.getText();
    }

    public String getAddItem_itemPriceField() {
        return addItem_itemPriceField.getText();
    }

    public void setRestockDenoms_addButton(ActionListener actn) {
        ActionListener[] actnlstnrs;
        actnlstnrs = restockDenoms_addButton.getActionListeners();
        if(actnlstnrs.length == 2){
            restockDenoms_addButton.removeActionListener((ActionListener)Array.get(actnlstnrs, 0));
        }
        restockDenoms_addButton.addActionListener(actn);
    }


    public void resetFields(){
        addItem_itemCaloriesField.setText("");
        addItem_itemNameField.setText("");
        addItem_itemPriceField.setText("");
        restockItem_itemQuantityField.setText("");
    }
}

