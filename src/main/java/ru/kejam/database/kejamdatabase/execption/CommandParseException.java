package ru.kejam.database.kejamdatabase.execption;

public class CommandParseException extends RuntimeException{
    private final String raw;
    private final String command;
    private final String sql;

    public CommandParseException(String raw,
                                 String command,
                                 String sql) {
        this.raw = raw;
        this.command = command;
        this.sql = sql;
    }

    public String getInfo() {
        return "Error parse command " + command + "in raw " + raw +
                "in sql " + sql;
    }

    public String getShortInfo() {
        return "Error parse command " + command + "in raw " + raw;
    }
}
