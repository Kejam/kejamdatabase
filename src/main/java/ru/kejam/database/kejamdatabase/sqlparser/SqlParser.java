package ru.kejam.database.kejamdatabase.sqlparser;

public interface SqlParser<Command> {
    Command parseCommand(String sql);
}
