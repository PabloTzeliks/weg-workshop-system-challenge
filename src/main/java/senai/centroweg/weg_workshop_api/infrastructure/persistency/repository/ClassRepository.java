package senai.centroweg.weg_workshop_api.infrastructure.persistency.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<senai.centroweg.weg_workshop_api.domain.model.Class, Integer> {
    List<senai.centroweg.weg_workshop_api.domain.model.Class> findByTeacherId(Integer teacherId);
}
