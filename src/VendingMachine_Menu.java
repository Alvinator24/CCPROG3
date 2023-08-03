import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class VendingMachine_Menu extends JFrame {

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
    VendingMachine_Menu(ArrayList<Denomination> denomList){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new MigLayout());


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


    }

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


    public void addSlot(JPanel slot){
        slotPanel.add(slot);
    }
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
     *
     * @param ordered
     * @param summary
     * @param change
     * @param messages
     */
    public void showReceipt(ArrayList<String> ordered, ArrayList<Double> summary, ArrayList<String> change, ArrayList<String> messages){
        JFrame receipt = new JFrame();
        JPanel orders = new JPanel(new MigLayout("wrap 2, fillx", "[]20[]"));
        JScrollPane ordersScroll = new JScrollPane(orders);
        JPanel changePanel = new JPanel(new MigLayout("fillx, wrap 2"));
        JScrollPane changeScroll = new JScrollPane(changePanel);
        JLabel messageLabel = new JLabel();
        receipt.setLayout(new MigLayout("fillx", "[][][]30[]"));

        for(int i = 0; i < ordered.size(); ++i){
            if(i % 2 == 0){
                orders.add(new JLabel("-" + ordered.get(i)));
            }
            else{
                orders.add(new JLabel(ordered.get(i)), "align right");
            }
        }

        for(int i = 0 ; i < change.size(); ++i){
            if(i % 2 == 0){
                changePanel.add(new JLabel("PHP " + change.get(i)));
            }
            else{
                changePanel.add(new JLabel("| Count: " + change.get(i)), "align right");
            }
        }


        receipt.add(new JLabel("Total: " + String.valueOf(summary.get(0)) + " PHP"),"wrap");
        receipt.add(new JLabel("Received: " + String.valueOf(summary.get(1)) + " PHP"),"wrap");
        receipt.add(new JLabel("Total Calories: " + String.valueOf(summary.get(3))),"wrap");
        receipt.add(new JLabel("Change: " + String.valueOf(summary.get(2)) + " PHP"), "cell 1 3");

        receipt.add(ordersScroll, "cell 0 3");
        receipt.add(changeScroll, "cell 1 3, wrap");
        //receipt.add(messageLabel);



        Timer updateLabel = new Timer(1000, new ActionListener() {
            int counter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter < messages.size()){
                    System.out.println(messages.get(counter));
                    receipt.add(new JLabel(messages.get(counter)), "wrap");
                    ++counter;
                    SwingUtilities.updateComponentTreeUI(receipt);
                    receipt.pack();
                }
            }
        });

        updateLabel.start();




        receipt.pack();
        receipt.setVisible(true);

    }

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
            vendingDropDown.addActionListener(actn);
        }
        refreshElements();
    }

    public void setInsertCoin(ActionListener actn) {
        ActionListener[] num = insertCoin.getActionListeners();
        if(num.length > 0){
            insertCoin.removeActionListener((ActionListener)Array.get(num, 0));
        }
        insertCoin.addActionListener(actn);


    }

    public void setExitButton(ActionListener actn) {
        exitButton.addActionListener(actn);
    }

    public void setCheckoutButton(ActionListener actn) {
        ActionListener[] num = checkoutButton.getActionListeners();
        if(num.length > 0){
            checkoutButton.removeActionListener((ActionListener)Array.get(num, 0));
        }
        checkoutButton.addActionListener(actn);
    }

    public void setCheckoutButton(boolean state){
        checkoutButton.setEnabled(state);
    }

    public void setResetButton(ActionListener actn) {
        resetButton.addActionListener(actn);
    }

    public JComboBox getVendingDropDown() {
        return vendingDropDown;
    }

    public void setVendingDropDown(ArrayList<String> vendingNames){
        removeElements();
        String[] strNames = vendingNames.toArray(new String[vendingNames.size()]);

        ActionListener[] actns = vendingDropDown.getActionListeners();
        vendingDropDown = new JComboBox<>(strNames);
        for(ActionListener actn: actns){
            vendingDropDown.addActionListener(actn);
        }

        refreshElements();
    }
    public void setVendingDropDown(ActionListener actn){
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

    public void setMachineName(String machineName) {
        this.machineName.setText(machineName);
    }

    public void setMaintenance(ActionListener actn) {
        maintenance.addActionListener(actn);
    }

    public void setMenu(int mode){
        switch(mode){
            case 0 ->{
                setVisible(false);
            }
            case 1 ->{
                setVisible(true);
            }
        }
    }
}
