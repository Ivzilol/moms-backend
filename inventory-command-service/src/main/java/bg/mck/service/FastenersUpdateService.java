package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.FastenerUpdateDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.FastenerUpdateEvent;
import bg.mck.events.material.MaterialEvent;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.FastenerRepository;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static bg.mck.enums.ConstantMessages.*;

@Service
public class FastenersUpdateService {

    private final FastenerRepository fastenerRepository;
    private final InventoryQueryServiceClient inventoryQueryServiceClient;
    private final MaterialMapper materialMapper;


    public FastenersUpdateService(FastenerRepository fastenerRepository, InventoryQueryServiceClient inventoryQueryServiceClient, MaterialMapper materialMapper) {
        this.fastenerRepository = fastenerRepository;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
        this.materialMapper = materialMapper;
    }

    public void updateFastener(Long id, UpdateMaterialDTO updateMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {


                FastenerEntity fastenerEntity = fastenerRepository.findById(id)
                        .orElseThrow(() -> new InventoryItemNotFoundException(String.format(MATERIAL_NOT_FOUND_MESSAGE,FASTENER,id)));

                FastenerUpdateDTO fastenerUpdateDTO = materialMapper.mapFastenerDtoFromUpdateMaterialDTO(updateMaterialDTO);

                if (ValidationUtil.isValid(fastenerUpdateDTO,FASTENER_UPDATE_DTO_NAME)) {
                    updateFastenerEntity(fastenerUpdateDTO, fastenerEntity);

                    FastenerUpdateEvent fastenerUpdateEvent = createFastenerUpdateEvent(fastenerEntity);

                    MaterialEvent<FastenerUpdateEvent> fastenerUpdateEventMaterialEvent = EventCreationHelper
                            .toMaterialEvent(fastenerUpdateEvent);

                    fastenerUpdateEventMaterialEvent.setMaterialType(MaterialType.FASTENERS);
                    inventoryQueryServiceClient.sendMaterialEvent
                        (fastenerUpdateEventMaterialEvent,
                                fastenerUpdateEventMaterialEvent.getEventType().name(),
                                fastenerUpdateEventMaterialEvent.getMaterialType().name());

            }

    }


    private FastenerUpdateEvent createFastenerUpdateEvent(FastenerEntity fastenerEntity) {
        FastenerUpdateEvent fastenerUpdateEvent = new FastenerUpdateEvent(fastenerEntity.getId(), EventType.ItemUpdated);
        materialMapper.mapFastenerEntityToEvent(fastenerEntity, fastenerUpdateEvent);
        fastenerUpdateEvent.setMaterialType(fastenerEntity.getCategory().getMaterialType().name());
        fastenerUpdateEvent.setCategory(MaterialType.FASTENERS.name());
        return fastenerUpdateEvent;
    }


    private void updateFastenerEntity(FastenerUpdateDTO fastenerUpdateDTO, FastenerEntity fastenerEntity) {
        materialMapper.updateFastenerEntityFromDto(fastenerUpdateDTO,fastenerEntity);
        fastenerRepository.save(fastenerEntity);
    }

}
