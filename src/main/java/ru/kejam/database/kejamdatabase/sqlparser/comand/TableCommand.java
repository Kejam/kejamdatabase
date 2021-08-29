package ru.kejam.database.kejamdatabase.sqlparser.comand;

import lombok.Data;
import lombok.Getter;


public abstract class TableCommand implements Command{
    @Getter
    private final String tableName;

    protected TableCommand(String tableName) {
        this.tableName = tableName;
    }


}
