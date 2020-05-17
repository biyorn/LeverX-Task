package ru.leverx.task.service.config;

import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.employee.VetService;
import ru.leverx.task.service.employee.impl.DogVetServiceImpl;
import ru.leverx.task.service.employee.impl.FarmDogServiceEmployeeImpl;
import ru.leverx.task.service.job.JobService;
import ru.leverx.task.service.job.impl.DogJobServiceImpl;
import ru.leverx.task.service.train.TrainPlatform;
import ru.leverx.task.service.train.impl.DogTrainPlatformImpl;

public class ServiceConfig {

    public TrainPlatform<Dog> createTrainPlatform() {
        return new DogTrainPlatformImpl();
    }

    public FarmDogServiceEmployeeImpl createServiceEmployee() {
        return new FarmDogServiceEmployeeImpl();
    }

    public VetService<Dog> createVet() {
        return new DogVetServiceImpl();
    }

    public JobService<Dog> createJobService() {
        return new DogJobServiceImpl();
    }
}
