package metro.commands;

import metro.hmetro.Metro;

public record OutputCommand(String line) implements Command {

    @Override
    public void execute() {
        Metro.INSTANCE.outputLine(line);
    }
}
