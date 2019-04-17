package kursevicius.karolis.dbforms.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "sudaro")
public class Sudaro implements Serializable {
//    @EmbeddedId
//    SudaroId id;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @MapsId("productId")
    @JoinColumn(name = "prekes_nr", referencedColumnName = "prekes_nr")
    Preke product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @MapsId("orderId")
    @JoinColumn(name = "uzsakymo_nr", referencedColumnName = "uzsakymo_nr", columnDefinition = "numeric")
    Uzsakymas order;

    @Column(name = "kiekis", nullable = false)
    int quantity;

    @Column(name = "kaina", columnDefinition = "Kaina")
    BigDecimal price;

    public Sudaro() {
    }

    public Sudaro(Preke product, Uzsakymas order) {
        this.product = product;
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sudaro sudaro = (Sudaro) o;
        return quantity == sudaro.quantity &&
                Objects.equals(product, sudaro.product) &&
                Objects.equals(order, sudaro.order) &&
                Objects.equals(price, sudaro.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order, quantity, price);
    }
}
