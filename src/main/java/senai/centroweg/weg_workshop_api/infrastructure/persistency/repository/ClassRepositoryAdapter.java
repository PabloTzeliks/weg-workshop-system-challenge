package senai.centroweg.weg_workshop_api.infrastructure.persistency.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import senai.centroweg.weg_workshop_api.domain.model.Class;
import senai.centroweg.weg_workshop_api.domain.ports.ClassRepositoryPort;

import java.util.List;

@Repository
public class ClassRepositoryAdapter implements ClassRepositoryPort {

    private final ClassRepository classRepository;

    @Autowired
    public ClassRepositoryAdapter(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Class save(Class entity) {
        return classRepository.save(entity);
    }

    @Override
    public List<Class> listAll() {
        return classRepository.findAll();
    }
}
