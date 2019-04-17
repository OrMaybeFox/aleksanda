package kursevicius.karolis.dbforms.service;

import kursevicius.karolis.dbforms.bean.CreateCarRequest;
import kursevicius.karolis.dbforms.bean.SearchCarsRequest;
import kursevicius.karolis.dbforms.bean.UpdateCarRequest;
import kursevicius.karolis.dbforms.karoliomodel.Car;
import kursevicius.karolis.dbforms.karoliomodel.CarModel;
import kursevicius.karolis.dbforms.karoliomodel.RentPlace;
import kursevicius.karolis.dbforms.repository.CarModelRepository;
import kursevicius.karolis.dbforms.repository.CarRepository;
import kursevicius.karolis.dbforms.repository.RentPlaceRepository;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Value
//@Service
public class CarServiceImpl /*implements CarService */{
  /*  CarRepository carRepository;
    RentPlaceRepository rentPlaceRepository;
    CarModelRepository carModelRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car getById(Long id) {
        return carRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car createCar(CreateCarRequest request) {
        Car car = new Car();
        CarModel carModel = carModelRepository.getOne(request.getCarModelId());
        RentPlace rentPlace = rentPlaceRepository.getOne(request.getRentPlaceId());
        car.setRentPlace(rentPlace);
        rentPlace.getCars().add(car);
        car.setCarModel(carModel);
        carModel.getCars().add(car);
        car.setRegistrationDate(request.getRegistrationDate());
        car.setPlateNumber(request.getPlateNumber());
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(UpdateCarRequest request) {
        Car car = carRepository.getOne(request.getCarId());
        CarModel carModel = carModelRepository.getOne(request.getCarModelId());
        RentPlace rentPlace = rentPlaceRepository.getOne(request.getRentPlaceId());
        if (!car.getRentPlace().getId().equals(rentPlace.getId())) {
            car.setRentPlace(rentPlace);
            rentPlace.getCars().add(car);
        }
        if (!car.getCarModel().getId().equals(carModel.getId())) {
            car.setCarModel(carModel);
            carModel.getCars().add(car);
        }
        car.setRegistrationDate(request.getRegistrationDate());
        car.setPlateNumber(request.getPlateNumber());
        return carRepository.save(car);
    }

    @Override
    public List<Car> searchCars(SearchCarsRequest request) {
        return carRepository.findAll().stream()
                .filter(car -> plateNumberMatches(car, request.getPlateNumber()))
                .filter(car -> rentPlaceIdMatches(car, request.getRentPlaceId()))
                .filter(car -> carModelIdMatches(car, request.getCarModelId()))
                .filter(car -> dateFromIsEqualOrSmaller(car, request.getRegistrationDateFrom()))
                .filter(car -> dateToIsBigger(car, request.getRegistrationDateTo()))
                .collect(Collectors.toList());
    }

    private boolean carModelIdMatches(Car car, Long carModelId) {
        if (carModelId != null)
            return carModelId.equals(car.getCarModel().getId());
        return true;
    }

    private boolean dateToIsBigger(Car car, Date dateTo) {
        if (dateTo != null)
            return dateTo.compareTo(car.getRegistrationDate()) > 0;
        return true;
    }

    private boolean dateFromIsEqualOrSmaller(Car car, Date dateFrom) {
        if (dateFrom != null)
            return dateFrom.compareTo(car.getRegistrationDate()) <= 0;
        return true;
    }

    private boolean rentPlaceIdMatches(Car car, Long rentPlaceId) {
        if (rentPlaceId != null)
            return rentPlaceId.equals(car.getRentPlace().getId());
        return true;
    }

    private boolean plateNumberMatches(Car car, String plateNumber) {
        if (!StringUtils.isEmpty(plateNumber))
            return plateNumber.equalsIgnoreCase(car.getPlateNumber());
        return true;
    }*/
}
