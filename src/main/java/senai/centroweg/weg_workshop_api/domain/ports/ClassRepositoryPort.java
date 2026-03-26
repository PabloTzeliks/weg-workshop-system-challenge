package senai.centroweg.weg_workshop_api.domain.ports;

import senai.centroweg.weg_workshop_api.domain.model.Class;

import java.util.List;

public interface ClassRepositoryPort {

    Class save(Class entity);

    List<Class> listAll();
}
