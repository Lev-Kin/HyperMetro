package metro.exeception;

public class LineNotFoundException extends RuntimeException {
    public LineNotFoundException(String name) {
        super(String.format("Line \"%s\" not found", name));
    }
}
