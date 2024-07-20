package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.enums.EventType;
import bg.mck.events.construction.ConstructionDeletedEvent;
import bg.mck.events.construction.ConstructionEvent;
import bg.mck.events.service.ServiceDeletedEvent;
import bg.mck.events.service.ServiceEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.ConstructionRepository;
import bg.mck.utils.EventCreationHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConstructionDeleteService {

    private final ConstructionRepository constructionRepository;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public ConstructionDeleteService(ConstructionRepository constructionRepository, InventoryQueryServiceClient inventoryQueryClient) {
        this.constructionRepository = constructionRepository;
        this.inventoryQueryClient = inventoryQueryClient;
    }

    public void deleteConstructionSiteById(Long id) {
        ConstructionSiteEntity constructionSite = findById(id);
        String name = constructionSite.getName();
        constructionRepository.deleteById(id);

        ConstructionDeletedEvent event = new ConstructionDeletedEvent(id, EventType.ItemDeleted, name);
        ConstructionEvent<ConstructionDeletedEvent> constructionEvent = EventCreationHelper.toConstructionEvent(event);

        inventoryQueryClient.sendConstructionEvent(constructionEvent, EventType.ItemDeleted.name());
    }

    private ConstructionSiteEntity findById(Long id) {
        Optional<ConstructionSiteEntity> entity = constructionRepository.findById(id);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new InventoryItemNotFoundException("Construction site with id " + id + " not found");
        }
    }
}
