package ru.kejam.database.kejamdatabase.storage.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cell {
    private String name;
    private Class className;
    private Object value;
}
