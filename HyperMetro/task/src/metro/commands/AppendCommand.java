package metro.commands;

import metro.hmetro.Metro;

public record AppendCommand(String line, String station) implements Command {

    @Override
    public void execute() {
        Metro.INSTANCE.appendStation(line, station);
    }
}
