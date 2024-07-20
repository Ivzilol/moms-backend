package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.SetUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.SetEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.material.SetUpdateEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.SetRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;

@Service
public class SetUpdateService {

    private final SetRepository setRepository;
    private final MaterialMapper materialMapper;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;
    public SetUpdateService(SetRepository setRepository, MaterialMapper materialMapper, InventoryQueryServiceClient inventoryQueryServiceClient) {
        this.setRepository = setRepository;
        this.materialMapper = materialMapper;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
    }

    public void updateSet(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        SetEntity setEntity = setRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE, SET, id)));

        SetUpdateDTO setUpdateDTO = materialMapper.mapSetDtoFromUpdateMaterialDto(updateMaterialDTO);

        if (ValidationUtil.isValid(setUpdateDTO,SET_UPDATE_DTO_NAME)) {
            updateSetEntity(setUpdateDTO, setEntity);

            SetUpdateEvent setUpdateEvent = createSetUpdateEvent(setEntity);
            MaterialEvent<SetUpdateEvent> setUpdateEventMaterialEvent = EventCreationHelper.toMaterialEvent(setUpdateEvent);

            setUpdateEventMaterialEvent.setMaterialType(MaterialType.SET);
            inventoryQueryServiceClient
                    .sendMaterialEvent(setUpdateEventMaterialEvent,
                            setUpdateEventMaterialEvent.getEventType().name(),
                            setUpdateEventMaterialEvent.getMaterialType().name());
        }
    }

    private SetUpdateEvent createSetUpdateEvent(SetEntity setEntity) {
        SetUpdateEvent setUpdateEvent = new SetUpdateEvent(setEntity.getId(), EventType.ItemUpdated);
        materialMapper.mapSetEntityToEvent(setEntity,setUpdateEvent);
        setUpdateEvent.setMaterialType(setEntity.getCategory().getMaterialType().name());
        setUpdateEvent.setCategory(MaterialType.SET.name());
        return setUpdateEvent;
    }

    private void updateSetEntity(SetUpdateDTO setUpdateDTO, SetEntity setEntity) {
        materialMapper.updateSetEntityFromDto(setUpdateDTO,setEntity);
        setRepository.save(setEntity);

    }
}
