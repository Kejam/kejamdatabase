package ru.kejam.database.kejamdatabase.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kejam.database.kejamdatabase.controller.model.InitialResponse;
import ru.kejam.database.kejamdatabase.sqlprocessor.SqlProcessor;
import ru.kejam.database.kejamdatabase.sqlprocessor.SqlProcessorResponse;
import ru.kejam.database.kejamdatabase.storage.Storage;

import java.util.Set;

@RestController
@Slf4j
public class ConnectionController {
    private final SqlProcessor sqlProcessor;
    private final Storage storage;

    public ConnectionController(SqlProcessor sqlProcessor,
                                Storage storage) {
        this.sqlProcessor = sqlProcessor;
        this.storage = storage;
    }

    @GetMapping(path = "initdb")
    public ResponseEntity<InitialResponse> checkConnection() {
        log.info("Someone try to init connection with DB");
        return ResponseEntity.ok(
                InitialResponse
                        .builder()
                        .status("OK")
                        .description("Success init connection with db")
                        .build()
        );
    }

    @PostMapping(path = "executeQuery")
    public ResponseEntity<SqlProcessorResponse> executeQuery(@RequestParam("sql") String sql) {
        log.info("Try to execute sql {}", sql);
        final SqlProcessorResponse sqlProcessorResponse = sqlProcessor.executeSql(sql);
        return ResponseEntity.ok(sqlProcessorResponse);
    }

    @GetMapping(path = "list")
    public ResponseEntity<Set<String>> listTables() {
        log.info("Try to get list of tables");
        return ResponseEntity.ok(storage.listTables());
    }
}
