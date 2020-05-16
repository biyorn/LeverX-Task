package ru.leverx.task.service.train.employee.impl;

import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.train.employee.VetService;

public class DogVetServiceImpl implements VetService<Dog> {

    @Override
    public Dog toTreat(Dog dog) {
        // some code
        return dog;
    }
}
