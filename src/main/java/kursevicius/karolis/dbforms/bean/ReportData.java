package kursevicius.karolis.dbforms.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportData {
    String id;
    String date;
    List<CarBrand> carBrands;
    List<CarModel> carModels;
    String totalCarCount;
    String totalCarBrandCount;
    String totalCarModelCount;

    @Getter
    @Setter
    public static class CarBrand {
        String name;
        String modelCount;
        String carCount;
    }

    @Getter
    @Setter
    public static class CarModel {
        String brandName;
        String name;
        String carCount;
    }
}
