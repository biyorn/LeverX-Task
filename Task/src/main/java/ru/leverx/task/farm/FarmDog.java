package ru.leverx.task.farm;

import lombok.Data;
import ru.leverx.task.entity.Aviary;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.service.train.TrainPlatform;
import ru.leverx.task.service.train.employee.VetService;
import ru.leverx.task.service.train.employee.impl.FarmDogServiceEmployeeImpl;

import java.util.List;

@Data
public class FarmDog {

    private List<Dog> dogs;
    private TrainPlatform<Dog> trainPlatform;
    private List<Aviary> aviaries;
    private FarmDogServiceEmployeeImpl farmDogServiceEmployeeImpl;
    private VetService<Dog> vet;

}
