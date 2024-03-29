import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the main menu and vending machine creation view in a vending machine GUI.
 */
public class View_MainMenu {

    public static final int MAIN_MENU = 0;
    public static final int CREATE_MENU = 1;
    //Main Menu
    private final JFrame frame_MainMenu;
    private final JLabel label_Title;
    private final JButton button_Test;
    private final JButton button_Create;
    private GridBagConstraints gbc;


    //Create Menu
    private final JFrame frame_CreateVending;
    private final JLabel label_VendingName;
    private final JLabel label_VendingSlotLimit;
    private final JLabel label_VendingItemLimit;
    private final JLabel label_Errors;
    private final JTextField textField_VendingName;
    private final JTextField textField_VendingSlotLimit;
    private final JTextField textField_VendingItemLimit;
    private final JButton button_CreateVending;
    private final JButton button_ExitVending;

    /**
     * Constructs a new View_MainMenu instance, initializes the main menu,
     * and creates vending menu components.
     */
    View_MainMenu(){
       frame_MainMenu = new JFrame("Main Menu");
       label_Title = new JLabel("Vending Machine Tester");
       button_Test = new JButton("Test Vending Machine");
       button_Create = new JButton("Create Vending Machine");

       frame_CreateVending = new JFrame("Vending Machine Creation");
       label_VendingName = new JLabel("Name: ");
       label_VendingSlotLimit = new JLabel("Slot limit: ");
       label_VendingItemLimit = new JLabel("Item Limit: ");
       label_Errors = new JLabel();
       textField_VendingName = new JTextField();
       textField_VendingSlotLimit = new JTextField("8");
       textField_VendingItemLimit = new JTextField("10");
       button_CreateVending = new JButton("Create");
       button_ExitVending = new JButton("Exit");

       gbc = new GridBagConstraints();
    }

    /**
     * Calls the methods that set up the view elements of the main menu
     * and the vending machine creation menu.
     */
    public void setupViewElements(){
        setup_MainMenu();
        setup_CreateVendingMenu();

    }
    /**
     * Sets up the view elements of the vending machine creation menu.
     */
    private void setup_CreateVendingMenu(){
        frame_CreateVending.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_CreateVending.setLayout(new GridBagLayout());
        frame_CreateVending.setSize(500,410);

        //text Labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0,0, 0, 0);
        frame_CreateVending.add(label_VendingName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0, 0, 0);
        frame_CreateVending.add(label_VendingSlotLimit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,0, 0, 0);
        frame_CreateVending.add(label_VendingItemLimit, gbc);



        //text fields
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0, 0, 0);
        textField_VendingName.setColumns(10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame_CreateVending.add(textField_VendingName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0, 0, 0);
        textField_VendingSlotLimit.setColumns(5);
        frame_CreateVending.add(textField_VendingSlotLimit, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,0, 0, 0);
        textField_VendingItemLimit.setColumns(5);
        frame_CreateVending.add(textField_VendingItemLimit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame_CreateVending.add(button_CreateVending, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame_CreateVending.add(button_ExitVending, gbc);
        button_ExitVending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(MAIN_MENU);
            }
        });

    }
    /**
     * Creates the ActionListener for the "Create Vending Machine" button
     * in the vending machine creation menu.
     *
     * @param actn  The ActionListener to be set for the button.
     */
    public void setButton_CreateVending(ActionListener actn){
        button_CreateVending.addActionListener(actn);
    }
    /**
     * Retrieves the text entered in the "Vending Name" text field
     * of the vending machine creation menu.
     *
     * @return  The text entered in the "Vending Name" text field.
     */
    public String getTextField_VendingName(){
        return textField_VendingName.getText();
    }

    /**
     * Accesses the text entered in the "Slot Limit" text field
     * of the vending machine creation menu.
     *
     * @return  The text entered in the "Slot Limit" text field.
     */
    public String getTextField_VendingSlotLimit() {
        return textField_VendingSlotLimit.getText();
    }

    /**
     * Retrieves the text entered in the "Item Limit" text field
     * of the vending machine creation menu.
     *
     * @return  The text entered in the "Item Limit" text field.
     */
    public String getTextField_VendingItemLimit() {
        return textField_VendingItemLimit.getText();
    }

    /**
     * Sets the text of the "Errors" label in either the main menu
     * or the vending machine creation menu.
     *
     * @param string    The error message to be displayed.
     */
    public void setLabel_Errors(String string){
        label_Errors.setText(string);
    }

    /**
     * Sets up the view elements of the main menu.
     */
    private void setup_MainMenu(){
        frame_MainMenu.setVisible(true);
        frame_MainMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_MainMenu.setLayout(new GridBagLayout());
        frame_MainMenu.setSize(300,310);



        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0, 5, 0);
        frame_MainMenu.add(label_Title, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0, 0, 0);
        frame_MainMenu.add(button_Create, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame_MainMenu.add(button_Test, gbc);



        button_Create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu(CREATE_MENU);
            }
        });
    }

    /**
     * Sets the ActionListener for the "Test Vending Machine" button
     * in the main menu.
     *
     * @param actionListener    The ActionListener to be set for the button.
     */
    public void setButton_Test(ActionListener actionListener) {
        button_Test.addActionListener(actionListener);
    }

    /**
     * Sets the view menu to be displayed based on the provided menu option.
     *
     * @param menu  The menu option to be displayed
     *              (MAIN_MENU, CREATE_MENU, or 3 for hiding all menus).
     */
    public void setMenu(int menu){
        clearErrorLabel();
        switch (menu) {
            case 0 -> {
                frame_MainMenu.setVisible(true);
                frame_CreateVending.setVisible(false);
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 1;
                gbc.anchor = GridBagConstraints.CENTER;
                frame_MainMenu.add(label_Errors, gbc);
            }
            case 1 -> {
                frame_MainMenu.setVisible(false);
                frame_CreateVending.setVisible(true);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.insets = new Insets(0,0, 0, 0);
                frame_CreateVending.add(label_Errors, gbc);
            }
            case 3 -> {
                frame_MainMenu.setVisible(false);
                frame_CreateVending.setVisible(false);
            }
        }
    }

    /**
     * Clears the text of the "Errors" label in either the main menu
     * or the vending machine creation menu.
     */
    public void clearErrorLabel(){
        label_Errors.setText("");
    }

    /**
     * Resets the labels in the vending machine creation menu to
     * their default values.
     */
    public void resetCreateVendingLabels(){
        textField_VendingName.setText("");
        textField_VendingSlotLimit.setText("8");
        textField_VendingItemLimit.setText("10");
    }

}
