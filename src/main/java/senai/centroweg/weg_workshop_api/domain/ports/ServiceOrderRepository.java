package senai.centroweg.weg_workshop_api.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.centroweg.weg_workshop_api.domain.enums.StatusSO;
import senai.centroweg.weg_workshop_api.domain.model.ServiceOrder;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer> {
    List<ServiceOrder> findByStatus(StatusSO status);
    List<ServiceOrder> findByResponsibleTeacherId(Integer teacherId);
}
