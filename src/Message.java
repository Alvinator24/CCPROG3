public class Message {
    private String message;
    private int precedence;

    Message(String message, int precedence){
        this.message = message;
        this.precedence = precedence;
    }

    public String getMessage() {
        return message;
    }

    public int getPrecedence() {
        return precedence;
    }
}
