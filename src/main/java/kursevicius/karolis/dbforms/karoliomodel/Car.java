package kursevicius.karolis.dbforms.karoliomodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "automobilis")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "automobilio_ID_seq")
    @SequenceGenerator(name = "automobilio_ID_seq")
    @Column(name = "automobilio_ID")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "modelio_ID", referencedColumnName = "modelio_ID")
    CarModel carModel;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "nuomos_punkto_ID", referencedColumnName = "nuomos_punkto_ID")
    RentPlace rentPlace;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CustomerCar> customers = new ArrayList<>();

    @Column(name = "registracijos_data", nullable = false)
    Date registrationDate;

    @Column(name = "valstybinis_numeris", unique = true, nullable = false)
    String plateNumber;
}
