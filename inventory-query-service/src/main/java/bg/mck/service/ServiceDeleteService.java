package bg.mck.service;

import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.service.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeleteService {

    private final ServiceRepository serviceRepository;

    public ServiceDeleteService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void deleteServiceById(String id) {
        boolean exists = serviceRepository.existsById(id);
        if (exists) {
            serviceRepository.deleteById(id);
        } else {
            throw new InventoryItemNotFoundException("Service with id " + id + " not found");
        }
    }
}
