package kursevicius.karolis.dbforms.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tiekejas")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tiekejo_nr_seq")
    @SequenceGenerator(name = "tiekejo_nr_seq")
    @Column(name = "tiekejo_numeris")
    Long id;

    @Column(name = "pavadinimas", nullable = false)
    String description;
}
