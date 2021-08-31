package ru.kejam.database.kejamdatabase.sqlparser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kejam.database.kejamdatabase.sqlparser.comand.SelectTableCommand;

@Slf4j
@Service
public class SelectTableSqlParser implements SqlParser<SelectTableCommand> {
    @Override
    public SelectTableCommand parseCommand(String sql) {
        final String clearSql = sql.strip()
                .replaceAll(" +", " ")
                .replaceAll("[\\r\\n]", "");
        final String[] parts = clearSql.split(" ");
        final String tableName = parts[parts.length - 1].replaceAll(";", "");
        if ("*".equalsIgnoreCase(parts[1])) {
            return new SelectTableCommand(tableName, true);
        }
        final String[] names = clearSql.substring(
                clearSql.indexOf('(') + 1,
                clearSql.indexOf(')')
        ).replace(" ", "").split(",");
        final SelectTableCommand selectTableCommand = new SelectTableCommand(tableName, false);
        for (int i = 0; i < names.length; i++) {
            selectTableCommand.putName(names[i]);
        }
        return selectTableCommand;
    }
}
