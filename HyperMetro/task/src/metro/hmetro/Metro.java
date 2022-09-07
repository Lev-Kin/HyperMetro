package metro.hmetro;

import com.google.gson.JsonObject;
import metro.exeception.LineNotFoundException;
import metro.exeception.StationNotFoundException;
import metro.list.DoublyLinkedList;
import metro.list.Node;

import java.util.HashMap;
import java.util.Map;

public enum Metro {
    INSTANCE;
    private static final Station DEPOT = new Station("depot");
    private final Map<String, DoublyLinkedList<Station>> metroLines = new HashMap<>();

    Metro() {
    }

    public void init(JsonObject json) {
        json.entrySet().forEach(line -> {
            DoublyLinkedList<Station> stationsList = new DoublyLinkedList<>();
            JsonObject stations = line.getValue().getAsJsonObject();
            stations.entrySet().forEach(station -> {
                JsonObject stationJson = station.getValue().getAsJsonObject();
                Station s = new Station();
                s.setName(stationJson.get("name").getAsString());
                if (!stationJson.get("transfer").isJsonNull()) {
                    stationJson.get("transfer").getAsJsonArray()
                            .forEach(jsonElement -> {
                                Map<String, String> transfer = new HashMap<>();
                                JsonObject transferJson = jsonElement.getAsJsonObject();
                                transfer.put("line", transferJson.get("line").getAsString());
                                transfer.put("station", transferJson.get("station").getAsString());
                                s.getTransfer().add(transfer);
                            });
                }
                stationsList.addLast(s);
            });
            metroLines.put(line.getKey(), stationsList);
        });
    }

    public void outputLine(String line) {
        if (metroLines.containsKey(line)) {
            DoublyLinkedList<Station> stations = metroLines.get(line);
            System.out.println(DEPOT);
            System.out.print(stations);
            System.out.println(DEPOT);
            return;
        }
        throw new LineNotFoundException(line);
    }

    public void addStation(String line, String station) {
        if (metroLines.containsKey(line)) {
            metroLines.get(line).addFirst(new Station(station));
            return;
        }
        throw new LineNotFoundException(line);
    }

    public void appendStation(String line, String station) {
        if (metroLines.containsKey(line)) {
            metroLines.get(line).addLast(new Station(station));
            return;
        }
        throw new LineNotFoundException(line);
    }

    public void removeStation(String line, String station) {
        if (metroLines.containsKey(line)) {
            DoublyLinkedList<Station> list = metroLines.get(line);
            Node<Station> tmp = list.getHead();
            while (tmp != null) {
                if (tmp.getData().getName().equals(station)) {
                    list.remove(tmp);
                    return;
                }
                tmp = tmp.getNext();
            }
            return;
        }
        throw new LineNotFoundException(line);
    }

    public void connect(String line1, String station1, String line2, String station2) {
        connectStation(line1, station1, line2, station2);
        connectStation(line2, station2, line1, station1);
    }

    private void connectStation(String line1, String station1, String line2, String station2) {
        if (metroLines.containsKey(line1)) {
            DoublyLinkedList<Station> stations = metroLines.get(line1);
            Node<Station> tmp = stations.getHead();
            while (tmp != null) {
                if (tmp.getData().getName().equals(station1)) {
                    tmp.getData().getTransfer().add(
                            Map.of(
                                    "line", line2,
                                    "station", station2
                            )
                    );
                    return;
                }
                tmp = tmp.getNext();
            }
            throw new StationNotFoundException(station1);
        }
        throw new LineNotFoundException(line1);
    }
}
