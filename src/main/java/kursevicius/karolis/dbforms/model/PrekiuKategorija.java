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
@Table(name = "prekiu_kategorija")
public class PrekiuKategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kategorijos_id_seq")
    @SequenceGenerator(name = "kategorijos_id_seq")
    @Column(name = "kategorijos_id", columnDefinition = "char")
    String id;

    @Column(name = "zanras", nullable = false)
    String description;
}
