package senai.centroweg.weg_workshop_api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;
import senai.centroweg.weg_workshop_api.domain.model.Class;
import senai.centroweg.weg_workshop_api.domain.model.User;
import senai.centroweg.weg_workshop_api.domain.ports.ClassRepositoryPort;
import senai.centroweg.weg_workshop_api.domain.ports.UserRepositoryPort;

import java.util.List;

@Service
public class ClassService {

    private final ClassRepositoryPort classRepository;
    private final UserRepositoryPort userRepository;

    @Autowired
    public ClassService(ClassRepositoryPort classRepository, UserRepositoryPort userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    public Class createClass(String name, Integer teacherId, List<Integer> studentIds) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Only teachers can own a class"));

        if (teacher.getUserType() != UserType.TEACHER) {
            throw new RuntimeException("Only teachers can own a class");
        }

        List<User> students = studentIds.stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + id)))
                .toList();

        Class newClass = new Class(name, teacher, students);

        return classRepository.save(newClass);
    }

    public List<Class> listAll() {
        return classRepository.listAll();
    }
}
