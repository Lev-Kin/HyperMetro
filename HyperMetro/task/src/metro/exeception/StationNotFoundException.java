package metro.exeception;

public class StationNotFoundException extends RuntimeException {
    public StationNotFoundException(String name) {
        super(String.format("Station \"%s\" not found", name));
    }
}
