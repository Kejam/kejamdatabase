package ru.kejam.database.kejamdatabase.storage;

import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;
import ru.kejam.database.kejamdatabase.sqlprocessor.CreateTableResult;
import ru.kejam.database.kejamdatabase.storage.data.Table;

public interface Storage {
    CreateTableResult createTable(CreateTableCommand command);
    Table getTable(String name);
}
