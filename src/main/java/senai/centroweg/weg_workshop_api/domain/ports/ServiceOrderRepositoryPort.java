package senai.centroweg.weg_workshop_api.domain.ports;

import senai.centroweg.weg_workshop_api.domain.enums.StatusSO;
import senai.centroweg.weg_workshop_api.domain.model.ServiceOrder;

import java.util.List;
import java.util.Optional;

public interface ServiceOrderRepositoryPort {

    ServiceOrder save(ServiceOrder serviceOrder);

    Optional<ServiceOrder> findById(Integer id);

    List<ServiceOrder> findByStatus(StatusSO status);

    List<ServiceOrder> findByResponsibleTeacherId(Integer teacherId);
}
