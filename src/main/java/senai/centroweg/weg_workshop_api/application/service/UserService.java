package senai.centroweg.weg_workshop_api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;
import senai.centroweg.weg_workshop_api.domain.model.User;
import senai.centroweg.weg_workshop_api.domain.ports.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    @Autowired
    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User createUser(String name, UserType userType) {
        User user = new User(name, userType);
        return userRepositoryPort.save(user);
    }

    public List<User> listAll() {
        return userRepositoryPort.listAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepositoryPort.findById(id);
    }
}
