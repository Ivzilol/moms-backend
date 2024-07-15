package bg.mck.service;


import bg.mck.enums.MaterialType;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.material.*;
import org.springframework.data.mongodb.repository.MongoRepository;
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


    public MaterialDeleteService(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, MetalRepository metalRepository) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
        this.metalRepository = metalRepository;
    }

    public void deleteMaterialByIdAndCategory(String id, String categoryName) {
       MaterialType type = MaterialType.valueOf(categoryName.toUpperCase());

        switch (type) {
            case FASTENERS -> deleteMaterialById(fastenerRepository, id);
            case GALVANIZED_SHEET -> deleteMaterialById(galvanisedSheetRepository, id);
            case INSULATION -> deleteMaterialById(insulationRepository, id);
            case PANELS -> deleteMaterialById(panelRepository, id);
            case REBAR -> deleteMaterialById(rebarRepository, id);
            case SET -> deleteMaterialById(setRepository, id);
            case UNSPECIFIED -> deleteMaterialById(unspecifiedRepository, id);
            case METAL -> deleteMaterialById(metalRepository, id);
            default -> throw new InvalidCategoryException("Unhandled category type: " + type);
        }

    }

    private <T> void deleteMaterialById(MongoRepository<T, String> repository, String id) {
        Optional<T> material = repository.findById(id);

        if (material.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new InventoryItemNotFoundException("Material with id " + id + " not found");
        }
    }

}
