package org.baibei.binarybot;

import org.baibei.binarybot.Objects.Bot;
import org.baibei.binarybot.Objects.Command;
import org.baibei.binarybot.Objects.CommandHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BinaryBotApplication {

    public static void main(String[] args) {
        //test(100000);

        SpringApplication.run(BinaryBotApplication.class, args);
    }

    private static boolean test(int iterations) {
        List<Command> commands = new ArrayList<>();

        for (int i = 0; i < iterations; i++) {
            StringBuilder command = new StringBuilder();
            int rand = (int) (Math.random() * 4 - 0.1);

            command.append(switch (rand) {
                            case 0 -> "/bin ";
                            case 1 -> "/dec ";
                            case 2 -> "a ";
                            default -> "/aaa ";
            });

            StringBuilder message = new StringBuilder();
            for (int j = 0; j < (int) (Math.random() * 200); j++) {
                message.append((char) (Math.random() * 500));
            }
            command.append(message.toString());

            System.out.println("Command: " + command);
            commands.add(CommandHandler.handle(command.toString()).setArguments());
        }

        for (Command command : commands) {
            System.out.println(Bot.buildMessage(command));
        }
        return true;
    }

}
