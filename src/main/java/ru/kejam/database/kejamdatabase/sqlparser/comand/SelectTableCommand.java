package ru.kejam.database.kejamdatabase.sqlparser.comand;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class SelectTableCommand extends TableCommand {
    private final List<String> names;
    private final boolean where;
    private final Map<String, String> conditions;

    public SelectTableCommand(String tableName, boolean where) {
        super(tableName);
        this.where = where;
        names = new ArrayList<>();
        conditions = new HashMap<>();
    }

    public void putName(String name) {
        this.names.add(name);
    }

    public void putCondition(String name, String value) {
        this.conditions.put(name, value);
    }
}
