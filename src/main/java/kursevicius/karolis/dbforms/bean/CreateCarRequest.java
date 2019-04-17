package kursevicius.karolis.dbforms.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCarRequest {
    String plateNumber;
    Date registrationDate;
    Long carModelId;
    Long rentPlaceId;
}
