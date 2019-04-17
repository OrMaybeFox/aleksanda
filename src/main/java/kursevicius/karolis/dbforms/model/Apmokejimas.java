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
//@Table(name = "apmokejimas")
public class Apmokejimas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cekio_nr_seq")
    @SequenceGenerator(name = "cekio_nr_seq")
    @Column(name = "cekio_nr")
    Integer id;

    @Column(name = "date", nullable = false)
    Date date;

    @Column(name = "suma", nullable = false, columnDefinition = "money")
    BigDecimal amount;
}
