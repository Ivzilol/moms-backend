package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.InsulationUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.InsulationEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.InsulationUpdateEvent;
import bg.mck.events.MaterialEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.InventoryMapper;
import bg.mck.repository.InsulationRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;
@Service
public class InsulationUpdateService {

    private final InsulationRepository insulationRepository;
    private final InventoryMapper inventoryMapper;

    private final InventoryQueryServiceClient inventoryQueryServiceClient;

    public InsulationUpdateService(InsulationRepository insulationRepository, InventoryMapper inventoryMapper, InventoryQueryServiceClient inventoryQueryServiceClient) {
        this.insulationRepository = insulationRepository;
        this.inventoryMapper = inventoryMapper;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
    }

    public void updateInsulation(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        InsulationEntity insulationEntity = insulationRepository.findById(id)
                .orElseThrow(() ->
                        new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE, INSULATION, id)));

        InsulationUpdateDTO insulationUpdateDTO = inventoryMapper.mapInsulationDtoFromUpdateMaterialDTO(updateMaterialDTO);

        if (ValidationUtil.isValid(insulationUpdateDTO,INSULATION_UPDATE_DTO_NAME)) {
            updateInsulationEntity(insulationUpdateDTO,insulationEntity);
            InsulationUpdateEvent insulationUpdateEvent = createInsulationUpdateEvent(insulationEntity);
            MaterialEvent<InsulationUpdateEvent> insulationUpdateEventMaterialEvent = EventCreationHelper
                    .toMaterialEvent(insulationUpdateEvent);
            insulationUpdateEventMaterialEvent.setMaterialType(MaterialType.INSULATION);
            inventoryQueryServiceClient
                    .sendMaterialEvent
                            (insulationUpdateEventMaterialEvent,
                                    insulationUpdateEventMaterialEvent.getEventType().name(),
                                    insulationUpdateEventMaterialEvent.getMaterialType().name());
        }
    }

    private InsulationUpdateEvent createInsulationUpdateEvent(InsulationEntity insulationEntity) {
        InsulationUpdateEvent insulationUpdateEvent = new InsulationUpdateEvent(insulationEntity.getId(), EventType.ItemUpdated);
        inventoryMapper.mapInsulationEntityToEvent(insulationEntity,insulationUpdateEvent);
        insulationUpdateEvent.setMaterialType(insulationEntity.getCategory().getMaterialType().name());
        insulationUpdateEvent.setCategory(MaterialType.INSULATION.name());
        return insulationUpdateEvent;
    }

    private void updateInsulationEntity(InsulationUpdateDTO insulationUpdateDTO, InsulationEntity insulationEntity) {
        inventoryMapper.updateInsulationEntityFromDto(insulationUpdateDTO,insulationEntity);
        insulationRepository.save(insulationEntity);
    }
}
