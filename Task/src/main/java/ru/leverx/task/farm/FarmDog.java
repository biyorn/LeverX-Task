package ru.leverx.task.farm;

import lombok.Builder;
import lombok.extern.java.Log;
import ru.leverx.task.entity.Aviary;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.entity.enumeration.DogAgeType;
import ru.leverx.task.service.employee.ServiceEmployee;
import ru.leverx.task.service.employee.VetService;
import ru.leverx.task.service.job.JobService;
import ru.leverx.task.service.train.TrainPlatform;

import java.util.List;

@Log
@Builder
public class FarmDog {

    private List<Dog> dogs;
    private List<Aviary> aviaries;
    private TrainPlatform<Dog> trainPlatform;
    private ServiceEmployee<Dog, Aviary> serviceEmployee;
    private VetService<Dog> vet;
    private JobService<Dog> jobService;

    public void feedDogs() {
        log.info("Dogs are eating");
        dogs.forEach(serviceEmployee::toFeed);
    }

    public void inspectDogs() {
        log.info("Dogs are inspecting at vet");
        dogs.stream()
                .filter(dog -> !dog.isHealthy())
                .forEach(vet::toTreat);
    }

    public void cleanAviaries() {
        log.info("Aviaries are cleaning");
        aviaries.forEach(serviceEmployee::toClean);
    }

    public void goJob() {
        log.info("Some dogs went to work");
        dogs.stream()
                .filter(dog -> dog.getDogAgeType().equals(DogAgeType.ADULT))
                .forEach(jobService::toWork);
    }

}
