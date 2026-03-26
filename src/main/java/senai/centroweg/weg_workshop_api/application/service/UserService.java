package senai.centroweg.weg_workshop_api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.centroweg.weg_workshop_api.application.dto.request.UserRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.UserResponseDTO;
import senai.centroweg.weg_workshop_api.application.mapper.UserMapper;
import senai.centroweg.weg_workshop_api.domain.model.User;
import senai.centroweg.weg_workshop_api.domain.ports.UserRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepositoryPort userRepositoryPort, UserMapper userMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO request) {

        User user = userMapper.toEntity(request);
        User savedUser = userRepositoryPort.save(user);

        return userMapper.toResponseDTO(savedUser);
    }

    public List<UserResponseDTO> listAll() {

        return userRepositoryPort.listAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(Integer id) {

        return userRepositoryPort.findById(id)
                .map(userMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}