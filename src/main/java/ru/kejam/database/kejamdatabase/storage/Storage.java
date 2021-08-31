package ru.kejam.database.kejamdatabase.storage;

import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;
import ru.kejam.database.kejamdatabase.storage.data.Table;

public interface Storage {
    boolean createTable(CreateTableCommand command);
    Table getTable(String name);
}
