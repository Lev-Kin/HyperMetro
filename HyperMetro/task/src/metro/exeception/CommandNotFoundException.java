package metro.exeception;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException() {
        super("Invalid command");
    }
}
