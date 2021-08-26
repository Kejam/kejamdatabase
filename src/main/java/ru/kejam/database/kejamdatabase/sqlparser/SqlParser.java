package ru.kejam.database.kejamdatabase.sqlparser;

public interface SqlParser<Command> {
    public Command parseCommand(String sql);
}
