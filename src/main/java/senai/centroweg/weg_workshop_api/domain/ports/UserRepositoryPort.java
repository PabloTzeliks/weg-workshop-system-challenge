package senai.centroweg.weg_workshop_api.domain.ports;

import senai.centroweg.weg_workshop_api.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User save(User save);

    Optional<User> findById(Integer id);

    Optional<User> findByName(String name);

    List<User> listAll();
}
