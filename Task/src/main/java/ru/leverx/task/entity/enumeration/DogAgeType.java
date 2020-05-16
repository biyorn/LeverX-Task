package ru.leverx.task.entity.enumeration;

public enum DogAgeType {

    YOUNG("young"), ADULT("adult"), OLD("old");

    private String name;

    DogAgeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
