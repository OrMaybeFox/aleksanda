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
public class Tiekejas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tiekejo_nr_seq")
    @SequenceGenerator(name = "tiekejo_nr_seq")
    @Column(name = "tiekejo_numeris", columnDefinition = "int")
    Integer id;

    @Column(name = "pavadinimas", nullable = false, columnDefinition = "char")
    String description;
}
