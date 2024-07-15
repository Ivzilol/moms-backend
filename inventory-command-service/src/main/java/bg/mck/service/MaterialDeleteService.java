package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.MaterialDeletedEvent;
import bg.mck.events.MaterialEvent;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.*;
import bg.mck.utils.EventCreationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialDeleteService {


    private final FastenerRepository fastenerRepository;
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final InsulationRepository insulationRepository;
    private final PanelRepository panelRepository;
    private final RebarRepository rebarRepository;
    private final SetRepository setRepository;
    private final UnspecifiedRepository unspecifiedRepository;
    private final ObjectMapper objectMapper;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public MaterialDeleteService(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, ObjectMapper objectMapper, InventoryQueryServiceClient inventoryQueryClient) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
        this.objectMapper = objectMapper;
        this.inventoryQueryClient = inventoryQueryClient;
    }

    public void deleteMaterialByIdAndCategory(Long materialId, String categoryName) throws JsonProcessingException {
        MaterialType materialType;
        try {
            materialType = MaterialType.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("Invalid category name: " + categoryName);
        }

        switch (materialType) {
            case FASTENERS -> deleteMaterialById(fastenerRepository, materialId);
            case GALVANIZED_SHEET -> deleteMaterialById(galvanisedSheetRepository, materialId);
            case INSULATION -> deleteMaterialById(insulationRepository, materialId);
            case PANELS -> deleteMaterialById(panelRepository, materialId);
            case REBAR -> deleteMaterialById(rebarRepository, materialId);
            case SET -> deleteMaterialById(setRepository, materialId);
            case UNSPECIFIED -> deleteMaterialById(unspecifiedRepository, materialId);
            default -> throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }

        MaterialDeletedEvent event = new MaterialDeletedEvent(materialId, EventType.ItemDeleted);

        MaterialEvent<MaterialDeletedEvent> materialEvent = EventCreationHelper.toMaterialEvent(event);


        inventoryQueryClient.sendMaterialEvent(materialEvent, materialEvent.getEventType().name(), categoryName);
    }

    private <T> void deleteMaterialById(JpaRepository<T, Long> repository, Long id) {
        Optional<T> material = repository.findById(id);

        if (material.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new InventoryItemNotFoundException("Material with id " + id + " not found");
        }
    }

}
