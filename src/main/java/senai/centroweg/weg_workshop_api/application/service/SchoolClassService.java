package senai.centroweg.weg_workshop_api.application.service;

import org.springframework.stereotype.Service;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;
import senai.centroweg.weg_workshop_api.domain.model.SchoolClass;
import senai.centroweg.weg_workshop_api.domain.model.User;
import senai.centroweg.weg_workshop_api.domain.ports.ClassRepositoryPort;
import senai.centroweg.weg_workshop_api.domain.ports.UserRepositoryPort;

import java.util.List;

@Service
public class SchoolClassService {

    private final ClassRepositoryPort classRepository;
    private final UserRepositoryPort userRepository;

    public SchoolClassService(ClassRepositoryPort classRepository, UserRepositoryPort userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    public SchoolClass createClass(String name, Integer teacherId, List<Integer> studentIds) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Only teachers can own a class"));

        if (teacher.getUserType() != UserType.TEACHER) {
            throw new RuntimeException("Only teachers can own a class");
        }

        List<User> students = studentIds.stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + id)))
                .toList();

        SchoolClass newClass = new SchoolClass(name, teacher, students);

        return classRepository.save(newClass);
    }

    public List<SchoolClass> listAll() {
        return classRepository.listAll();
    }
}
