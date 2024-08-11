package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.CreateTransportDTO;
import bg.mck.entity.transportEntity.TransportEntity;
import bg.mck.enums.EventType;
import bg.mck.events.transport.TransportRegisteredEvent;
import bg.mck.exceptions.DuplicatedInventoryItemException;
import bg.mck.mapper.TransportMapper;
import bg.mck.repository.TransportRepository;
import org.springframework.stereotype.Service;

@Service
public class TransportRegisterService {

    private final TransportRepository transportRepository;
    private final TransportMapper transportMapper;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public TransportRegisterService(TransportRepository transportRepository, TransportMapper transportMapper, InventoryQueryServiceClient inventoryQueryClient) {
        this.transportRepository = transportRepository;
        this.transportMapper = transportMapper;
        this.inventoryQueryClient = inventoryQueryClient;
    }


    public void registerTransport(CreateTransportDTO createTransportDTO) {
        checkIfDuplicated(createTransportDTO.getName());

        TransportEntity entity = transportMapper.mapCreateTransportDtoToTransportEntity(createTransportDTO);
        TransportEntity savedEntity = transportRepository.save(entity);

        TransportRegisteredEvent event = new TransportRegisteredEvent(
                savedEntity.getId(),
                EventType.ItemRegistered,
                savedEntity.getName(),
                savedEntity.getMaxLength(),
                savedEntity.getWeight(),
                savedEntity.getTruck(),
                savedEntity.getQuantity(),
                savedEntity.getDescription(),
                savedEntity.getSpecificationFileUrl()
        );

      inventoryQueryClient.sendTransportEvent(event, EventType.ItemRegistered.name());

    }

    private void checkIfDuplicated(String name) {
        if (transportRepository.findByName(name) != null) {
            throw new DuplicatedInventoryItemException("Duplicated transport with name: " + name);
        }
    }
}
