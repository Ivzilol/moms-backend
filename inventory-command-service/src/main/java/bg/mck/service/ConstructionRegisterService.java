package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.CreateConstructionDTO;
import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.enums.EventType;
import bg.mck.events.construction.ConstructionRegisteredEvent;
import bg.mck.exceptions.DuplicatedInventoryItemException;
import bg.mck.mapper.ConstructionMapper;
import bg.mck.repository.ConstructionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConstructionRegisterService {

    private final ConstructionRepository constructionRepository;
    private final ConstructionMapper constructionMapper;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public ConstructionRegisterService(ConstructionRepository constructionRepository, ConstructionMapper constructionMapper, InventoryQueryServiceClient inventoryQueryClient) {
        this.constructionRepository = constructionRepository;
        this.constructionMapper = constructionMapper;
        this.inventoryQueryClient = inventoryQueryClient;
    }


    public void registerConstruction(CreateConstructionDTO createConstructionDTO) {
        checkIfDuplicated(createConstructionDTO.getName());

        ConstructionSiteEntity entity = constructionMapper.mapCreateConstructionDtoToConstructionEntity(createConstructionDTO);
        ConstructionSiteEntity savedEntity = constructionRepository.save(entity);

        ConstructionRegisteredEvent event = new ConstructionRegisteredEvent(
                savedEntity.getId(),
                EventType.ItemRegistered,
                savedEntity.getName(),
                savedEntity.getConstructionNumber()
        );

      inventoryQueryClient.sendConstructionEvent(event, EventType.ItemRegistered.name());

    }

    private void checkIfDuplicated(String name) {
        if (constructionRepository.findByName(name) != null) {
            throw new DuplicatedInventoryItemException("Duplicated construction site with name: " + name);
        }
    }
}
