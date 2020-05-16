package ru.leverx.task.runner;

import ru.leverx.task.entity.Aviary;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.entity.ServiceEmployee;
import ru.leverx.task.entity.Vet;
import ru.leverx.task.entity.enumeration.DogAgeType;
import ru.leverx.task.entity.enumeration.DogJob;
import ru.leverx.task.farm.FarmDog;
import ru.leverx.task.service.train.TrainPlatform;
import ru.leverx.task.service.train.impl.DogTrainPlatformImpl;

import java.util.ArrayList;
import java.util.List;

public class Run {

    private static final boolean IS_HEALTHY = true;
    private static final boolean IS_SICK = false;
    private static final boolean IS_FED = true;
    private static final boolean IS_HUNGRY = false;
    private static final boolean IS_TRAINED = true;
    private static final boolean IS_NOT_TRAINED = false;

    public static void main(String[] args) {
        Run run = new Run();
        List<Dog> dogs = run.createDogs();
        TrainPlatform<Dog> dogTrainPlatform = run.createDogTrainPlatform();
        List<Aviary> aviaries = run.createAviaries();
        List<ServiceEmployee> serviceEmployees = run.createServiceEmployees();
        List<Vet> veterinarians = run.createVeterinarians();
        FarmDog farmDog = run.createFarmDog(dogs, dogTrainPlatform, aviaries, serviceEmployees, veterinarians);
        System.out.println("Start a day...");



        System.out.println("End a day.");
    }

    private List<Dog> createDogs() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(createDog(1, DogAgeType.YOUNG, DogJob.NONE, IS_HEALTHY, IS_HUNGRY, IS_NOT_TRAINED));
        dogs.add(createDog(3, DogAgeType.ADULT, DogJob.EMERGENCY, IS_SICK, IS_FED, IS_TRAINED));
        dogs.add(createDog(5, DogAgeType.ADULT, DogJob.POLICE, IS_HEALTHY, IS_HUNGRY, IS_TRAINED));
        dogs.add(createDog(7, DogAgeType.ADULT, DogJob.EMERGENCY, IS_SICK, IS_FED, IS_TRAINED));
        dogs.add(createDog(9, DogAgeType.OLD, DogJob.NONE, IS_HEALTHY, IS_HUNGRY, IS_TRAINED));

        return dogs;
    }

    private Dog createDog(int age, DogAgeType dogAgeType, DogJob dogJob,
                          boolean isHealthy, boolean isFed,
                          boolean isTrained) {
        return Dog.builder()
                .buildAge(age)
                .buildDogAgeType(dogAgeType)
                .buildDogJob(dogJob)
                .buildHealthy(isHealthy)
                .buildFed(isFed)
                .buildTrained(isTrained)
                .build();
    }

    private TrainPlatform<Dog> createDogTrainPlatform() {
        return new DogTrainPlatformImpl();
    }

    private List<Aviary> createAviaries() {

        return null;
    }

    private List<ServiceEmployee> createServiceEmployees() {

        return null;
    }

    private List<Vet> createVeterinarians() {

        return null;
    }

    private FarmDog createFarmDog(List<Dog> dogs, TrainPlatform<Dog> dogTrainPlatform,
                                  List<Aviary> aviaries, List<ServiceEmployee> serviceEmployees,
                                  List<Vet> veterinarians) {
        FarmDog farmDog = new FarmDog();
        farmDog.setDogs(dogs);
        farmDog.setTrainPlatform(dogTrainPlatform);
        farmDog.setAviaries(aviaries);
        farmDog.setServiceEmployees(serviceEmployees);
        farmDog.setVeterinarians(veterinarians);
        return farmDog;
    }
}
