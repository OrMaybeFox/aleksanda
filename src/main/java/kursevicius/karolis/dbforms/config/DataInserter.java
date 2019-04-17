package kursevicius.karolis.dbforms.config;

import kursevicius.karolis.dbforms.karoliomodel.Brand;
import kursevicius.karolis.dbforms.karoliomodel.Car;
import kursevicius.karolis.dbforms.karoliomodel.CarModel;
import kursevicius.karolis.dbforms.karoliomodel.RentPlace;
import kursevicius.karolis.dbforms.repository.RentPlaceRepository;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Value
//@Component
public class DataInserter implements CommandLineRunner {
    private final long beginTime = Timestamp.valueOf("2000-01-01 00:00:00").getTime();
    private final long endTime = Timestamp.valueOf("2018-12-31 00:58:00").getTime();

    RentPlaceRepository rentPlaceRepository;

    @Override
    public void run(String... args) {
        insert();
    }

    private void insert() {
        RentPlace vytautinis = createRentPlace("Vytautinis", "Vytauto g. 1");
        RentPlace petrinis = createRentPlace("Petrinis", "Petro g. 2");

        Brand bmw = createBrand("BMW", "E39", "E60", "E90", "E46");
        Brand vw = createBrand("VW", "Golf", "Sharan", "Passat");
        Brand audi = createBrand("Audi", "80", "100");
        bmw.getCarModels().forEach(carModel -> carModel.getCars().forEach(car -> car.setRentPlace(vytautinis)));
        audi.getCarModels().forEach(carModel -> carModel.getCars().forEach(car -> car.setRentPlace(vytautinis)));
        vw.getCarModels().forEach(carModel -> carModel.getCars().forEach(car -> car.setRentPlace(petrinis)));

        vytautinis.getCars().addAll(getCars(bmw));
        vytautinis.getCars().addAll(getCars(audi));
        petrinis.getCars().addAll(getCars(vw));

//        rentPlaceRepository.save(vytautinis);
//        rentPlaceRepository.save(petrinis);
    }

    private List<Car> getCars(Brand brand) {
        List<Car> cars = new ArrayList<>();
        for (CarModel model : brand.getCarModels())
            cars.addAll(model.getCars());
        return cars;
    }

    private RentPlace createRentPlace(String name, String address) {
        RentPlace rentPlace = new RentPlace();
        rentPlace.setName(name);
        rentPlace.setAddress(address);
        return rentPlace;
    }

    private Brand createBrand(String name, String... modelNames) {
        Brand brand = new Brand();
        brand.setName(name);
        brand.setCarModels(createModels(brand, modelNames));
        return brand;
    }

    private List<CarModel> createModels(Brand brand, String... names) {
        List<CarModel> models = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            CarModel model = new CarModel();
            model.setBrand(brand);
            model.setName(names[i]);
            model.setCars(createCars(model, i));
            models.add(model);
        }
        return models;
    }

    private List<Car> createCars(CarModel model, int no) {
        no++;
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < no; i++) {
            Car car = new Car();
            car.setPlateNumber(generateRandomPlateNumber());
            car.setRegistrationDate(getRandomDate());
            car.setCarModel(model);
            cars.add(car);
        }
        return cars;
    }

    private String generateRandomPlateNumber() {
        return generateRandomPlateLetters() + (ThreadLocalRandom.current().nextInt(900) + 100);
    }

    private String generateRandomPlateLetters() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            char tmp = (char) ('A' + random.nextInt('Z' - 'A'));
            sb.append(tmp);
        }
        return sb.toString();
    }

    private Date getRandomDate() {
        long diff = endTime - beginTime + 1;
        return new Date(beginTime + (long) (Math.random() * diff));
    }
}
