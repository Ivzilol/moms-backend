package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.MaterialDeletedEvent;
import bg.mck.events.material.MaterialEvent;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.*;
import bg.mck.utils.EventCreationHelper;
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
    private final MetalRepository metalRepository;
    private final InventoryQueryServiceClient inventoryQueryClient;

    public MaterialDeleteService(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository,
                                 PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, MetalRepository metalRepository, InventoryQueryServiceClient inventoryQueryClient) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
        this.metalRepository = metalRepository;
        this.inventoryQueryClient = inventoryQueryClient;
    }

    public void deleteMaterialByIdAndCategory(Long materialId, String categoryName) {
        MaterialType materialType;
        try {
            materialType = MaterialType.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("Invalid category name: " + categoryName);
        }

        String name;

        switch (materialType) {
            case FASTENERS -> {
                FastenerEntity entity = getMaterialById(fastenerRepository, materialId);
                name = entity.getName();
                deleteMaterialById(fastenerRepository, materialId);
            }
            case GALVANIZED_SHEET -> {
                GalvanisedSheetEntity entity = getMaterialById(galvanisedSheetRepository, materialId);
                name = entity.getName();
                deleteMaterialById(galvanisedSheetRepository, materialId);
            }
            case INSULATION -> {
                InsulationEntity entity = getMaterialById(insulationRepository, materialId);
                name = entity.getName();
                deleteMaterialById(insulationRepository, materialId);
            }
            case PANELS -> {
                PanelEntity entity = getMaterialById(panelRepository, materialId);
                name = entity.getName();
                deleteMaterialById(panelRepository, materialId);
            }
            case REBAR -> {
                RebarEntity entity = getMaterialById(rebarRepository, materialId);
                name = entity.getName();
                deleteMaterialById(rebarRepository, materialId);
            }
            case SET -> {
                SetEntity entity = getMaterialById(setRepository, materialId);
                name = entity.getName();
                deleteMaterialById(setRepository, materialId);
            }
            case UNSPECIFIED -> {
                UnspecifiedEntity entity = getMaterialById(unspecifiedRepository, materialId);
                name = entity.getName();
                deleteMaterialById(unspecifiedRepository, materialId);
            }
            case METAL -> {
                MetalEntity entity = getMaterialById(metalRepository, materialId);
                name = entity.getName();
                deleteMaterialById(metalRepository, materialId);
            }
            default -> throw new InvalidCategoryException("Unhandled category type: " + materialType);
        }

        MaterialDeletedEvent event = new MaterialDeletedEvent(materialId, EventType.ItemDeleted, name);

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

    private <T> T getMaterialById(JpaRepository<T, Long> repository, Long id) {
        Optional<T> material = repository.findById(id);
        if (material.isPresent()) {
            return material.get();
        } else {
            throw new InventoryItemNotFoundException("Material with id " + id + " not found");
        }
    }

}
