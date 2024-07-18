package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.MetalUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.MetalEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.MaterialEvent;
import bg.mck.events.MetalUpdateEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.InventoryMapper;
import bg.mck.repository.MetalRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;

@Service
public class MetalUpdateService {

    private final MetalRepository metalRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;

    public MetalUpdateService(MetalRepository metalRepository, InventoryMapper inventoryMapper, InventoryQueryServiceClient inventoryQueryServiceClient) {
        this.metalRepository = metalRepository;
        this.inventoryMapper = inventoryMapper;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
    }

    public void updateMetal(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        MetalEntity metalEntity = metalRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE, METAL, id)));

        MetalUpdateDTO metalUpdateDTO = inventoryMapper.mapMetalDtoFromUpdateMaterialDto(updateMaterialDTO);

        if (ValidationUtil.isValid(metalUpdateDTO,METAL_DTO_NAME)) {
            updateMetalEntity(metalUpdateDTO,metalEntity);

            MetalUpdateEvent metalUpdateEvent = createMetalUpdateEvent(metalEntity);
            MaterialEvent<MetalUpdateEvent> metalUpdateEventMaterialEvent = EventCreationHelper.toMaterialEvent(metalUpdateEvent);
            metalUpdateEventMaterialEvent.setMaterialType(MaterialType.METAL);
            inventoryQueryServiceClient
                    .sendMaterialEvent(metalUpdateEventMaterialEvent,
                            metalUpdateEventMaterialEvent.getEventType().name(),
                            metalUpdateEventMaterialEvent.getMaterialType().name());
        }
    }

    private MetalUpdateEvent createMetalUpdateEvent(MetalEntity metalEntity) {
        MetalUpdateEvent metalUpdateEvent = new MetalUpdateEvent(metalEntity.getId(), EventType.ItemUpdated);
        inventoryMapper.mapMetalEntityToEvent(metalEntity,metalUpdateEvent);
        metalUpdateEvent.setMaterialType(metalEntity.getCategory().getMaterialType().name());
        metalUpdateEvent.setCategory(MaterialType.METAL.name());
        return metalUpdateEvent;
    }

    private void updateMetalEntity(MetalUpdateDTO metalUpdateDTO, MetalEntity metalEntity) {
        inventoryMapper.updateMetalEntityFromDto(metalUpdateDTO,metalEntity);
        metalRepository.save(metalEntity);
    }
}
