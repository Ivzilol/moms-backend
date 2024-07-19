package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.UnspecifiedUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.UnspecifiedEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.MaterialEvent;
import bg.mck.events.UnspecifiedUpdateEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.InventoryMapper;
import bg.mck.repository.UnspecifiedRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;

@Service
public class UnspecifiedUpdateService {

    private final UnspecifiedRepository unspecifiedRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;

    public UnspecifiedUpdateService(UnspecifiedRepository unspecifiedRepository, InventoryMapper inventoryMapper, InventoryQueryServiceClient inventoryQueryServiceClient) {
        this.unspecifiedRepository = unspecifiedRepository;
        this.inventoryMapper = inventoryMapper;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
    }


    public void updateUnspecified(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        UnspecifiedEntity unspecifiedEntity = unspecifiedRepository.findById(id)
                .orElseThrow(() ->
                        new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE, UNSPECIFIED, id)));

        UnspecifiedUpdateDTO unspecifiedUpdateDTO = inventoryMapper.mapUnspecifiedDtoFromUpdateMaterialDto(updateMaterialDTO);

        if (ValidationUtil.isValid(unspecifiedUpdateDTO,UNSPECIFIED_UPDATE_DTO_NAME)) {
            updateUnspecifiedEntity(unspecifiedUpdateDTO,unspecifiedEntity);

            UnspecifiedUpdateEvent unspecifiedUpdateEvent = createUnspecifiedUpdateEvent(unspecifiedEntity);
            MaterialEvent<UnspecifiedUpdateEvent> unspecifiedUpdateEventMaterialEvent = EventCreationHelper
                    .toMaterialEvent(unspecifiedUpdateEvent);
            unspecifiedUpdateEventMaterialEvent.setMaterialType(MaterialType.UNSPECIFIED);
            inventoryQueryServiceClient
                    .sendMaterialEvent(unspecifiedUpdateEventMaterialEvent,
                            unspecifiedUpdateEventMaterialEvent.getEventType().name(),
                            unspecifiedUpdateEventMaterialEvent.getMaterialType().name());
        }
    }

    private UnspecifiedUpdateEvent createUnspecifiedUpdateEvent(UnspecifiedEntity unspecifiedEntity) {
        UnspecifiedUpdateEvent unspecifiedUpdateEvent = new UnspecifiedUpdateEvent(unspecifiedEntity.getId(),
                EventType.ItemUpdated);
        inventoryMapper.mapUnspecifiedEntityToEvent(unspecifiedEntity,unspecifiedUpdateEvent);
        unspecifiedUpdateEvent.setMaterialType(unspecifiedEntity.getCategory().getMaterialType().name());
        unspecifiedUpdateEvent.setCategory(MaterialType.UNSPECIFIED.name());
        return  unspecifiedUpdateEvent;
    }

    private void updateUnspecifiedEntity(UnspecifiedUpdateDTO unspecifiedUpdateDTO, UnspecifiedEntity unspecifiedEntity) {
        inventoryMapper.updateUnspecifiedEntityFromDto(unspecifiedUpdateDTO,unspecifiedEntity);
        unspecifiedRepository.save(unspecifiedEntity);
    }
}
