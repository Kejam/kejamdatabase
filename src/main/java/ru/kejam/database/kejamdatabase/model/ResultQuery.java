package ru.kejam.database.kejamdatabase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultQuery {
    private final String result;
}
