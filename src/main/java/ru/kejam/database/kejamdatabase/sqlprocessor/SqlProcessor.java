package ru.kejam.database.kejamdatabase.sqlprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kejam.database.kejamdatabase.sqlparser.CreateTableSqlParser;
import ru.kejam.database.kejamdatabase.sqlparser.InsertTableSqlParser;
import ru.kejam.database.kejamdatabase.sqlparser.SelectTableSqlParser;
import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;
import ru.kejam.database.kejamdatabase.sqlparser.comand.InsertTableCommand;
import ru.kejam.database.kejamdatabase.storage.Storage;
import ru.kejam.database.kejamdatabase.storage.data.Table;

import java.sql.SQLException;

@Service
@Slf4j
public class SqlProcessor {
    private final CreateTableSqlParser createTableSqlParser;
    private final InsertTableSqlParser insertTableSqlParser;
    private final SelectTableSqlParser selectTableSqlParser;
    private final Storage storage;

    public SqlProcessor(CreateTableSqlParser createTableSqlParser,
                        InsertTableSqlParser insertTableSqlParser,
                        SelectTableSqlParser selectTableSqlParser,
                        Storage storage) {
        this.createTableSqlParser = createTableSqlParser;
        this.insertTableSqlParser = insertTableSqlParser;
        this.selectTableSqlParser = selectTableSqlParser;
        this.storage = storage;
    }

    public SqlProcessorResponse executeSql(String sql) {
        if (sql.contains("insert")) {
            final InsertTableCommand insertTableCommand = insertTableSqlParser.parseCommand(sql);
            return insert(insertTableCommand);
        }
        if (sql.contains("select")) {
            return null;
        }
        if (sql.contains("create")) {
            final CreateTableCommand command = createTableSqlParser.parseCommand(sql);
            return createTable(command);
        }
        return SqlProcessorResponse.builder()
                .errorReason("Unknown sql command type")
                .error(true)
                .build();
    }

    private SqlProcessorResponse createTable(CreateTableCommand command) {
        final CreateTableResult createTableResult = storage.createTable(command);
        if (createTableResult.isStatus()) {
            return SqlProcessorResponse.builder()
                    .tableName(command.getTableName())
                    .error(false)
                    .build();
        } else {
            return SqlProcessorResponse.builder()
                    .tableName(command.getTableName())
                    .error(true)
                    .errorReason(createTableResult.getReason())
                    .build();
        }
    }

    private SqlProcessorResponse insert(InsertTableCommand insertTableCommand) {
        final String tableName = insertTableCommand.getTableName();
        final Table table = storage.getTable(tableName);
        try {
            table.add(insertTableCommand.getValues());
            return SqlProcessorResponse.builder()
                    .tableName(tableName)
                    .error(false)
                    .build();
        } catch (SQLException e) {
            return SqlProcessorResponse.builder()
                    .tableName(tableName)
                    .error(true)
                    .errorReason(e.getMessage())
                    .build();
        }
    }
}
