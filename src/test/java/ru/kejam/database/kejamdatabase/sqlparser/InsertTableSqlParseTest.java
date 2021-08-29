package ru.kejam.database.kejamdatabase.sqlparser;

import org.junit.jupiter.api.Test;
import ru.kejam.database.kejamdatabase.sqlparser.comand.InsertTableCommand;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertTableSqlParseTest {
    private final InsertTableSqlParser insertTableSqlParser = new InsertTableSqlParser();
    private final String sql = "insert into start_table (id, name) values ('1', 'Alex');";

    @Test
    void parseCommand() {
        final InsertTableCommand command = insertTableSqlParser.parseCommand(sql);

        final Map<String, String> values = command.getValues();
        assertEquals("1", values.get("id"));
        assertEquals("Alex", values.get("name"));
        assertEquals("start_table", command.getTableName());
    }
}
