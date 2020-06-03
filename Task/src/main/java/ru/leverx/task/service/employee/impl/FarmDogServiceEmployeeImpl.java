package ru.leverx.task.service.employee.impl;

import lombok.extern.java.Log;
import ru.leverx.task.entity.Aviary;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.employee.ServiceEmployee;

@Log
public class FarmDogServiceEmployeeImpl implements ServiceEmployee<Dog, Aviary> {

    private static final boolean IS_FED = true;
    private static final boolean IS_CLEANED = true;

    @Override
    public Aviary toClean(Aviary aviary) {
        log.info("The dog [" + aviary.getDog().getAge() + "] aviary is cleaned");
        aviary.setClean(IS_CLEANED);
        return aviary;
    }

    @Override
    public Dog toFeed(Dog dog) {
        log.info("The dog [" + dog.getAge() + "] is fed.");
        dog.setFed(IS_FED);
        return dog;
    }
}
