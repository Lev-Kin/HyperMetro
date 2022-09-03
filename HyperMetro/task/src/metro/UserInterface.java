package metro;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    HashMap<String, LinkedList<String>> metroData = new HashMap<>();
    String[] command;

    public void start() {

        InfoReader infoReader = new InfoReader();
        metroData = infoReader.readFile(Main.FILE_NAME, metroData);

        getCommand();

        while (!command[0].equals("/exit")) {
            switch (command[0]) {
                case ("/append"):
                    if (command[1] != null && command[2] != null) {
                        appendStation(metroData, command[1], command[2]);
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                case ("/add-head"):
                    if (command[1] != null && command[2] != null) {
                        addStation(metroData, command[1], command[2]);
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                case ("/remove"):
                    if (command[1] != null && command[2] != null) {
                        removeStation(metroData, command[1], command[2]);
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                case ("/output"):
                    if (command[1] != null) {
                        printLineStations(metroData, command[1]);
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
            getCommand();
        }
    }

    public void getCommand() {

        String[] inputLine = scanner.nextLine().split(" ");
        command = new String[3];
        int indInLine = 0;
        int indComm = 0;

        while (indComm < inputLine.length) {
            if (inputLine[indComm].startsWith("\"")) {

                command[indInLine] = inputLine[indComm];
                if (indInLine == inputLine.length - 1) {
                    command[indInLine] = command[indInLine].replaceAll("\"", "");
                    return;
                }

                if (!inputLine[indComm].endsWith("\"")) {
                    for (int j = indComm + 1; j < inputLine.length; j++) {
                        if (inputLine[j].endsWith("\"")) {
                            command[indInLine] += " " + inputLine[j];
                            command[indInLine] = command[indInLine].replaceAll("\"", "");
                            indInLine++;
                            indComm += j;
                            break;
                        } else {
                            command[indInLine] += " " + inputLine[j];
                        }
                    }
                } else {
                    command[indInLine] = command[indInLine].replaceAll("\"", "");
                    indComm++;
                    indInLine++;
                }

            } else if (!inputLine[indComm].endsWith("\"")) {
                command[indInLine] = inputLine[indComm];
                indInLine++;
                indComm++;

            } else {
                indComm++;
            }
        }
    }

    public void appendStation(HashMap<String, LinkedList<String>> _metroData, String _lineName, String _stationName) {
        if (_metroData.containsKey(_lineName)) {
            LinkedList<String> stations = _metroData.get(_lineName);
            stations.add(stations.size() - 1, _stationName);
            _metroData.put(_lineName, stations);
        } else {
            System.out.println("Error: invalid line name");
        }
    }

    public void addStation(HashMap<String, LinkedList<String>> _metroData, String _lineName, String _stationName) {
        if (_metroData.containsKey(_lineName)) {
            LinkedList<String> stations = _metroData.get(_lineName);
            stations.add(1, _stationName);
            _metroData.put(_lineName, stations);
        } else {
            System.out.println("Error: invalid line name");
        }
    }

    public void removeStation(HashMap<String, LinkedList<String>> _metroData, String _lineName, String _stationName) {
        if (_metroData.containsKey(_lineName)) {
            LinkedList<String> stations = _metroData.get(_lineName);
            stations.remove(_stationName);
            _metroData.put(_lineName, stations);
        } else {
            System.out.println("Error: invalid line name");
        }
    }

    public void printLineStations(HashMap<String, LinkedList<String>> _metroData, String _lineName) {
        if (_metroData.containsKey(_lineName)) {
            LinkedList<String> stations = _metroData.get(_lineName);

            for (int i = 2; i < stations.size(); i++) {
                System.out.printf("%s - %s - %s\n", stations.get(i - 2), stations.get(i - 1), stations.get(i));
            }
        } else {
            System.out.println("Error: invalid line name");
        }
    }
}

