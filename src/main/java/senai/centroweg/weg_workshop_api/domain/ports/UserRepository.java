package senai.centroweg.weg_workshop_api.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.centroweg.weg_workshop_api.domain.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
}
