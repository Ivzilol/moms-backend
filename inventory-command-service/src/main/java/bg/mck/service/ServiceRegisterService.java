package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.CreateServiceDTO;
import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.enums.EventType;
import bg.mck.events.service.ServiceRegisteredEvent;
import bg.mck.exceptions.DuplicatedInventoryItemException;
import bg.mck.mapper.ServiceMapper;
import bg.mck.repository.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceRegisterService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public ServiceRegisterService(ServiceRepository serviceRepository, ServiceMapper serviceMapper, InventoryQueryServiceClient inventoryQueryClient) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
        this.inventoryQueryClient = inventoryQueryClient;
    }

    public void registerService(CreateServiceDTO createServiceDTO) {
        checkIfDuplicated(createServiceDTO.getName());

        ServiceEntity entity = serviceMapper.mapCreateServiceDtoToServiceEntity(createServiceDTO);
        ServiceEntity savedEntity = serviceRepository.save(entity);

        ServiceRegisteredEvent event = new ServiceRegisteredEvent(
                savedEntity.getId(),
                EventType.ItemRegistered,
                savedEntity.getName(),
                savedEntity.getQuantity(),
                savedEntity.getDescription(),
                savedEntity.getSpecificationFileUrl()
        );

      inventoryQueryClient.sendServiceEvent(event, EventType.ItemRegistered.name());

    }

    private void checkIfDuplicated(String name) {
        if (serviceRepository.findByName(name) != null) {
            throw new DuplicatedInventoryItemException("Duplicated service with name: " + name);
        }
    }
}
