package ru.kejam.database.kejamdatabase.searchengine;

import ru.kejam.database.kejamdatabase.storage.Storage;

public interface SearchEngine {
    public Object search(Storage storage, SearchRequest searchRequest);
}
