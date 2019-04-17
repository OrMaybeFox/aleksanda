package kursevicius.karolis.dbforms.service;

import kursevicius.karolis.dbforms.bean.CreateCarRequest;
import kursevicius.karolis.dbforms.bean.SearchCarsRequest;
import kursevicius.karolis.dbforms.bean.UpdateCarRequest;
import kursevicius.karolis.dbforms.karoliomodel.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car getById(Long id);

    void delete(Long id);

    Car createCar(CreateCarRequest request);

    Car updateCar(UpdateCarRequest request);

    List<Car> searchCars(SearchCarsRequest request);
}
