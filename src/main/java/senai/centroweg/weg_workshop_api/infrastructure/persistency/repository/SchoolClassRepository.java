package senai.centroweg.weg_workshop_api.infrastructure.persistency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.centroweg.weg_workshop_api.domain.model.SchoolClass;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    List<SchoolClass> findByTeacherId(Integer teacherId);
}
