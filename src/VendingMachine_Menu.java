package com.example;

import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class creates the GUI of the Vending Machine tester application.
 */
public class VendingMachine_Menu extends JFrame {

    GridBagConstraints gbc;
    JPanel slotPanel;
    JPanel transactionPanel;
    JPanel transactionBought;
    JScrollPane transactionScroll;
    JPanel transactionSummary;
    JScrollPane slotScroll;
    JLabel machineName;

    JComboBox denomDropDown;
    JComboBox vendingDropDown;
    JButton insertCoin;
    JButton exitButton;
    JButton checkoutButton;
    JButton resetButton;
    JButton maintenance;

    /**
     * Constructs a new VendingMachine_Menu instance with the specified 
     * list of denominations.
     *
     * @param denomList The list of denominations to populate the 
     *                  denominations drop-down menu.
     */
    VendingMachine_Menu(ArrayList<Denomination> denomList){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new MigLayout());
        //setSize(1300,750);
        //this.setPreferredSize(new Dimension(1400, 850));


        slotPanel = new JPanel();
        insertCoin = new JButton("Insert");
        exitButton = new JButton("Exit");
        checkoutButton = new JButton("Checkout");
        resetButton = new JButton("Clear Transaction");
        maintenance = new JButton("Maintenance");
        machineName = new JLabel();

       ArrayList<String> denomsList = new ArrayList<String>();
        for(Denomination denom : denomList){
            denomsList.add(String.valueOf(denom.getValue()));
        }
        String denomArr[] = denomsList.toArray(new String[denomList.size()]);

        denomDropDown = new JComboBox(denomArr);
        vendingDropDown = new JComboBox();

