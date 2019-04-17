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
//@Table(name = "klientas")
public class Klientas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kliento_ID_seq")
    @SequenceGenerator(name = "kliento_ID_seq")
    @Column(name = "kliento_ID")
    Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer", optional = false)
    RentLicense rentLicense;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CustomerCar> cars = new ArrayList<>();

    @Column(name = "asmens_kodas", unique = true, nullable = false)
    String legalCode;

    @Column(name = "kliento_vardas", nullable = false)
    String firstName;

    @Column(name = "kliento_pavarde", nullable = false)
    String lastName;
}
