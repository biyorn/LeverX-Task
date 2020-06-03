package ru.leverx.task.service.train.impl;

import lombok.extern.java.Log;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.train.TrainPlatform;

@Log
public class DogTrainPlatformImpl implements TrainPlatform<Dog> {

    private static final boolean IS_TRAINED = true;

    @Override
    public Dog toTrain(Dog dog) {
        log.info("The dog [" + dog.getAge() + "] is trained");
        dog.setTrained(IS_TRAINED);
        return dog;
    }
}
