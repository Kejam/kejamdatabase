package ru.kejam.database.kejamdatabase.sqlprocessor;

import lombok.Builder;
import lombok.Data;
import ru.kejam.database.kejamdatabase.storage.data.Cell;

@Data
@Builder
public class SqlProcessorResponse {
    private final String tableName;
    private final Cell[][] cells;
    private final boolean error;
    private final String errorReason;
}
