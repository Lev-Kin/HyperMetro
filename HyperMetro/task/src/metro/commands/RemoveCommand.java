package metro.commands;

import metro.hmetro.Metro;

public record RemoveCommand(String line, String station) implements Command {

    @Override
    public void execute() {
        Metro.INSTANCE.removeStation(line, station);
    }
}
