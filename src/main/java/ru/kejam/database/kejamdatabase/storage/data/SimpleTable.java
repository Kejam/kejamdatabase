package ru.kejam.database.kejamdatabase.storage.data;

import java.sql.SQLException;
import java.util.Map;

public class SimpleTable implements Table{
    private String name;
    private Cell[][] cells;
    private int capacity = 10;
    private int freeRaw = 0;

    public SimpleTable(Map<String, Class> fields, String name) {
        cells = new Cell[10][fields.size()];
        fields.forEach(
                (k,v) -> {
                    for (int i = 0; i < cells.length; i++) {
                        cells[0][i] = new Cell(k, v, null);
                    }
                }
        );
    }


    @Override
    public void add(Map<String, String> values) throws SQLException {
        final int raw = freeRaw++;
        for (int i = 0; i < cells[freeRaw].length; i++) {
            final Cell cell = cells[freeRaw][i];
            final String name = cell.getName();
            if (values.containsKey(name)) {
                cell.setValue(values.get(name));
                values.remove(name);
            }
        }
        if (!values.isEmpty()) {
            throw new SQLException("Values is not empty " + values.keySet());
        }
    }
}
