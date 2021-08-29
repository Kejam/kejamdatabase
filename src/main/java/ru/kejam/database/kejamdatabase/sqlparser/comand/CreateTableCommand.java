package ru.kejam.database.kejamdatabase.sqlparser.comand;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class CreateTableCommand extends TableCommand{
    @Getter
    private final Map<String, Class> fields;

    public CreateTableCommand(String tableName) {
        super(tableName);
        this.fields = new HashMap<>();
    }

    public void putField(String name, Class type) {
        fields.put(name, type);
    }
}
