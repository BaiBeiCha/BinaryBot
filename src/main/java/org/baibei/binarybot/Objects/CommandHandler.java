package org.baibei.binarybot.Objects;

public class CommandHandler {

    public static Command handle(String command) {
        Command cmd = new Command();

        String[] words = command.split(" ");

        if (words.length <= 1) {
            cmd.setCommand("Invalid command");
            return cmd;
        }

        int index = findCommand(words);
        if (index == -1) {
            cmd.setCommand("Invalid command");
            return cmd;
        }

        String[] args_before = new String[index];
        for (int i = 0; i < args_before.length; i++) {
            args_before[i] = words[i];
        }

        String[] args_after = new String[words.length - index - 1];
        for (int i = 0; i < args_after.length; i++) {
            args_after[i] = words[index + i + 1];
        }

        cmd.setCommand(words[index]);
        cmd.setArguments_before(args_before);
        cmd.setArguments_after(args_after);

        return cmd;
    }

    public static int findCommand(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                if (words[i].charAt(0) == '/') {
                    return i;
                }
            }
        }

        return -1;
    }
}
