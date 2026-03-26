package senai.centroweg.weg_workshop_api.domain.ports;

import senai.centroweg.weg_workshop_api.domain.model.Class;

import java.util.List;
import java.util.Optional;

public interface ClassRepositoryPort {

    Class save(Class entity);

    Optional<Class> findById(Integer id);

    List<Class> listAll();
}
