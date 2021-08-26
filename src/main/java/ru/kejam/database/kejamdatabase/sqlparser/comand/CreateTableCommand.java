package ru.kejam.database.kejamdatabase.sqlparser.comand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class CreateTableCommand implements Command{
    private final Map<String, Class> fields;

    public CreateTableCommand() {
        this.fields = new HashMap<>();
    }

    @Override
    public void putField(String name, Class type) {
        fields.put(name, type);
    }
}
