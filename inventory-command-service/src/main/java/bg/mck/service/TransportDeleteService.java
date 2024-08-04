package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.entity.transportEntity.TransportEntity;
import bg.mck.enums.EventType;
import bg.mck.events.service.ServiceDeletedEvent;
import bg.mck.events.service.ServiceEvent;
import bg.mck.events.transport.TransportDeletedEvent;
import bg.mck.events.transport.TransportEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.TransportRepository;
import bg.mck.utils.EventCreationHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransportDeleteService {

    private final TransportRepository transportRepository;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public TransportDeleteService(TransportRepository transportRepository, InventoryQueryServiceClient inventoryQueryClient) {
        this.transportRepository = transportRepository;
        this.inventoryQueryClient = inventoryQueryClient;
    }

    public void deleteTransportById(Long id) {
        TransportEntity transport = findById(id);
        String name = transport.getName();
        transportRepository.deleteById(id);

        TransportDeletedEvent event = new TransportDeletedEvent(id, EventType.ItemDeleted, name);

        inventoryQueryClient.sendTransportEvent(event, EventType.ItemDeleted.name());
    }

    private TransportEntity findById(Long id) {
        Optional<TransportEntity> entity = transportRepository.findById(id);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new InventoryItemNotFoundException("Transport with id " + id + " not found");
        }
    }
}
