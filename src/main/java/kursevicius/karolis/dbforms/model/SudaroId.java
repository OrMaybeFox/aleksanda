package kursevicius.karolis.dbforms.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class SudaroId implements Serializable {
    @Column(name = "prekes_nr")
    BigDecimal productId;

    @Column(name = "uzsakymo_nr")
    Long orderId;

    public SudaroId() {
    }

    public SudaroId(BigDecimal productId, Long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudaroId sudaroId = (SudaroId) o;
        return Objects.equals(productId, sudaroId.productId) &&
                Objects.equals(orderId, sudaroId.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
