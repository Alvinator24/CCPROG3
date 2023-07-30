import javax.swing.*;
import java.awt.*;

public class VendingMaintenance {

    JFrame frame = new JFrame();
    JFrame frame2 = new JFrame();
    JFrame frame3 = new JFrame();
    JFrame frame4 = new JFrame();
    JFrame frame5 = new JFrame();
    JPanel panel = new JPanel();
    JButton addItem = new JButton("Add Item");
    JButton restockItems = new JButton("Restock Items");
    JButton restockDenoms = new JButton("Restock Denoms");
    JButton emptyMachine = new JButton("Empty Machine");
    JButton exitButton = new JButton("Exit");

    public VendingMaintenance() {

        frame.setTitle("Maintenance Features");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(addItem, gbc);

        gbc.gridy = 1;
        frame.add(addItem, gbc);

        gbc.gridy = 2;
        frame.add(restockItems, gbc);

        gbc.gridy = 3;
        frame.add(restockDenoms, gbc);

        gbc.gridy = 4;
        frame.add(emptyMachine, gbc);

        gbc.gridy = 5;
        frame.add(exitButton, gbc);

        frame2.setVisible(true);
        frame3.setVisible(true);
        frame4.setVisible(true);
        frame5.setVisible(true);

        // pack();
        // setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        new VendingMaintenance();
    }

}