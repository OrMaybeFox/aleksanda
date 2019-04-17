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
//@Table(name = "modelis")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modelio_ID_seq")
    @SequenceGenerator(name = "modelio_ID_seq")
    @Column(name = "modelio_ID")
    Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carModel")
    List<Car> cars = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "markes_ID", referencedColumnName = "markes_ID")
    Brand brand;

    @Column(name = "modelio_pavadinimas", nullable = false)
    String name;
}
