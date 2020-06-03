package ru.leverx.task.entity.enumeration;

public enum DogJob {

    POLICE("police"), EMERGENCY("emergency"), NONE("none");

    private String name;

    DogJob(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
