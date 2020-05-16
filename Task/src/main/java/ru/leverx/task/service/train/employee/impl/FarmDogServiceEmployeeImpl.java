package ru.leverx.task.service.train.employee.impl;

import ru.leverx.task.entity.Aviary;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.train.employee.ServiceEmployee;

public class FarmDogServiceEmployeeImpl implements ServiceEmployee<Dog, Aviary> {

    @Override
    public Aviary toClean(Aviary aviary) {
        // some code
        return aviary;
    }

    @Override
    public Dog toFeed(Dog dog) {
        // some code
        return dog;
    }
}
