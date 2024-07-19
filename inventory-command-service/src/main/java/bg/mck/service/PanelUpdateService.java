package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.PanelUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.PanelEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.MaterialEvent;
import bg.mck.events.PanelUpdateEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.InventoryMapper;
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
    private final InventoryMapper inventoryMapper;
    public PanelUpdateService(PanelRepository panelRepository, InventoryQueryServiceClient inventoryQueryServiceClient, InventoryMapper inventoryMapper) {
        this.panelRepository = panelRepository;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
        this.inventoryMapper = inventoryMapper;
    }


    public void updatePanel(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        PanelEntity panelEntity = panelRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE, PANEL, id)));

        PanelUpdateDTO panelUpdateDTO = inventoryMapper.mapPanelDtoFromUpdateMaterialDto(updateMaterialDTO);

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
        inventoryMapper.mapPanelEntityToEvent(panelEntity, panelUpdateEvent);
        panelUpdateEvent.setMaterialType(panelEntity.getCategory().getMaterialType().name());
        panelUpdateEvent.setCategory(MaterialType.PANELS.name());
        return panelUpdateEvent;
    }
    private void updatePanelEntity(PanelUpdateDTO panelUpdateDTO, PanelEntity panelEntity) {
        inventoryMapper.updatePanelEntityFromDto(panelUpdateDTO,panelEntity);
        panelRepository.save(panelEntity);
    }
}