        setupElements();

    }

    /**
     * Sets up the GUI elements and layout for the vending machine menu.
     */
    private void setupElements(){

        createNewTransaction();
        slotPanel.setLayout(new MigLayout("Wrap 3"));
        slotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        slotPanel.setSize(700, 600);
        slotPanel.setVisible(true);

        slotScroll = new JScrollPane(slotPanel);
        slotScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        slotScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(vendingDropDown);
        add(exitButton);
        add(transactionPanel, "cell 5 0, span 1 3, wrap");
        add(slotScroll, "wrap, span 5");
        add(denomDropDown);
        add(insertCoin);
        add(checkoutButton);
        add(resetButton);
        add(maintenance);

        //setTransaction(null);

    }

    /**
     * Removes all the elements from the vending machine menu.
     */
    public void removeElements(){
        remove(vendingDropDown);
        remove(exitButton);
        remove(transactionPanel);
        slotScroll.remove(slotPanel);
        remove(slotScroll);
        remove(denomDropDown);
        remove(insertCoin);
        remove(checkoutButton);
        remove(resetButton);
        remove(maintenance);
    }

    /**
     * Refreshes the GUI elements in the vending machine menu.
     */
    public void refreshElements(){
        //setVisible(false);
        add(vendingDropDown);
        add(exitButton);
        add(transactionPanel, "cell 5 0, span 1 3, wrap");
        slotPanel.setVisible(false);
        slotScroll.setVisible(false);
        add(slotScroll, "wrap, span 5");
        slotPanel.setVisible(true);
        slotScroll.setPreferredSize(new Dimension(800,650));
        slotScroll.setVisible(true);
        add(denomDropDown);
        add(insertCoin);
        add(checkoutButton);
        add(resetButton);
        add(maintenance);
        pack();

        //SwingUtilities.updateComponentTreeUI(this);
//        setVisible(false);
//        setVisible(true);
    }

    /**
     * Creates a new transaction panel with the appropriate elements 
     * and layout.
     */
    public void createNewTransaction(){
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new MigLayout());
        transactionPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        transactionPanel.setPreferredSize(new Dimension(450,600));

        machineName.setFont(new Font("SansSerif", Font.BOLD,25));
        machineName.setHorizontalAlignment(SwingConstants.CENTER);
        transactionPanel.add(machineName, "dock north, align center, push");

        transactionSummary = new JPanel(new MigLayout(
                "wrap 2","[][]"
        ));
        transactionSummary.setPreferredSize(new Dimension(400,100));
        transactionSummary.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        transactionSummary.setVisible(true);

        transactionBought = new JPanel(new MigLayout(
                "wrap 2", "[][]"
        ));
        transactionScroll = new JScrollPane(transactionBought);
        transactionScroll.setPreferredSize(new Dimension (450, 500));
        transactionScroll.setVisible(true);

        transactionPanel.add(transactionScroll);
        transactionPanel.add(transactionSummary, "dock south,push");
    }

    /**
     * Updates the transaction panel with the purchased items 
     * and transaction totals.
     *
     * @param purchased The list of purchased items to be displayed.
     * @param totals    The list of transaction totals to be displayed.
     */
    public void updateTransaction(ArrayList<String> purchased, ArrayList<String> totals){
        transactionPanel.remove(transactionScroll);

        transactionBought = new JPanel(new MigLayout(
                "wrap 2, fillx", "[][]"
        ));

        for(int i = 0; i < purchased.size(); ++i){
            JLabel toadd = new JLabel(purchased.get(i));
            if(i % 2 == 0){
                transactionBought.add(toadd);
            }
            else{
                transactionBought.add(toadd, "gapleft push");
            }
        }

        transactionScroll = new JScrollPane(transactionBought);
        transactionScroll.setPreferredSize(new Dimension (450, 500));

        transactionScroll.setVisible(true);
        transactionPanel.add(transactionScroll);

        transactionPanel.remove(transactionSummary);
        transactionSummary = new JPanel(new MigLayout(
                "wrap 2, fillx","[][]"
        ));
        transactionSummary.setPreferredSize(new Dimension(400,100));
        transactionSummary.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for(int i = 0; i < totals.size(); ++i){
            JLabel toadd = new JLabel(totals.get(i));
            if(i % 2 == 0){
                transactionSummary.add(toadd);
            }
            else{
                transactionSummary.add(toadd, "align right");
            }
        }

        transactionSummary.setVisible(true);
        transactionPanel.add(transactionSummary, "dock south,push");
        SwingUtilities.updateComponentTreeUI(this);
        //setVisible(false);
        //setVisible(true);
    }

    /**
     * Adds a slot panel to the vending machine menu.
     *
     * @param slot  The JPanel representing the slot to be added.
     */
    public void addSlot(JPanel slot){
        slotPanel.add(slot);
    }

    /**
     * Refreshes the slot panel in the vending machine menu.
     */
    public void refreshSlotPanel(){
        removeElements();
        slotPanel = new JPanel();

        slotPanel.setLayout(new MigLayout("Wrap 3"));
        slotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        slotPanel.setSize(850, 600);
        slotPanel.setVisible(true);

        slotScroll = new JScrollPane(slotPanel);
        slotScroll.setSize(830,520);
        slotScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        slotScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        refreshElements();



    }

    /**
     * Retrieves the selected denomination from the denominations 
     * drop-down menu.
     *
     * @return  The selected denomination as a double value.
     */
    public double getSelectedDenom(){
        return Double.parseDouble((String)denomDropDown.getSelectedItem());
    }

    //replaced by setVending?
    public void addVending(ArrayList<VendingMachine> vendingList){
        ArrayList<String> vendingMachines = new ArrayList<String>();
        for(VendingMachine vending: vendingList){
            vendingMachines.add(vending.getName());
        }

        String[] vendingArr = vendingMachines.toArray(new String[vendingMachines.size()]);
        ActionListener[] actns = vendingDropDown.getActionListeners();
        vendingDropDown = new JComboBox<>(vendingArr);
        for(ActionListener actn: actns){
            System.out.println("this");
            vendingDropDown.addActionListener(actn);
        }
        refreshElements();
    }

    /**
     * Sets the ActionListener for the "Insert" coin button.
     *
     * @param actn  The ActionListener to be set for the button.
     */
    public void setInsertCoin(ActionListener actn) {
        ActionListener[] num = insertCoin.getActionListeners();
        if(num.length > 0){
            insertCoin.removeActionListener((ActionListener)Array.get(num, 0));
        }
        insertCoin.addActionListener(actn);


    }

    /**
     * Sets the ActionListener for the "Exit" button.
     *
     * @param actn  The ActionListener to be set for the button.
     */
    public void setExitButton(ActionListener actn) {
        exitButton.addActionListener(actn);
    }

    /**
     * Sets the ActionListener for the "Checkout" button.
     *
     * @param actn  The ActionListener to be set for the button.
     */
    public void setCheckoutButton(ActionListener actn) {
        checkoutButton.addActionListener(actn);
    }

    /**
     * Sets the ActionListener for the "Clear Transaction" button.
     *
     * @param actn  The ActionListener to be set for the button.
     */
    public void setResetButton(ActionListener actn) {
        resetButton.addActionListener(actn);
    }

    /**
     * Retrieves the JComboBox representing the vending machines 
     * drop-down menu.
     *
     * @return  The JComboBox representing the vending machines 
     *          drop-down menu.
     */
    public JComboBox getVendingDropDown() {
        return vendingDropDown;
    }

    /**
     * Sets the vending machines drop-down menu with the provided 
     * list of vending names.
     *
     * @param vendingNames  The list of vending machine names to be 
     *                      displayed in the drop-down menu.
     */
    public void setVendingDropDown(ArrayList<String> vendingNames){
        removeElements();
        String[] strNames = vendingNames.toArray(new String[vendingNames.size()]);
        for(String str: strNames){
            System.out.println(str);
        }
        ActionListener[] actns = vendingDropDown.getActionListeners();
        vendingDropDown = new JComboBox<>(strNames);
        for(ActionListener actn: actns){
            System.out.println("this");
            vendingDropDown.addActionListener(actn);
        }

        refreshElements();
    }

    /**
     * Sets the ActionListener for the vending machines drop-down menu.
     *
     * @param actn  The ActionListener to be set for the drop-down menu.
     */
    public void setVendingDropDown(ActionListener actn){
        System.out.println("that");
        vendingDropDown.addActionListener(actn);
    }

//    public void setTransaction(String transaction) {
//        if(transaction == null){
//            this.transaction.setVerticalAlignment(JLabel.CENTER);
//            this.transaction.setHorizontalAlignment(JLabel.CENTER);
//            this.transaction.setText("ADD ITEMS TO START TRANSACTION");
//        }
//        else{
//            this.transaction.setVerticalAlignment(JLabel.NORTH);
//            this.transaction.setHorizontalAlignment(JLabel.WEST);
//            this.transaction.setText(transaction);
//        }
//
//    }

    /**
     * Sets the name of the vending machine to be displayed in 
     * the transaction panel.
     *
     * @param machineName   The name of the vending machine.
     */
    public void setMachineName(String machineName) {
        this.machineName.setText(machineName);
    }

    /**
     * Sets the visibility of the vending machine menu based on 
     * the provided mode.
     *
     * @param mode The mode (0 to hide, 1 to show).
     */
    public void setMenu(int mode){
        switch(mode){
            case 0 :{
                setVisible(false);
            }
            case 1 :{
                setVisible(true);
            }
        }
    }
}
