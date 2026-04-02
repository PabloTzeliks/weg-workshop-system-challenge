package senai.centroweg.weg_workshop_api.application.service;

import org.springframework.stereotype.Service;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderApproveRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderExecuteRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.ServiceOrderResponseDTO;
import senai.centroweg.weg_workshop_api.application.mapper.ServiceOrderMapper;
import senai.centroweg.weg_workshop_api.domain.enums.StatusSO;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;
import senai.centroweg.weg_workshop_api.domain.model.ServiceOrder;
import senai.centroweg.weg_workshop_api.domain.model.User;
import senai.centroweg.weg_workshop_api.domain.ports.ServiceOrderRepositoryPort;
import senai.centroweg.weg_workshop_api.domain.ports.UserRepositoryPort;

import java.util.List;

@Service
public class ServiceOrderService {

    private final ServiceOrderRepositoryPort serviceOrderRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ServiceOrderMapper serviceOrderMapper;

    public ServiceOrderService(ServiceOrderRepositoryPort serviceOrderRepositoryPort,
                               UserRepositoryPort userRepositoryPort,
                               ServiceOrderMapper serviceOrderMapper) {

        this.serviceOrderRepositoryPort = serviceOrderRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.serviceOrderMapper = serviceOrderMapper;
    }

    public ServiceOrderResponseDTO openServiceOrder(ServiceOrderRequestDTO requestDTO) {
        Integer teacherId = requestDTO.teacherId();
        List<Integer> studentIds = requestDTO.studentIds();

        User teacher = userRepositoryPort.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (teacher.getUserType() != UserType.TEACHER) {
            throw new RuntimeException("Only teachers can open service orders");
        }

        List<User> students = studentIds.stream()
                .map(id -> userRepositoryPort.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + id)))
                .toList();

        ServiceOrder serviceOrder = serviceOrderMapper.toEntity(requestDTO);
        serviceOrder.setResponsibleTeacher(teacher);
        serviceOrder.setStudents(students);
        serviceOrder.setStatus(StatusSO.OPEN);

        return serviceOrderMapper.toResponseDTO(serviceOrderRepositoryPort.save(serviceOrder));
    }

    public ServiceOrderResponseDTO executeServiceOrder(ServiceOrderExecuteRequestDTO requestDTO) {
        Integer serviceOrderId = requestDTO.serviceOrderId();
        Integer studentId = requestDTO.studentId();
        String usedMaterials = requestDTO.usedMaterials();
        String technicalConclusion = requestDTO.technicalConclusion();

        ServiceOrder serviceOrder = serviceOrderRepositoryPort.findById(serviceOrderId)
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

        return serviceOrderMapper.toResponseDTO(serviceOrderRepositoryPort.save(serviceOrder));
    }

    public ServiceOrderResponseDTO approveServiceOrder(ServiceOrderApproveRequestDTO requestDTO) {
        Integer serviceOrderId = requestDTO.serviceOrderId();
        Integer teacherId = requestDTO.teacherId();

        ServiceOrder serviceOrder = serviceOrderRepositoryPort.findById(serviceOrderId)
                .orElseThrow(() -> new RuntimeException("Service order not found"));

        if (serviceOrder.getStatus() != StatusSO.WAITING_APPROVAL) {
            throw new RuntimeException("Service order is not waiting for approval");
        }

        if (!serviceOrder.getResponsibleTeacher().getId().equals(teacherId)) {
            throw new RuntimeException("Only the responsible teacher can approve");
        }

        serviceOrder.setStatus(StatusSO.CONCLUDED);

        return serviceOrderMapper.toResponseDTO(serviceOrderRepositoryPort.save(serviceOrder));
    }
}