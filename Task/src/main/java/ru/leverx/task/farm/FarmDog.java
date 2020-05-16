package ru.leverx.task.farm;

import lombok.Data;
import ru.leverx.task.entity.Aviary;
import ru.leverx.task.entity.Dog;
import ru.leverx.task.entity.ServiceEmployee;
import ru.leverx.task.entity.Vet;
import ru.leverx.task.service.train.TrainPlatform;

import java.util.List;

@Data
public class FarmDog {

    private List<Dog> dogs;
    private TrainPlatform<Dog> trainPlatform;
    private List<Aviary> aviaries;
    private List<ServiceEmployee> serviceEmployees;
    private List<Vet> veterinarians;

}
