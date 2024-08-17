package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.PanelUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.PanelEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.MaterialEvent;
import bg.mck.events.material.PanelUpdateEvent;
import bg.mck.exceptions.DuplicatedInventoryItemException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.PanelRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;

@Service
public class PanelUpdateService {


    private final PanelRepository panelRepository;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;
    private final MaterialMapper materialMapper;
    public PanelUpdateService(PanelRepository panelRepository, InventoryQueryServiceClient inventoryQueryServiceClient, MaterialMapper materialMapper) {
        this.panelRepository = panelRepository;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
        this.materialMapper = materialMapper;
    }


    public void updatePanel(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        PanelEntity panelEntity = panelRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE, PANEL, id)));

        PanelUpdateDTO panelUpdateDTO = materialMapper.mapPanelDtoFromUpdateMaterialDto(updateMaterialDTO);

        if (ValidationUtil.isValid(panelUpdateDTO,PANEL_UPDATE_DTO_NAME)) {
            updatePanelEntity(panelUpdateDTO,panelEntity);
            PanelUpdateEvent panelUpdateEvent = createPanelUpdateEvent(panelEntity);
            MaterialEvent<PanelUpdateEvent> panelUpdateEventMaterialEvent = EventCreationHelper.toMaterialEvent(panelUpdateEvent);
            panelUpdateEventMaterialEvent.setMaterialType(MaterialType.PANELS);
            inventoryQueryServiceClient
                    .sendMaterialEvent(panelUpdateEventMaterialEvent,
                            panelUpdateEventMaterialEvent.getEventType().name(),
                            panelUpdateEventMaterialEvent.getMaterialType().name());
        }
    }

    private PanelUpdateEvent createPanelUpdateEvent(PanelEntity panelEntity) {
        PanelUpdateEvent panelUpdateEvent = new PanelUpdateEvent(panelEntity.getId(), EventType.ItemUpdated);
        materialMapper.mapPanelEntityToEvent(panelEntity, panelUpdateEvent);
        panelUpdateEvent.setMaterialType(panelEntity.getCategory().getMaterialType().name());
        panelUpdateEvent.setCategory(MaterialType.PANELS.name());
        return panelUpdateEvent;
    }
    private void updatePanelEntity(PanelUpdateDTO panelUpdateDTO, PanelEntity panelEntity) {
        PanelEntity currentState = panelRepository.findById(panelEntity.getId()).get();
        materialMapper.updatePanelEntityFromDto(panelUpdateDTO,panelEntity);
        if (!currentState.getType().equals(panelEntity.getType()) ||
            !currentState.getLength().equals(panelEntity.getLength()) ||
            !currentState.getLengthUnit().name().equals(panelEntity.getLengthUnit().name()) ||
            !currentState.getTotalThickness().equals(panelEntity.getTotalThickness()) ||
            !currentState.getTotalThicknessUnit().name().equals(panelEntity.getTotalThicknessUnit().name())) {
            if(doesAlreadyExists(panelEntity)) {
                throw new DuplicatedInventoryItemException(UPDATE_FAILED_MATERIAL_ALREADY_EXIST);
            } else {
                panelEntity.setName(panelEntity.getType() + " " + panelEntity.getLength() + " " + panelEntity.getLengthUnit()
                        + " " + panelEntity.getTotalThickness() + " " + panelEntity.getTotalThicknessUnit());
            }
        }

        panelRepository.save(panelEntity);
    }

    private boolean doesAlreadyExists(PanelEntity panelEntity) {
        PanelEntity byName = panelRepository.findByName(panelEntity.getType() + " " + panelEntity.getLength() + " " + panelEntity.getLengthUnit()
                + " " + panelEntity.getTotalThickness() + " " + panelEntity.getTotalThicknessUnit());
        return byName != null;
    }
}
