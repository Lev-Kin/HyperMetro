package metro.hmetro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Station {
    private String name;
    private List<Map<String, String>> transfer = new ArrayList<>();

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, String>> getTransfer() {
        return transfer;
    }

    public void setTransfer(List<Map<String, String>> transfer) {
        this.transfer = transfer;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(name);
        transfer.forEach(t -> output.append(" - ")
                .append(t.get("station"))
                .append(String.format(" (%s)", t.get("line"))));
        return output.toString();
    }
}

