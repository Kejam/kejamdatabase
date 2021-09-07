package ru.kejam.database.kejamdatabase.storage.data;

import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Getter
public class SimpleTable implements Table{
    private String name;
    private Cell[][] cells;
    private int capacity = 10;
    private int currentRaw = 0;
    private int numberOfFields;

    public SimpleTable(Map<String, Class> fields, String name) {
        this.numberOfFields = fields.size();
        this.name = name;
        this.cells = new Cell[10][this.numberOfFields];
        fields.forEach(
                (k,v) -> {
                    for (int i = 0; i < this.cells.length; i++) {
                        for (int y = 0; y < this.cells[i].length; y++) {
                            this.cells[y][i] = new Cell(k, v, null);
                        }
                    }
                }
        );
    }


    @Override
    public void add(Map<String, String> values) throws SQLException {
        final int raw = this.currentRaw;
        if (checkCapacity(1)) {
            resizeTable();
        }
        if (!checkInsert(this.cells[0], values)) {
            throw new SQLException("Error input type. Please validate insert value!");
        }
        for (int i = 0; i < this.cells[raw].length; i++) {
            final Cell cell = this.cells[raw][i];
            final String name = cell.getName();
            if (values.containsKey(name)) {
                if (cell.getClassName() == Integer.class) {
                    try {
                        Integer.parseInt(values.get(name));
                        cell.setValue(values.get(name));
                        values.remove(name);
                    } catch (NumberFormatException e) {
                        throw new SQLException("Error data type for cell " + cell.getName() + " expect " + cell.getClassName());
                    }
                } else {
                    cell.setValue(values.get(name));
                    values.remove(name);
                }
            }
        }
        if (!values.isEmpty()) {
            throw new SQLException("Values is not empty " + values.keySet());
        }
        this.currentRaw++;
    }


    private boolean checkInsert(Cell[] cells, Map<String, String> values) {
        int matches = 0;
        for (final Cell cell : cells) {
            final String name = cell.getName();
            if (values.containsKey(name)) {
                if (cell.getClassName() == Integer.class) {
                    try {
                        Integer.parseInt(values.get(name));
                        matches++;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                } else {
                    matches++;
                }
            }
        }
        return matches == values.size();
    }

    @SneakyThrows
    @Override
    public Cell[][] select(List<String> names) {
        final Cell[][] result = new Cell[currentRaw][names.size()];
        for (int i = 0; i < this.cells.length; i++) {
            int iResult = 0;
            for (int y = 0; y < this.cells[i].length; y++) {
                final Cell cell = this.cells[i][y];
                if (names.contains(cell.getName())) {
                    result[i][iResult++] = cell.clone();
                }
            }
        }
        return result;
    }

    private boolean checkCapacity(int i) {
        return (this.currentRaw + 1 + i) >= this.capacity;
    }

    private void resizeTable() {
        this.capacity = capacity * 2;
        Cell[][] news = new Cell[this.capacity][this.numberOfFields];
        for (int i = 0; i < news.length; i++) {
            for (int y = 0; y < news[i].length; y++) {
                final Cell cell = this.cells[0][y];
                news[i][y] = new Cell(cell.getName(), cell.getClassName(), null);
            }
        }
        for (int i = 0; i < this.cells.length; i++) {
            for (int y = 0; y < this.cells[i].length; y++) {
                news[i][y] = this.cells[i][y];
            }
        }
        this.cells = news;
    }
}
