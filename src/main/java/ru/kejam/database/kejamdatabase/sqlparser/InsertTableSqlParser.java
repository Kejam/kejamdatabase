package ru.kejam.database.kejamdatabase.sqlparser;

import ru.kejam.database.kejamdatabase.execption.CommandParseException;
import ru.kejam.database.kejamdatabase.sqlparser.comand.InsertTableCommand;

import java.util.Arrays;

public class InsertTableSqlParser implements SqlParser<InsertTableCommand> {
    @Override
    public InsertTableCommand parseCommand(String sql) {
        final String clearSql = sql.strip()
                .replaceAll(" +", " ")
                .replaceAll("[\\r\\n]", "");
        final String[] parts = clearSql.split(" ");
        final String tableName = parts[2];
        final String[] names = clearSql.substring(
                clearSql.indexOf('(') + 1,
                clearSql.indexOf(')')
        ).replace(" ", "").replace("'", "").split(",");
        final String[] values = clearSql.substring(
                clearSql.lastIndexOf('(') + 1,
                clearSql.lastIndexOf(')')
        ).replace(" ", "").replace("'", "").split(",");
        if (names.length != values.length) {
            throw new CommandParseException(Arrays.toString(names), InsertTableSqlParser.class.getName(), clearSql);
        }
        final InsertTableCommand insertTableCommand = new InsertTableCommand(tableName);
        for (int i = 0; i < names.length; i++) {
            insertTableCommand.putValue(names[i], values[i]);
        }
        return insertTableCommand;
    }
}
