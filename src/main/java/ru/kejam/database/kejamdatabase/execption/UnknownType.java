package ru.kejam.database.kejamdatabase.execption;

public class UnknownType extends RuntimeException{
    private final String nameType;

    public UnknownType(String nameType) {
        this.nameType = nameType;
    }

    public String getNameType() {
        return nameType;
    }
}
