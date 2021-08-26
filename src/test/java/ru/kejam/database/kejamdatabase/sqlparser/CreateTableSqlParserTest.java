package ru.kejam.database.kejamdatabase.sqlparser;

import org.junit.jupiter.api.Test;
import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTableSqlParserTest {
    private final CreateTableSqlParser createTableSqlParser = new CreateTableSqlParser();
    private final String sql = "create table start_table(\n" +
            "      id int,\n" +
            "      name String\n" +
            ");";

    @Test
    void parseCommand() {
        final CreateTableCommand command = createTableSqlParser.parseCommand(sql);

        final Map<String, Class> fields = command.getFields();
        assertEquals(Integer.class, fields.get("id"));
        assertEquals(String.class, fields.get("name"));
    }
}