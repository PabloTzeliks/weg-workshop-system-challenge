package senai.centroweg.weg_workshop_api.application.mapper;

import org.springframework.stereotype.Component;
import senai.centroweg.weg_workshop_api.application.dto.request.SchoolClassRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.SchoolClassResponseDTO;
import senai.centroweg.weg_workshop_api.domain.model.SchoolClass;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchoolClassMapper {

    public SchoolClassResponseDTO toResponseDTO(SchoolClass clazz) {
        return new SchoolClassResponseDTO(clazz.getId(), clazz.getName(), clazz.getTeacher(), clazz.getStudents());
    }

    public SchoolClass toEntity(SchoolClassRequestDTO classRequestDTO) {
        return new SchoolClass(classRequestDTO.name(), null, null);
    }

    public List<SchoolClassResponseDTO> toResponseDTOList(List<SchoolClass> classes) {
        return classes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
