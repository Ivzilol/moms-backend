package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.RebarUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.MetalEntity;
import bg.mck.entity.materialEntity.RebarEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.material.RebarUpdateEvent;
import bg.mck.exceptions.DuplicatedInventoryItemException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.RebarRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;

@Service
public class RebarUpdateService {

    private final RebarRepository rebarRepository;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;
    private final MaterialMapper materialMapper;
    public RebarUpdateService(RebarRepository rebarRepository, InventoryQueryServiceClient inventoryQueryServiceClient, MaterialMapper materialMapper, ValidationUtil validationUtil) {
        this.rebarRepository = rebarRepository;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
        this.materialMapper = materialMapper;
    }

    public void updateRebar(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        RebarEntity rebarEntity = rebarRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE,REBAR,id)));

        RebarUpdateDTO rebarUpdateDTO = materialMapper.mapRebarDtoFromUpdateMaterialDto(updateMaterialDTO);

        if (ValidationUtil.isValid(rebarUpdateDTO,REBAR_UPDATE_DTO_NAME)) {
            updateRebarEntity(rebarUpdateDTO, rebarEntity);

            RebarUpdateEvent rebarUpdateEvent = createRebarUpdateEvent(rebarEntity);

            MaterialEvent<RebarUpdateEvent> rebarUpdateEventMaterialEvent = EventCreationHelper.toMaterialEvent(rebarUpdateEvent);

            rebarUpdateEventMaterialEvent.setMaterialType(MaterialType.REBAR);
            inventoryQueryServiceClient.sendMaterialEvent
                    (rebarUpdateEventMaterialEvent,
                            rebarUpdateEventMaterialEvent.getEventType().name(),
                            rebarUpdateEventMaterialEvent.getMaterialType().name());
        }
    }

    private RebarUpdateEvent createRebarUpdateEvent(RebarEntity rebarEntity) {
        RebarUpdateEvent rebarUpdateEvent = new RebarUpdateEvent(rebarEntity.getId(), EventType.ItemUpdated);
        materialMapper.mapRebarEntityToEvent(rebarEntity,rebarUpdateEvent);
        rebarUpdateEvent.setMaterialType(rebarEntity.getCategory().getMaterialType().name());
        rebarUpdateEvent.setCategory(MaterialType.REBAR.name());
        return rebarUpdateEvent;
    }

    private void updateRebarEntity(RebarUpdateDTO rebarUpdateDTO, RebarEntity rebarEntity) {
        RebarEntity currentState = rebarRepository.findById(rebarEntity.getId()).get();
        materialMapper.updateRebarEntityFromDto(rebarUpdateDTO,rebarEntity);
        if (!currentState.getDescription().equals(rebarEntity.getDescription())) {
            if (doesAlreadyExists(rebarEntity)) {
                throw new DuplicatedInventoryItemException(UPDATE_FAILED_MATERIAL_ALREADY_EXIST);
            } else {
                rebarEntity.setName(rebarEntity.getDescription());
            }
        }
        rebarRepository.save(rebarEntity);
    }

    private boolean doesAlreadyExists(RebarEntity rebarEntity) {
        RebarEntity byName = rebarRepository.findByName(rebarEntity.getDescription());
        return byName != null;
    }
}
