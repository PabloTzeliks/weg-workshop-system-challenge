package senai.centroweg.weg_workshop_api.application.mapper;

import org.springframework.stereotype.Component;
import senai.centroweg.weg_workshop_api.application.dto.request.UserRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.UserResponseDTO;
import senai.centroweg.weg_workshop_api.domain.model.User;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getUserType());
    }

    public User toEntity(UserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.name(), userRequestDTO.userType());
    }
}
