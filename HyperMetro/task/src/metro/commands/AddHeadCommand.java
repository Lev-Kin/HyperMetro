package metro.commands;

import metro.hmetro.Metro;

public record AddHeadCommand(String line, String station) implements Command {

    @Override
    public void execute() {
        Metro.INSTANCE.addStation(line, station);
    }
}
