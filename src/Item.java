/**
 * This class is where each instance of an item will be created
 * and where information about the item wil be accessed.
 */
public class Item {

    private final String itemName;
    private final double calories;

    /**
     * Constructs an Item class that will be placed in a slot for the Vending Machine to sell.
     *
     * @param itemName  The name of the item being sold.
     * @param calories  The amount of calories the item has.
     */
    public Item(String itemName, double calories) {
        this.itemName = itemName;
        this.calories = calories;
    }

    /**
     * Returns the name of the item.
     *
     * @return  String variable of the item's name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns the amount of calories of the item.
     *
     * @return  Double variable of the item's calorie amount.
     */
    public double getCalories() {
        return calories;
    }

   

    

}
