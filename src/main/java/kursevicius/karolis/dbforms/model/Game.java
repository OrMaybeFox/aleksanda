package kursevicius.karolis.dbforms.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "preke_zaidimas")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prekes_nr_seq")
    @SequenceGenerator(name = "prekes_nr_seq")
    @Column(name = "prekes_nr")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tiekejo_numeris", referencedColumnName = "tiekejo_numeris")
    Supplier supplier;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "priskiriamos",
            joinColumns = @JoinColumn(name = "prekes_nr"),
            inverseJoinColumns = @JoinColumn(name = "kategorijos_id")
    )
    List<GameCategory> categories;

    @Column(name = "kaina", nullable = false)
    BigDecimal price;

    @Column(name = "kiekis", nullable = false)
    int quantity;

    @Column(name = "kategorija", nullable = false)
    String categoryDescription;

    @Column(name = "metai", nullable = false)
    int year;

    @Column(name = "amziaus_cenzas", nullable = false)
    int censerAge;

    @Column(name = "aprasymas", nullable = false)
    String description;
}
