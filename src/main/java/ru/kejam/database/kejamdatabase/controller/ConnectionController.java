package ru.kejam.database.kejamdatabase.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kejam.database.kejamdatabase.controller.model.InitialResponse;
import ru.kejam.database.kejamdatabase.controller.model.ResultQuery;

@RestController
@Slf4j
public class ConnectionController {

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
    public ResponseEntity<ResultQuery> executeQuery(@RequestParam("sql") String sql) {
        log.info("Try to execute sql {}", sql);
        return ResponseEntity.ok(
            ResultQuery.builder().result("Success result").build()
        );
    }
}
