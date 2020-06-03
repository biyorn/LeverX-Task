package ru.leverx.task.service.employee.impl;

import lombok.extern.java.Log;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.employee.VetService;

@Log
public class DogVetServiceImpl implements VetService<Dog> {

    private static final boolean IS_CURED = true;

    @Override
    public Dog toTreat(Dog dog) {
        log.info("The dog [" + dog.getAge() + "] is cured");
        dog.setHealthy(IS_CURED);
        return dog;
    }
}
