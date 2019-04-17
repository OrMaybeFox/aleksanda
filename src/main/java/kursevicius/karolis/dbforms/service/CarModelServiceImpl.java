package kursevicius.karolis.dbforms.service;

import kursevicius.karolis.dbforms.karoliomodel.CarModel;
import kursevicius.karolis.dbforms.repository.CarModelRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Value
@Service
public class CarModelServiceImpl implements CarModelService {
    CarModelRepository carModelRepository;

    @Override
    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }
}
