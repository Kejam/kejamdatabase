package ru.kejam.database.kejamdatabase.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;
import ru.kejam.database.kejamdatabase.storage.data.Table;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class InMemoryStorage implements Storage {
    private final Map<String, Table> tables;

    public InMemoryStorage() {
        this.tables = new HashMap<>();
    }

    @Override
    public boolean createTable(CreateTableCommand command) {

        return false;
    }
}
