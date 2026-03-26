package senai.centroweg.weg_workshop_api.application.mapper;

import org.springframework.stereotype.Component;
import senai.centroweg.weg_workshop_api.application.dto.request.ClassRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.ClassResponseDTO;
import senai.centroweg.weg_workshop_api.domain.model.Class;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassMapper {

    public ClassResponseDTO toResponseDTO(Class clazz) {
        return new ClassResponseDTO(clazz.getId(), clazz.getName(), clazz.getTeacher(), clazz.getStudents());
    }

    public Class toEntity(ClassRequestDTO classRequestDTO) {
        return new Class(classRequestDTO.name(), null, null);
    }

    public List<ClassResponseDTO> toResponseDTOList(List<Class> classes) {
        return classes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
