package ru.kejam.database.kejamdatabase.sqlparser.comand;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SelectTableCommand extends TableCommand {
    private final List<String> names;
    private final boolean selectAllFields;

    public SelectTableCommand(String tableName, boolean selectAllFields) {
        super(tableName);
        this.selectAllFields = selectAllFields;
        this.names = new ArrayList<>();
    }

    public void putName(String name) {
        this.names.add(name);
    }
}
