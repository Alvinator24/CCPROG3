import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class VendingView {

    public void CreateVM() {
        JFrame createVMframe = new JFrame();
        createVMframe.setSize(500, 500);
        createVMframe.setTitle("Create VM");

        JButton createRVM = new JButton();
        createRVM.setText("Create RVM");

        JButton createSVM = new JButton();
        createSVM.setText("Create SVM");

        createVMframe.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        createRVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // jButton2ActionPerformed(evt);
                // LinkedList<VendingMachine> vendingLinkedList= new LinkedList();
                // vendingLinkedList.add(new VendingMachine("Jabol", 5, 5, denoms));
                // vendingLinkedList.add(new VendingMachine("Bean", 8, 4, denoms));
                // vendingLinkedList.add(new VendingMachine("Kain Lugi", 3, 2, denoms));
                JOptionPane.showMessageDialog(null, "RVM added!");
            }
        });

        createSVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // jButton2ActionPerformed(evt);
            }
        });

        createVMframe.add(createRVM, gbc);
        gbc.gridy = 1;
        createVMframe.add(createSVM, gbc);

        createVMframe.setVisible(true); // Add this line to show the frame
    }

    public VendingView() {

        JFrame frame = new JFrame();
        JLabel jLabel1 = new JLabel();
        JButton createVM = new JButton();
        JButton testVM = new JButton();

        frame.setSize(500, 500);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1.setText("Vending Machine Factory");

        createVM.setText("Create VM");
        createVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // jButton1ActionPerformed(evt)
                VendingView vendingView = new VendingView();
                vendingView.CreateVM();
            }
        });

        testVM.setText("Test VM");
        testVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // jButton2ActionPerformed(evt);
            }
        });

        createVM.setVisible(true);
        testVM.setVisible(true);

        frame.add(createVM, gbc);
        gbc.gridy = 1;
        frame.add(testVM, gbc);

        frame.setVisible(true);

    }

    public static void main(String[] args) {


        new VendingView();


        // grid layout
    }

}