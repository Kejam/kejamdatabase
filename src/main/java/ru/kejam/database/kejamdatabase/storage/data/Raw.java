package ru.kejam.database.kejamdatabase.storage.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Raw {
    private final String tableName;
    private final List<Cell> cells;
}
