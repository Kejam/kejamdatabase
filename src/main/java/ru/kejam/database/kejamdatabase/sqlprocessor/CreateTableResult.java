package ru.kejam.database.kejamdatabase.sqlprocessor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTableResult {
    private final String tableName;
    private final boolean status;
    private final String reason;
}
