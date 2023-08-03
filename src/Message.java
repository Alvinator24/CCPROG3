/**
 * This class is where messages for the checkout phase as the items are
 * being prepared to be sold.
 */
public class Message {
    private final String message;
    private final int precedence;

    /**
     * Constructs an instance of a Message class containing the actual
     * message and its precedence value.
     *
     * @param message       The text of the actual message.
     * @param precedence    The precedance value or the numerical order
     *                      for when the message will be displayed.
     */
    Message(String message, int precedence){
        this.message = message;
        this.precedence = precedence;
    }

    /**
     * Allows other classes to access the text of the Message class.
     *
     * @return String variable of the item's message.
     */
    public String getMessage() {
        return message;
    }
    /**
     * Allows other classes to access the precedance value of the Message class.
     *
     * @return Integer variable of the item's precedence value.
     */
    public int getPrecedence() {
        return precedence;
    }
}
