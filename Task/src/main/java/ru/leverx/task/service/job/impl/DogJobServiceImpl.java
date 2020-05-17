package ru.leverx.task.service.job.impl;

import lombok.extern.java.Log;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.job.JobService;

@Log
public class DogJobServiceImpl implements JobService<Dog> {

    @Override
    public Dog toWork(Dog dog) {
        log.info("The dog [" + dog.getAge() + "] is at work of " + dog.getDogJob().getName());
        return dog;
    }
}
