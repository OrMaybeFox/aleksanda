package kursevicius.karolis.dbforms.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "uzsakymas")
public class Uzsakymas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uzsakymo_nr_seq")
    @SequenceGenerator(name = "uzsakymo_nr_seq")
    @Column(name = "uzsakymo_nr", columnDefinition = "numeric")
    Long id;

    @Column(name = "mokestis", nullable = false, columnDefinition = "money")
    BigDecimal tax;

    @Column(name = "data", nullable = false, columnDefinition = "Data")
    Date data;

    @Column(name = "pristatymas", nullable = false)
    Date year;
}
