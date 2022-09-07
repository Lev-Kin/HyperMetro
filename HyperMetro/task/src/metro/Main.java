package metro;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import metro.commands.Command;
import metro.commands.CommandLineParser;
import metro.commands.Executor;
import metro.commands.ExitCommand;
import metro.exeception.CommandNotFoundException;
import metro.exeception.LineNotFoundException;
import metro.exeception.StationNotFoundException;
import metro.hmetro.Metro;
import metro.utils.JsonUtils;

import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor();

        try {
            Path filePath = Paths.get(args[0]);
            JsonObject data = JsonUtils.parseFromFile(filePath);
            Metro.INSTANCE.init(data);

            while (true) {
                try {
                    Command command = CommandLineParser.parse(scanner.nextLine());
                    if (command instanceof ExitCommand) {
                        break;
                    }
                    executor.execute(command);
                } catch (CommandNotFoundException
                         | LineNotFoundException
                         | StationNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (NoSuchFileException | InvalidPathException e) {
            System.out.println("Error! Such a file doesn't exist!");
        } catch (JsonParseException e) {
            System.out.println("Incorrect file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


