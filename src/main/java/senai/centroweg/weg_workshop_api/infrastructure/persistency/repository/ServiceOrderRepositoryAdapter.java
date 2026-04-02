package senai.centroweg.weg_workshop_api.infrastructure.persistency.repository;

import org.springframework.stereotype.Repository;
import senai.centroweg.weg_workshop_api.domain.enums.StatusSO;
import senai.centroweg.weg_workshop_api.domain.model.ServiceOrder;
import senai.centroweg.weg_workshop_api.domain.ports.ServiceOrderRepositoryPort;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceOrderRepositoryAdapter implements ServiceOrderRepositoryPort {

    private final ServiceOrderRepository serviceOrderRepository;

    public ServiceOrderRepositoryAdapter(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    @Override
    public ServiceOrder save(ServiceOrder serviceOrder) {
        return serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public Optional<ServiceOrder> findById(Integer id) {
        return serviceOrderRepository.findById(id);
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
