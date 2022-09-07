package metro.commands;

import metro.exeception.CommandNotFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandLineParser {
    private static final Pattern connectPattern;
    private static final Pattern appendPattern;
    private static final Pattern addHeadPattern;
    private static final Pattern removePattern;
    private static final Pattern outputPattern;
    private static final Pattern exitPattern;

    static {
        String argRegex = "[\\w.]+|\"(?=\\S)[\\s\\w.]+\"";
        String twoArgsRegex = "(?<line>" + argRegex + ")\\s+(?<station>" + argRegex + ")";
        String fourArgsRegex = twoArgsRegex + "\\s+(?<line2>" + argRegex + ")\\s+(?<station2>" + argRegex + ")";

        connectPattern = Pattern.compile("^/connect\\s+" + fourArgsRegex + "$");
        appendPattern = Pattern.compile("^/append\\s+" + twoArgsRegex + "$");
        addHeadPattern = Pattern.compile("^/add-head\\s+" + twoArgsRegex + "$");
        removePattern = Pattern.compile("^/remove\\s+" + twoArgsRegex + "$");
        outputPattern = Pattern.compile("^/output\\s+(?<line>" + argRegex + ")$");
        exitPattern = Pattern.compile("^/exit$");
    }

    private CommandLineParser() {
    }

    public static Command parse(String input) {
        Matcher connectMatcher = connectPattern.matcher(input);
        Matcher appendMatcher = appendPattern.matcher(input);
        Matcher addHeadMatcher = addHeadPattern.matcher(input);
        Matcher removeMatcher = removePattern.matcher(input);
        Matcher outputMatcher = outputPattern.matcher(input);
        Matcher exitMatcher = exitPattern.matcher(input);

        if (exitMatcher.matches()) {
            return new ExitCommand();
        }

        if (outputMatcher.matches()) {
            return new OutputCommand(outputMatcher.group("line").replace("\"", "").trim());
        }

        if (appendMatcher.matches()) {
            return new AppendCommand(
                    appendMatcher.group("line").replace("\"", "").trim(),
                    appendMatcher.group("station").replace("\"", "").trim()
            );
        }

        if (addHeadMatcher.matches()) {
            return new AddHeadCommand(
                    addHeadMatcher.group("line").replace("\"", "").trim(),
                    addHeadMatcher.group("station").replace("\"", "").trim()
            );
        }

        if (removeMatcher.matches()) {
            return new RemoveCommand(
                    removeMatcher.group("line").replace("\"", "").trim(),
                    removeMatcher.group("station").replace("\"", "").trim()
            );
        }

        if (connectMatcher.matches()) {
            return new ConnectCommand(
                    connectMatcher.group("line").replace("\"", "").trim(),
                    connectMatcher.group("station").replace("\"", "").trim(),
                    connectMatcher.group("line2").replace("\"", "").trim(),
                    connectMatcher.group("station2").replace("\"", "").trim()
            );
        }
        throw new CommandNotFoundException();
    }

}

