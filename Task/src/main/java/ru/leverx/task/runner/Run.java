package ru.leverx.task.runner;

import ru.leverx.task.service.config.ServiceConfig;
import ru.leverx.task.entity.Aviary;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.entity.enumeration.DogAgeType;
import ru.leverx.task.entity.enumeration.DogJob;
import ru.leverx.task.farm.FarmDog;
import ru.leverx.task.service.employee.ServiceEmployee;
import ru.leverx.task.service.employee.VetService;
import ru.leverx.task.service.job.JobService;
import ru.leverx.task.service.train.TrainPlatform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Run {

    private static final boolean IS_HEALTHY = true;
    private static final boolean IS_SICK = false;
    private static final boolean IS_HUNGRY = false;
    private static final boolean IS_TRAINED = true;
    private static final boolean IS_NOT_TRAINED = false;

    public static void main(String[] args) {
        Run run = new Run();
        FarmDog farmDog = run.createFarmDog();

        System.out.println("Start a day...");

        farmDog.feedDogs();
        farmDog.inspectDogs();
        farmDog.cleanAviaries();
        farmDog.goJob();
        farmDog.feedDogs();

        System.out.println("End a day.");
    }

    private FarmDog createFarmDog() {
        Run run = new Run();
        List<Dog> dogs = run.createDogs();
        List<Aviary> aviaries = run.createAviaries(dogs);
        ServiceConfig serviceConfig = new ServiceConfig();
        TrainPlatform<Dog> trainPlatform = serviceConfig.createTrainPlatform();
        ServiceEmployee<Dog, Aviary> serviceEmployee = serviceConfig.createServiceEmployee();
        VetService<Dog> vet = serviceConfig.createVet();
        JobService<Dog> jobService = serviceConfig.createJobService();

        return FarmDog.builder()
                .dogs(dogs)
                .aviaries(aviaries)
                .trainPlatform(trainPlatform)
                .serviceEmployee(serviceEmployee)
                .vet(vet)
                .jobService(jobService)
                .build();
    }

    private List<Dog> createDogs() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(createDog(1, DogAgeType.YOUNG, DogJob.NONE, IS_HEALTHY, IS_HUNGRY, IS_NOT_TRAINED));
        dogs.add(createDog(3, DogAgeType.ADULT, DogJob.EMERGENCY, IS_SICK, IS_HUNGRY, IS_TRAINED));
        dogs.add(createDog(5, DogAgeType.ADULT, DogJob.POLICE, IS_HEALTHY, IS_HUNGRY, IS_TRAINED));
        dogs.add(createDog(7, DogAgeType.ADULT, DogJob.EMERGENCY, IS_SICK, IS_HUNGRY, IS_TRAINED));
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
                .buildDogJob(dogJob)
                .buildHealthy(isHealthy)
                .buildFed(isFed)
                .buildTrained(isTrained)
                .build();
    }

    private List<Aviary> createAviaries(List<Dog> dogs) {
        return dogs.stream()
                .map(Aviary::new)
                .collect(Collectors.toList());
    }
}
