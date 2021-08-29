package ru.kejam.database.kejamdatabase.sqlparser;

import org.junit.jupiter.api.Test;
import ru.kejam.database.kejamdatabase.sqlparser.comand.SelectTableCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SelectTableSqlParserTest {
    private final SelectTableSqlParser selectTableSqlParser = new SelectTableSqlParser();
    private final String sqlAll = "select * from start_table;";
    private final String sql = "select (id, name) from start_table;";

    @Test
    void parseCommand() {
        final SelectTableCommand selectTableCommand = selectTableSqlParser.parseCommand(sql);

        final List<String> names = selectTableCommand.getNames();
        assertEquals("start_table", selectTableCommand.getTableName());
        assertEquals(2, names.size());
        assertTrue(names.contains("id"));
        assertTrue(names.contains("name"));
        assertFalse(selectTableCommand.isSelectAllFields());
    }

    @Test
    void parseCommandAll() {
        final SelectTableCommand selectTableCommand = selectTableSqlParser.parseCommand(sqlAll);

        assertEquals("start_table", selectTableCommand.getTableName());
        assertTrue(selectTableCommand.isSelectAllFields());
    }
}
