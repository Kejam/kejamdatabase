package ru.kejam.database.kejamdatabase.sqlparser.type;


import lombok.Getter;
import ru.kejam.database.kejamdatabase.execption.UnknownType;

public enum KejamTypeDB {
    StringType(String.class),
    IntegerType(Integer.class);

    @Getter
    private final Class javaClass;

    KejamTypeDB(Class javaClass) {
        this.javaClass = javaClass;
    }

    public static KejamTypeDB findType(String name) {
        if ("int".equalsIgnoreCase(name)) {
            return IntegerType;
        }
        if ("String".equalsIgnoreCase(name)) {
            return StringType;
        }
        throw new UnknownType(name);
    }
}
