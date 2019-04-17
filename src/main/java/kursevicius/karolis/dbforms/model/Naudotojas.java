package kursevicius.karolis.dbforms.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "naudotojas")
public class Naudotojas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kliento_nr_seq")
    @SequenceGenerator(name = "kliento_nr_seq")
    @Column(name = "kliento_nr")
    BigDecimal id;

    @Column(name = "vardas", columnDefinition = "char", nullable = false)
    String firstName;

    @Column(name = "pavarde", columnDefinition = "char", nullable = false)
    String lastName;

    @Column(name = "amzius", columnDefinition = "tinyint", nullable = false)
    int year;
}
