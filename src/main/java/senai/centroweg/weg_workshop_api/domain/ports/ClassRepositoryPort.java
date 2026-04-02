package senai.centroweg.weg_workshop_api.domain.ports;

import senai.centroweg.weg_workshop_api.domain.model.SchoolClass;

import java.util.List;
import java.util.Optional;

public interface ClassRepositoryPort {

    SchoolClass save(SchoolClass entity);

    Optional<SchoolClass> findById(Integer id);

    List<SchoolClass> listAll();
}
