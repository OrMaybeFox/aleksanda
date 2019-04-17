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
//@Table(name = "marke")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "markes_ID_seq")
    @SequenceGenerator(name = "markes_ID_seq")
    @Column(name = "markes_ID")
    Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    List<CarModel> carModels = new ArrayList<>();

    @Column(name = "markes_pavadinimas", unique = true, nullable = false)
    String name;
}
