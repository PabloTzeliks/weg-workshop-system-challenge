import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import senai.centroweg.weg_workshop_api.domain.model.ServiceOrder;
import senai.centroweg.weg_workshop_api.domain.ports.ServiceOrderRepositoryPort;
import senai.centroweg.weg_workshop_api.infrastructure.persistency.repository.ServiceOrderRepository;

import java.util.List;

@Repository
public class ServiceOrderRepositoryAdapter implements ServiceOrderRepositoryPort {

    private final ServiceOrderRepository serviceOrderRepository;

    @Autowired
    public ServiceOrderRepositoryAdapter(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    @Override
    public ServiceOrder save(ServiceOrder serviceOrder) {
        return serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public ServiceOrder findById(Long id) {
        return serviceOrderRepository.findById(id).orElse(null);
    }

    @Override
    public List<ServiceOrder> findByStatus(StatusSO status) {
        return serviceOrderRepository.findByStatus(status);
    }

    @Override
    public List<ServiceOrder> findByResponsibleTeacherId(Integer teacherId) {
        return serviceOrderRepository.findByResponsibleTeacherId(teacherId);
    }
}
