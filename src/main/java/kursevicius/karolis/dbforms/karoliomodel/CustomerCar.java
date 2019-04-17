package kursevicius.karolis.dbforms.karoliomodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "nuomojasi")
public class CustomerCar {
    @EmbeddedId
    CustomerCarId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("categoryId")
    Klientas customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("productId")
    Car car;

    @Column(name = "nuo", nullable = false)
    Date fromDate;

    @Column(name = "iki")
    Date toDate;

    @Column(name = "grazino")
    Date returnDate;
}
