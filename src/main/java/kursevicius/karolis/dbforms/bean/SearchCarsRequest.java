package kursevicius.karolis.dbforms.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchCarsRequest {
    Date registrationDateFrom;
    Date registrationDateTo;
    Long rentPlaceId;
    String plateNumber;
    Long carModelId;
}
