package ru.kejam.database.kejamdatabase.execption;

public class TableNotFoundException extends RuntimeException {
    private final String tableName;

    public TableNotFoundException(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
