package kursevicius.karolis.dbforms.service;

import kursevicius.karolis.dbforms.karoliomodel.RentPlace;
import kursevicius.karolis.dbforms.repository.RentPlaceRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Value
@Service
public class RentPlaceServiceImpl implements RentPlaceService {
    RentPlaceRepository rentPlaceRepository;

    @Override
    public List<RentPlace> findAll() {
        return rentPlaceRepository.findAll();
    }
}
