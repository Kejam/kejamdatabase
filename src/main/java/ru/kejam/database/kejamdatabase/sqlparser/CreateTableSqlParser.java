package ru.kejam.database.kejamdatabase.sqlparser;

import lombok.extern.slf4j.Slf4j;
import ru.kejam.database.kejamdatabase.execption.CommandParseException;
import ru.kejam.database.kejamdatabase.sqlparser.comand.CreateTableCommand;
import ru.kejam.database.kejamdatabase.sqlparser.type.KejamTypeDB;

@Slf4j
public class CreateTableSqlParser implements SqlParser{
    @Override
    public CreateTableCommand parseCommand(String sql) {
        final int indexStartFields = sql.indexOf("(");
        final String tableName = sql.substring(0, indexStartFields)
                .replaceAll(" +", "")
                .replaceAll("createtable", "");
        final CreateTableCommand command = new CreateTableCommand(tableName);
        final int endStartFields = sql.lastIndexOf(")");
        final String fields = sql.substring(indexStartFields + 1, endStartFields);
        final String[] rawFields = fields.split(",");
        for (String rawField : rawFields) {
            final String strip = rawField
                    .strip()
                    .replaceAll(" +", " ")
                    .replaceAll("\\r|\\n", "");
            final String[] values = strip.split(" ");
            if (values.length != 2) {
                log.error("Something wrong! Error parse raw {} in sql {}", rawField, sql);
                throw new CommandParseException(rawField, CreateTableCommand.class.getName(), sql);
            }
            final KejamTypeDB type = KejamTypeDB.findType(values[1]);
            command.putField(values[0], type.getJavaClass());
        }
        return command;
    }
}
