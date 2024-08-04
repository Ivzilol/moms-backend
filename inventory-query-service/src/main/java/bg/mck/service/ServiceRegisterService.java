package bg.mck.service;

import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.events.service.ServiceRegisteredEvent;
import bg.mck.repository.service.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceRegisterService {

    private final ServiceRepository serviceRepository;

    public ServiceRegisterService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void processingRegisterService(ServiceRegisteredEvent savedEvent) {
        ServiceEntity entity = new ServiceEntity();
        entity.setId(savedEvent.getServiceId());
        entity.setName(savedEvent.getName());
        entity.setDescription(savedEvent.getDescription());
        entity.setQuantity(savedEvent.getQuantity());
        entity.setSpecificationFileUrl(savedEvent.getSpecificationFileUrl());

        serviceRepository.save(entity);
    }
}
