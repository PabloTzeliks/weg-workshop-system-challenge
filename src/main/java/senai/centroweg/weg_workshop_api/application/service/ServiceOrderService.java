package senai.centroweg.weg_workshop_api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.centroweg.weg_workshop_api.domain.enums.StatusSO;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;
import senai.centroweg.weg_workshop_api.domain.model.ServiceOrder;
import senai.centroweg.weg_workshop_api.domain.model.User;
import senai.centroweg.weg_workshop_api.domain.ports.ServiceOrderRepository;
import senai.centroweg.weg_workshop_api.domain.ports.UserRepository;

import java.util.List;

@Service
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final UserRepository userRepository;

    @Autowired
    public ServiceOrderService(ServiceOrderRepository serviceOrderRepository, UserRepository userRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.userRepository = userRepository;
    }

    public ServiceOrder openServiceOrder(Integer teacherId, String equipment, String reportedDefect, List<Integer> studentIds) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (teacher.getUserType() != UserType.TEACHER) {
            throw new RuntimeException("Only teachers can open service orders");
        }

        List<User> students = studentIds.stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + id)))
                .toList();

        ServiceOrder serviceOrder = new ServiceOrder(equipment, reportedDefect, teacher, students);
        serviceOrder.setStatus(StatusSO.OPEN);
        return serviceOrderRepository.save(serviceOrder);
    }

    public ServiceOrder executeServiceOrder(Integer serviceOrderId, Integer studentId, String usedMaterials, String technicalConclusion) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new RuntimeException("Service order not found"));

        if (serviceOrder.getStatus() != StatusSO.OPEN) {
            throw new RuntimeException("Service order is not open for execution");
        }

        if (!serviceOrder.getStudents().stream().anyMatch(student -> student.getId().equals(studentId))) {
            throw new RuntimeException("Student not assigned to this service order");
        }

        serviceOrder.setUsedMaterials(usedMaterials);
        serviceOrder.setTechnicalConclusion(technicalConclusion);
        serviceOrder.setStatus(StatusSO.WAITING_APPROVAL);

        return serviceOrderRepository.save(serviceOrder);
    }

    public ServiceOrder approveServiceOrder(Integer serviceOrderId, Integer teacherId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new RuntimeException("Service order not found"));

        if (serviceOrder.getStatus() != StatusSO.WAITING_APPROVAL) {
            throw new RuntimeException("Service order is not waiting for approval");
        }

        if (!serviceOrder.getResponsibleTeacher().getId().equals(teacherId)) {
            throw new RuntimeException("Only the responsible teacher can approve");
        }

        serviceOrder.setStatus(StatusSO.CONCLUDED);

        return serviceOrderRepository.save(serviceOrder);
    }
}
