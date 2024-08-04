package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.enums.EventType;
import bg.mck.events.service.ServiceDeletedEvent;
import bg.mck.events.service.ServiceEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.ServiceRepository;
import bg.mck.utils.EventCreationHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceDeleteService {

    private final ServiceRepository serviceRepository;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public ServiceDeleteService(ServiceRepository serviceRepository, InventoryQueryServiceClient queryClient) {
        this.serviceRepository = serviceRepository;
        this.inventoryQueryClient = queryClient;
    }


    public void deleteServiceById(Long id) {
        ServiceEntity service = findById(id);
        String name = service.getName();
        serviceRepository.deleteById(id);

        ServiceDeletedEvent event = new ServiceDeletedEvent(id, EventType.ItemDeleted, name);
//        ServiceEvent<ServiceDeletedEvent> serviceEvent = EventCreationHelper.toServiceEvent(event);

        inventoryQueryClient.sendServiceEvent(event, EventType.ItemDeleted.name());
    }

    private ServiceEntity findById(Long id) {
        Optional<ServiceEntity> entity = serviceRepository.findById(id);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new InventoryItemNotFoundException("Service with id " + id + " not found");
        }
    }
}
