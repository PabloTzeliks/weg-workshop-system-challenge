package senai.centroweg.weg_workshop_api.application.mapper;

import org.springframework.stereotype.Component;
import senai.centroweg.weg_workshop_api.domain.model.Class;
import senai.centroweg.weg_workshop_api.application.dto.ClassRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.ClassResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassMapper {

    public ClassResponseDTO toResponseDTO(Class clazz) {
        return new ClassResponseDTO(clazz.getId(), clazz.getName(), clazz.getTeacher(), clazz.getStudents());
    }

    public Class toEntity(ClassRequestDTO classRequestDTO) {
        // TODO: Fetch the teacher entity from the database using teacherId
        return new Class(classRequestDTO.name(), null, null); // Replace null with actual teacher and student entities
    }

    public List<ClassResponseDTO> toResponseDTOList(List<Class> classes) {
        return classes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
