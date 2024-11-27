package org.baibei.binarybot.Objects;

import lombok.Data;

@Data
public class Command {

    private String command;
    private String[] arguments_before = new String[0];
    private String[] arguments_after = new String[0];
    private String[] arguments = new String[0];

    public Command(String command, String[] arguments_before, String[] arguments_after) {
        this.command = command;
        this.arguments_before = arguments_before;
        this.arguments_after = arguments_after;
    }

    public Command(String command) {
        this.command = command;
    }

    public Command() {}

    public Command setArguments() {
        this.arguments = new String[arguments_before.length + arguments_after.length];
        System.arraycopy(arguments_before, 0, this.arguments, 0, arguments_before.length);
        System.arraycopy(arguments_after, 0, this.arguments, arguments_before.length, arguments_after.length);
        return this;
    }
}
