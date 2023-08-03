import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Creates a custom Swing component representing a slot in a vending machine.
 */
public class SlotGui extends JPanel {
    private final JLabel name;
    private final JLabel price;
    private final JLabel calories;
    private final JLabel quantity;
    private JLabel available;

    private final JButton increment;
    private final JButton decrement;

    /**
     * Constructs a new SlotGui class to display to the user with the ff.
     * parameters.
     *
     * @param name      The name of the item in the slot.
     * @param price     The price of the item in the slot.
     * @param calories  The calories of the item in the slot.
     * @param available The initial quantity of the item available in the slot.
     */
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

    /**
     * Sets up the components of the GUI with resources from the
     * MigLayout swing library.
     */
    private void setupElements(){

        add(quantity, "cell 1 0, align center");


        add(increment,"cell 2 0, align left");

        add(decrement, "cell 0 0, align right");


        add(name, "cell 0 1, span 3, align center");


        add(price, "cell 1 2, align center");

        add(calories, "cell 1 3, align center");

        this.setVisible(true);
    }

    /**
     * Allows the user to set the price of the items in this slot
     * from the GUI.
     *
     * @param price The new price to set for the items.
     */
    public void setPrice(double price){
        this.price.setText(String.valueOf(price) + "PHP");
    }

    /**
     * Allows the user to set the quantity of the item in the slot
     * and the available quantity of the items from the GUI.
     *
     * @param quantity  The quantity of the item in the slot.
     * @param available The total available quantity of the item.
     */
    public void setQuantity(int quantity, int available){
        this.quantity.setText(String.valueOf(quantity) + "/" + String.valueOf(available));
    }

    /**
     * Sets the ActionListener for the increment button.
     *
     * @param actn The ActionListener to set for the increment button.
     */
    public void setIncrement(ActionListener actn) {
        increment.addActionListener(actn);
    }

    /**
     * Sets the ActionListener for the decrement button.
     *
     * @param actn The ActionListener to set for the decrement button.
     */
    public void setDecrement(ActionListener actn) {
        decrement.addActionListener(actn);
    }
}
