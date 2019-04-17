package kursevicius.karolis.dbforms.bean;

import lombok.Value;

@Value
public class CarDto {
    String carId;
    String registrationDate;
    String plateNumber;
    String modelId;
    String modelName;
    String brandId;
    String brandName;
    String rentPlaceId;
    String rentPlaceName;
    String rentPlaceAddress;
}
