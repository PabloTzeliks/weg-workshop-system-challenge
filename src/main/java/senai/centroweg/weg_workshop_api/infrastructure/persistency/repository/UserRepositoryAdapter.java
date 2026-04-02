package senai.centroweg.weg_workshop_api.infrastructure.persistency.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import senai.centroweg.weg_workshop_api.domain.model.User;
import senai.centroweg.weg_workshop_api.domain.ports.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
