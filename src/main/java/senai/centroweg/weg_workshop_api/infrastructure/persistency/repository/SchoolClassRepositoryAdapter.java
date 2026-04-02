package senai.centroweg.weg_workshop_api.infrastructure.persistency.repository;

import org.springframework.stereotype.Repository;
import senai.centroweg.weg_workshop_api.domain.model.SchoolClass;
import senai.centroweg.weg_workshop_api.domain.ports.ClassRepositoryPort;

import java.util.List;
import java.util.Optional;

@Repository
public class SchoolClassRepositoryAdapter implements ClassRepositoryPort {

    private final SchoolClassRepository classRepository;

    public SchoolClassRepositoryAdapter(SchoolClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public SchoolClass save(SchoolClass entity) {
        return classRepository.save(entity);
    }

    @Override
    public Optional<SchoolClass> findById(Integer id) {
        return classRepository.findById(id);
    }

    @Override
    public List<SchoolClass> listAll() {
        return classRepository.findAll();
    }
}
