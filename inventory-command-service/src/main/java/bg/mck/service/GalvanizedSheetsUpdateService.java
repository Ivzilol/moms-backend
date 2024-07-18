package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.GalvanizedSheetUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.GalvanisedSheetEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.GalvanizedSheetUpdateEvent;
import bg.mck.events.MaterialEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.InventoryMapper;
import bg.mck.repository.GalvanisedSheetRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;
@Service
public class GalvanizedSheetsUpdateService {

    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;

    public GalvanizedSheetsUpdateService(GalvanisedSheetRepository galvanisedSheetRepository,
                                         InventoryMapper inventoryMapper,
                                         InventoryQueryServiceClient inventoryQueryServiceClient) {
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.inventoryMapper = inventoryMapper;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
    }


    public void updateGalvanizedSheet(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        GalvanisedSheetEntity galvanisedSheetEntity = galvanisedSheetRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException
                        (String.format(MATERIAL_NOT_FOUND_MESSAGE, GALVANIZED_SHEET, id)));

        GalvanizedSheetUpdateDTO galvanizedSheetUpdateDTO = inventoryMapper
                .mapGalvanizedSheetDtoFromUpdateMaterialDTO(updateMaterialDTO);

        if (ValidationUtil.isValid(galvanizedSheetUpdateDTO,GALVANIZED_SHEET_DTO_NAME)) {
            updateEntity(galvanizedSheetUpdateDTO,galvanisedSheetEntity);
            GalvanizedSheetUpdateEvent galvanizedSheetUpdateEvent = createGalvanizedSheetUpdateEvent(galvanisedSheetEntity);
            MaterialEvent<GalvanizedSheetUpdateEvent> galvanizedSheetUpdateEventMaterialEvent = EventCreationHelper
                    .toMaterialEvent(galvanizedSheetUpdateEvent);

            galvanizedSheetUpdateEventMaterialEvent.setMaterialType(MaterialType.GALVANIZED_SHEET);

            inventoryQueryServiceClient
                    .sendMaterialEvent
                            (galvanizedSheetUpdateEventMaterialEvent,
                                    galvanizedSheetUpdateEventMaterialEvent.getEventType().name(),
                                    galvanizedSheetUpdateEventMaterialEvent.getMaterialType().name());
        }

    }

    private void updateEntity(GalvanizedSheetUpdateDTO galvanizedSheetUpdateDTO, GalvanisedSheetEntity galvanisedSheetEntity) {
        inventoryMapper.updateGalvanizedSheetEntityFromDto(galvanizedSheetUpdateDTO,galvanisedSheetEntity);
        galvanisedSheetRepository.save(galvanisedSheetEntity);
    }

    private GalvanizedSheetUpdateEvent createGalvanizedSheetUpdateEvent(GalvanisedSheetEntity galvanisedSheetEntity) {
        GalvanizedSheetUpdateEvent galvanizedSheetUpdateEvent = new GalvanizedSheetUpdateEvent(galvanisedSheetEntity.getId(), EventType.ItemUpdated);
        inventoryMapper.mapGalvanizedSheetToEvent(galvanisedSheetEntity, galvanizedSheetUpdateEvent);
        galvanizedSheetUpdateEvent.setMaterialType(galvanisedSheetEntity.getCategory().getMaterialType().name());
        galvanizedSheetUpdateEvent.setCategory(MaterialType.GALVANIZED_SHEET.name());
        return galvanizedSheetUpdateEvent;
    }
}
