package kursevicius.karolis.dbforms.karoliomodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "nuomos_punktas")
public class RentPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nuomos_punkto_ID_seq")
    @SequenceGenerator(name = "nuomos_punkto_ID_seq")
    @Column(name = "nuomos_punkto_ID")
    Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rentPlace")
    List<RentLicense> rentLicenses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rentPlace")
    List<Car> cars = new ArrayList<>();

    @Column(name = "pavadinimas", unique = true, nullable = false)
    String name;

    @Column(name = "adresas", unique = true, nullable = false)
    String address;
}
