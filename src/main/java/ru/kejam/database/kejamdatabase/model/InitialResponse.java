package ru.kejam.database.kejamdatabase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InitialResponse {
    private final String status;
    private final String description;
}
