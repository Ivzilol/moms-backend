package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.UnspecifiedUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.MetalEntity;
import bg.mck.entity.materialEntity.UnspecifiedEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.material.UnspecifiedUpdateEvent;
import bg.mck.exceptions.DuplicatedInventoryItemException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.UnspecifiedRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;

@Service
public class UnspecifiedUpdateService {

    private final UnspecifiedRepository unspecifiedRepository;
    private final MaterialMapper materialMapper;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;

    public UnspecifiedUpdateService(UnspecifiedRepository unspecifiedRepository, MaterialMapper materialMapper, InventoryQueryServiceClient inventoryQueryServiceClient) {
        this.unspecifiedRepository = unspecifiedRepository;
        this.materialMapper = materialMapper;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
    }


    public void updateUnspecified(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        UnspecifiedEntity unspecifiedEntity = unspecifiedRepository.findById(id)
                .orElseThrow(() ->
                        new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE, UNSPECIFIED, id)));

        UnspecifiedUpdateDTO unspecifiedUpdateDTO = materialMapper.mapUnspecifiedDtoFromUpdateMaterialDto(updateMaterialDTO);

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
        materialMapper.mapUnspecifiedEntityToEvent(unspecifiedEntity,unspecifiedUpdateEvent);
        unspecifiedUpdateEvent.setMaterialType(unspecifiedEntity.getCategory().getMaterialType().name());
        unspecifiedUpdateEvent.setCategory(MaterialType.UNSPECIFIED.name());
        return  unspecifiedUpdateEvent;
    }

    private void updateUnspecifiedEntity(UnspecifiedUpdateDTO unspecifiedUpdateDTO, UnspecifiedEntity unspecifiedEntity) {
        UnspecifiedEntity currentState = unspecifiedRepository.findById(unspecifiedEntity.getId()).get();
        materialMapper.updateUnspecifiedEntityFromDto(unspecifiedUpdateDTO,unspecifiedEntity);
        if (!currentState.getDescription().equals(unspecifiedEntity.getDescription())) {
            if (doesAlreadyExists(unspecifiedEntity)) {
                throw new DuplicatedInventoryItemException(UPDATE_FAILED_MATERIAL_ALREADY_EXIST);
            } else {
                unspecifiedEntity.setName(unspecifiedEntity.getDescription());
            }
        }
        unspecifiedRepository.save(unspecifiedEntity);
    }

    private boolean doesAlreadyExists(UnspecifiedEntity unspecifiedEntity) {
        UnspecifiedEntity byName = unspecifiedRepository.findByName(unspecifiedEntity.getDescription());
        return byName != null;
    }
}
