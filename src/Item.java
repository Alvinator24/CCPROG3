public class Item {

    private String itemName;
    private double price;
    private double calories;

    // constructor for the Item class
    public Item(String itemName, double price, double calories) {
        this.itemName = itemName;
        this.price = price;
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }


    public double getCalories() {
        return calories;
    }

   

    

}
