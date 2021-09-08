package ru.kejam.database.kejamdatabase.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kejam.database.kejamdatabase.execption.TableNotFoundException;
import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;
import ru.kejam.database.kejamdatabase.sqlprocessor.CreateTableResult;
import ru.kejam.database.kejamdatabase.storage.data.SimpleTable;
import ru.kejam.database.kejamdatabase.storage.data.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@Slf4j
@Service
public class InMemoryStorage implements Storage {
    private final Map<String, Table> tables;

    public InMemoryStorage() {
        this.tables = new HashMap<>();
    }

    @Override
    public CreateTableResult createTable(CreateTableCommand command) {
        final String tableName = command.getTableName();
        if (tables.containsKey(tableName)) {
            log.error("Table {} exists", tableName);
            return CreateTableResult.builder()
                    .tableName(tableName)
                    .status(false)
                    .reason("Table already exists")
                    .build();
        }
        tables.put(tableName, new SimpleTable(command.getFields(), tableName));
        return CreateTableResult.builder()
                .tableName(tableName)
                .status(true)
                .build();
    }

    @Override
    public Table getTable(String name) {
        if(!tables.containsKey(name)) {
            throw new TableNotFoundException(name);
        }
        return tables.get(name);
    }

    @Override
    public Set<String> listTables() {
        return tables.keySet();
    }
}
