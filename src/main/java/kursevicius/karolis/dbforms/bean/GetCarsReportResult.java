package kursevicius.karolis.dbforms.bean;

import kursevicius.karolis.dbforms.karoliomodel.Car;
import kursevicius.karolis.dbforms.karoliomodel.CarModel;
import lombok.Value;

@Value
public class GetCarsReportResult {
    int count;
    CarModel mostPopularCarModel;
    Car newestCar;
    Car oldestCar;
}
