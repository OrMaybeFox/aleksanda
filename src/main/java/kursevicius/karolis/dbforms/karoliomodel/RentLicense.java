package kursevicius.karolis.dbforms.karoliomodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "nuomos_leidimas")
public class RentLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nuomos_leidimo_ID_seq")
    @SequenceGenerator(name = "nuomos_leidimo_ID_seq")
    @Column(name = "nuomos_leidimo_ID")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "nuomos_punkto_ID", referencedColumnName = "nuomos_punkto_ID")
    RentPlace rentPlace;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "kliento_ID", referencedColumnName = "kliento_ID")
    Klientas customer;
}
