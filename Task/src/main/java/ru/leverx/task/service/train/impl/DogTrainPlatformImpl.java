package ru.leverx.task.service.train.impl;

import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.train.TrainPlatform;

public class DogTrainPlatformImpl implements TrainPlatform<Dog> {

    private static final boolean IS_TRAINED = true;

    @Override
    public Dog toTrain(Dog dog) {
        // some code
        return dog;
    }
}
