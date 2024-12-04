package org.baibei.binarybot;

import org.baibei.binarybot.Objects.Bot;
import org.baibei.binarybot.Objects.Command;
import org.baibei.binarybot.Objects.CommandHandler;
import org.baibei.binarybot.Objects.Convertor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class BinaryBotApplication {

    public static void main(String[] args) {
        test(5);

        //String converted = Convertor.convertTo("2AC", 16, 2);
        //System.out.println("Converted: " + converted);
        //System.out.println("ReConverted: " + Convertor.convertTo(converted, 2, 16));

        //SpringApplication.run(BinaryBotApplication.class, args);
    }

    private static boolean test(int iterations) {
        HashMap<Integer, Character> ints = new HashMap<>();

        for (int i = 0; i <= 9; i++) {
            ints.put(i, (char) (i + '0'));
        }
        for (int i = 0; i < 26; i++) {
            ints.put((10 + i), (char) ('A' + i));
        }
        for (int i = 0; i < 26; i++) {
            ints.put((10 + 26 + i), (char) ('a' + i));
        }

        List<Command> commands = new ArrayList<>();

        for (int i = 0; i < iterations; i++) {
            StringBuilder command = new StringBuilder();
            //int rand = (int) (Math.random() * 4 - 0.1);
            //command.append(switch (rand) {
            //                case 0 -> "/bin ";
            //                case 1 -> "/dec ";
            //                case 2 -> "a ";
            //                default -> "/aaa ";
            //});

            command.append("/to");
            int base1 = (int) (Math.random() * 50 + 2);
            int base2 = (int) (Math.random() * 50 + 2);
            command.append(" ");
            command.append(base1);
            command.append(" ");
            command.append(base2);
            command.append(" ");

            StringBuilder message = new StringBuilder();
            for (int j = 0; j < (int) (Math.random() * 200); j++) {
                int c = (int) (Math.random() * 62);
                if (c == 62) {
                    message.append(" ");
                } else {
                    message.append(ints.get(c));
                }
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
