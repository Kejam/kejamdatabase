package ru.kejam.database.kejamdatabase.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;
import ru.kejam.database.kejamdatabase.storage.data.SimpleTable;
import ru.kejam.database.kejamdatabase.storage.data.Table;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Slf4j
public class InMemoryStorage implements Storage {
    private final Map<String, Table> tables;

    public InMemoryStorage() {
        this.tables = new HashMap<>();
    }

    @Override
    public boolean createTable(CreateTableCommand command) {
        final String tableName = command.getTableName();
        if (tables.containsKey(tableName)) {
            log.error("Table {} exists", tableName);
        }
        tables.put(tableName, new SimpleTable(command.getFields(), tableName));
        return true;
    }
}
