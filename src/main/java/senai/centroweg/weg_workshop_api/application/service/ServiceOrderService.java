package senai.centroweg.weg_workshop_api.application.service;

import org.springframework.stereotype.Service;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderApproveRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderExecuteRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.ServiceOrderResponseDTO;
import senai.centroweg.weg_workshop_api.application.mapper.ServiceOrderMapper;
import senai.centroweg.weg_workshop_api.domain.enums.StatusSO;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;
import senai.centroweg.weg_workshop_api.domain.exception.BusinessException;
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
        User teacher = userRepositoryPort.findById(requestDTO.teacherId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        if (teacher.getUserType() != UserType.TEACHER) {
            throw new BusinessException("Apenas professores podem abrir ordens de serviço.");
        }

        List<User> students = requestDTO.studentIds().stream()
                .map(id -> userRepositoryPort.findById(id)
                        .orElseThrow(() -> new BusinessException("Aluno não encontrado: " + id)))
                .toList();

        ServiceOrder serviceOrder = serviceOrderMapper.toEntity(requestDTO);
        serviceOrder.setResponsibleTeacher(teacher);
        serviceOrder.setStudents(students);
        serviceOrder.setStatus(StatusSO.OPEN);

        return serviceOrderMapper.toResponseDTO(serviceOrderRepositoryPort.save(serviceOrder));
    }

    public ServiceOrderResponseDTO executeServiceOrder(ServiceOrderExecuteRequestDTO requestDTO) {
        ServiceOrder serviceOrder = serviceOrderRepositoryPort.findById(requestDTO.serviceOrderId())
                .orElseThrow(() -> new BusinessException("Ordem de serviço não encontrada."));

        serviceOrder.executeOrder(
                requestDTO.studentId(),
                requestDTO.usedMaterials(),
                requestDTO.technicalConclusion()
        );

        return serviceOrderMapper.toResponseDTO(serviceOrderRepositoryPort.save(serviceOrder));
    }

    public ServiceOrderResponseDTO approveServiceOrder(ServiceOrderApproveRequestDTO requestDTO) {
        ServiceOrder serviceOrder = serviceOrderRepositoryPort.findById(requestDTO.serviceOrderId())
                .orElseThrow(() -> new BusinessException("Ordem de serviço não encontrada."));

        serviceOrder.approveOrder(requestDTO.teacherId());

        return serviceOrderMapper.toResponseDTO(serviceOrderRepositoryPort.save(serviceOrder));
    }
}