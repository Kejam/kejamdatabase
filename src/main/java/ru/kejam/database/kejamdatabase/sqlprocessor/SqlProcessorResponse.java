package ru.kejam.database.kejamdatabase.sqlprocessor;

import lombok.Builder;
import lombok.Data;
import ru.kejam.database.kejamdatabase.storage.data.Raw;

import java.util.List;

@Data
@Builder
public class SqlProcessorResponse {
    private final String tableName;
    private final List<Raw> raws;
}
