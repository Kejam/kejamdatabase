package ru.kejam.database.kejamdatabase.storage.data;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Table {
    void add(Map<String, String> values) throws SQLException;
    Cell[][] select(List<String> names);
}
