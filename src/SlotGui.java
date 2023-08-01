import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SlotGui extends JPanel {
    private JLabel name;
    private JLabel price;
    private JLabel calories;
    private JLabel quantity;
    private JLabel available;

    private JButton increment;
    private JButton decrement;
    private GridBagConstraints gbc;

    SlotGui(String name, double price, double calories, int available){
        this.name = new JLabel(name);
        this.price = new JLabel();
        setPrice(price);
        this.calories = new JLabel(String.valueOf(calories) + "kcals");
        this.quantity = new JLabel();
        setQuantity(0, available);
        increment = new JButton("+");
        decrement = new JButton("-");
        //this.setSize(50,75);
        this.setLayout(new MigLayout());
        this.setBorder(BorderFactory.createEtchedBorder());


        setPreferredSize( new Dimension(200,130));

        setupElements();
    }

    private void setupElements(){

        add(quantity, "cell 1 0, align center");


        add(increment,"cell 2 0, align left");

        add(decrement, "cell 0 0, align right");


        add(name, "cell 0 1, span 3, align center");


        add(price, "cell 1 2, align center");

        add(calories, "cell 1 3, align center");

        this.setVisible(true);
    }
    public void setPrice(double price){
        this.price.setText(String.valueOf(price) + "PHP");
    }

    public void setQuantity(int quantity, int available){
        this.quantity.setText(String.valueOf(quantity) + "/" + String.valueOf(available));
    }

    public void setIncrement(ActionListener actn) {
        increment.addActionListener(actn);
    }

    public void setDecrement(ActionListener actn) {
        decrement.addActionListener(actn);
    }
}
