package ru.kejam.database.kejamdatabase.storage.data;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleTable implements Table{
    private Cell[][] cells;
    private int capacity = 10;
    private int size = 0;

    public SimpleTable(Map<String, Class> fields) {
        cells = new Cell[10][fields.size()];
        fields.forEach(
                (k,v) -> {
                    for (int i = 0; i < cells.length; i++) {
                        cells[0][i] = new Cell(k, v, null);
                    }
                }
        );
    }
}