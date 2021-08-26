package ru.kejam.database.kejamdatabase.storage;

import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;

public interface Storage<Command> {
    public boolean createTable(CreateTableCommand command);
}
