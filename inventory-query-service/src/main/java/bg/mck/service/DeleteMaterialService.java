package bg.mck.service;


import bg.mck.enums.MaterialType;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteMaterialService {


    private final FastenerRepository fastenerRepository;
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final InsulationRepository insulationRepository;
    private final PanelRepository panelRepository;
    private final RebarRepository rebarRepository;
    private final SetRepository setRepository;
    private final UnspecifiedRepository unspecifiedRepository;


    public DeleteMaterialService(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
    }

    public void deleteMaterialByIdAndCategory(Long id, String categoryName) {
       MaterialType type = MaterialType.valueOf(categoryName.toUpperCase());

        switch (type) {
            case MaterialType.FASTENERS -> deleteMaterialById(fastenerRepository, id);
            case MaterialType.GALVANIZED_SHEET -> deleteMaterialById(galvanisedSheetRepository, id);
            case MaterialType.INSULATION -> deleteMaterialById(insulationRepository, id);
            case MaterialType.PANELS -> deleteMaterialById(panelRepository, id);
            case MaterialType.REBAR -> deleteMaterialById(rebarRepository, id);
            case MaterialType.SET -> deleteMaterialById(setRepository, id);
            case MaterialType.UNSPECIFIED -> deleteMaterialById(unspecifiedRepository, id);
            default -> throw new InvalidCategoryException("Unhandled category type: " + type);
        }

    }

    private <T> void deleteMaterialById(MongoRepository<T, Long> repository, Long id) {
        Optional<T> material = repository.findById(id);

        if (material.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new InventoryItemNotFoundException("Material with id " + id + " not found");
        }
    }

}
