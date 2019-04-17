package kursevicius.karolis.dbforms.karoliomodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class CustomerCarId implements Serializable {
    @Column(name = "kliento_ID")
    Long customerId;

    @Column(name = "automobilio_ID")
    Long carId;

    public CustomerCarId() {
    }

    public CustomerCarId(Long customerId, Long carId) {
        this.customerId = customerId;
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCarId that = (CustomerCarId) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(carId, that.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, carId);
    }
}
