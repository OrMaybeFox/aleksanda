package kursevicius.karolis.dbforms.service;

import kursevicius.karolis.dbforms.bean.ReportData;
import kursevicius.karolis.dbforms.karoliomodel.Brand;
import kursevicius.karolis.dbforms.karoliomodel.Car;
import kursevicius.karolis.dbforms.karoliomodel.CarModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ReportDataAssembler {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public ReportData assembleReportData(List<Car> cars) {
        ReportData reportData = new ReportData();
        reportData.setId(UUID.randomUUID().toString());
        reportData.setDate(toDateString(new Date()));
        reportData.setTotalCarCount(String.valueOf(cars.size()));
        Map<CarModel, Integer> carModelCarToCount = new HashMap<>();
        Map<Brand, Set<Car>> brandToCars = new HashMap<>();
        for (Car car : cars) {
            CarModel carModel = car.getCarModel();
            carModelCarToCount.put(carModel, carModelCarToCount.getOrDefault(carModel, 0) + 1);
            Brand brand = carModel.getBrand();
            brandToCars.computeIfAbsent(brand, b -> new HashSet<>()).add(car);
        }
        reportData.setTotalCarBrandCount(String.valueOf(brandToCars.size()));
        reportData.setTotalCarModelCount(String.valueOf(carModelCarToCount.size()));
        reportData.setCarModels(createCarModelsFromMap(carModelCarToCount));
        reportData.setCarBrands(createCarBrandsFromMap(brandToCars));
        return reportData;
    }

    private List<ReportData.CarBrand> createCarBrandsFromMap(Map<Brand, Set<Car>> brandToCarCount) {
        return brandToCarCount.entrySet().stream()
                .map(this::toReportDataCarBrand)
                .collect(Collectors.toList());
    }

    private ReportData.CarBrand toReportDataCarBrand(Map.Entry<Brand, Set<Car>> entry) {
        ReportData.CarBrand result = new ReportData.CarBrand();
        result.setName(entry.getKey().getName());
        result.setCarCount(String.valueOf(entry.getValue().size()));
        int modelCount = entry.getValue().stream()
                .map(Car::getCarModel)
                .collect(Collectors.toSet())
                .size();
        result.setModelCount(String.valueOf(modelCount));
        return result;
    }

    private List<ReportData.CarModel> createCarModelsFromMap(Map<CarModel, Integer> carModelToCount) {
        return carModelToCount.entrySet().stream()
                .map(this::toReportDataCarModel)
                .collect(Collectors.toList());
    }

    private ReportData.CarModel toReportDataCarModel(Map.Entry<CarModel, Integer> entry) {
        ReportData.CarModel result = new ReportData.CarModel();
        result.setBrandName(entry.getKey().getBrand().getName());
        result.setName(entry.getKey().getName());
        result.setCarCount(String.valueOf(entry.getValue()));
        return result;
    }

    private String toDateString(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }
}
