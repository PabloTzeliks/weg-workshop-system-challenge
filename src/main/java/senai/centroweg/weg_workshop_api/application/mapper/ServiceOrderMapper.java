package senai.centroweg.weg_workshop_api.application.mapper;

import org.springframework.stereotype.Component;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.ServiceOrderResponseDTO;
import senai.centroweg.weg_workshop_api.domain.model.ServiceOrder;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceOrderMapper {

    public ServiceOrderResponseDTO toResponseDTO(ServiceOrder serviceOrder) {
        return new ServiceOrderResponseDTO(serviceOrder.getId(), serviceOrder.getEquipment(), serviceOrder.getReportedDefect(), serviceOrder.getResponsibleTeacher(), serviceOrder.getStudents());
    }

    public ServiceOrder toEntity(ServiceOrderRequestDTO serviceOrderRequestDTO) {
        return new ServiceOrder(serviceOrderRequestDTO.equipment(), serviceOrderRequestDTO.reportedDefect(), null, null);
    }

    public List<ServiceOrderResponseDTO> toResponseDTOList(List<ServiceOrder> serviceOrders) {
        return serviceOrders.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
