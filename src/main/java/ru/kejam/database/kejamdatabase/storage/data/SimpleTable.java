package ru.kejam.database.kejamdatabase.storage.data;

import java.util.Map;

public class SimpleTable implements Table{
    private String name;
    private Cell[][] cells;
    private int capacity = 10;
    private int size = 0;

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
    public void add(Map<String, String> values) {
        
    }
}
