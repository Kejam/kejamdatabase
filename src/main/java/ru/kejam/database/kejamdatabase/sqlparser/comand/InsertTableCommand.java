package ru.kejam.database.kejamdatabase.sqlparser.comand;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class InsertTableCommand extends TableCommand{
    @Getter
    private final Map<String, String> values;

    public InsertTableCommand(String tableName) {
        super(tableName);
        this.values = new HashMap<>();
    }

    public void putValue(String nameOfColumn, String value) {
        this.values.put(nameOfColumn, value);
    }
}
