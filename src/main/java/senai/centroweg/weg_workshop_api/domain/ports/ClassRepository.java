package senai.centroweg.weg_workshop_api.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Integer> {
    List<Class> findByTeacherId(Integer teacherId);
}
