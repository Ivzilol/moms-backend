package bg.mck.service;

import bg.mck.dto.CreateMaterialDTO;
import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.enums.MaterialType;
import bg.mck.repository.CategoryRepository;
import bg.mck.repository.FastenerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final CategoryRepository categoryRepository;

    private final FastenerRepository fastenerRepository;

    public InventoryService(CategoryRepository categoryRepository, FastenerRepository fastenerRepository) {
        this.categoryRepository = categoryRepository;
        this.fastenerRepository = fastenerRepository;
    }

    public void initCategory() {
        if (categoryRepository.count() == 0) {
            CategoryEntity categoryFasteners = new CategoryEntity();
            categoryFasteners.setMaterialType(MaterialType.FASTENERS);
            categoryRepository.save(categoryFasteners);
            CategoryEntity categoryGalvanised = new CategoryEntity();
            categoryGalvanised.setMaterialType(MaterialType.GALVANIZED_SHEET);
            categoryRepository.save(categoryGalvanised);
            CategoryEntity categoryInsulation = new CategoryEntity();
            categoryInsulation.setMaterialType(MaterialType.INSULATION);
            categoryRepository.save(categoryInsulation);
            CategoryEntity categoryPanel = new CategoryEntity();
            categoryPanel.setMaterialType(MaterialType.PANELS);
            categoryRepository.save(categoryPanel);
            CategoryEntity categoryRebar = new CategoryEntity();
            categoryRebar.setMaterialType(MaterialType.REBAR);
            categoryRepository.save(categoryRebar);
            CategoryEntity categorySet = new CategoryEntity();
            categorySet.setMaterialType(MaterialType.SET);
            categoryRepository.save(categorySet);
            CategoryEntity categoryUnspecified = new CategoryEntity();
            categoryUnspecified.setMaterialType(MaterialType.UNSPECIFIED);
            categoryRepository.save(categoryUnspecified);
        }
    }

    public void createMaterial(CreateMaterialDTO createMaterialDTO) {
        if (createMaterialDTO.getMaterialType().equals(MaterialType.FASTENERS)) {
            FastenerEntity fastenerEntity = mapFastenerEntity(createMaterialDTO);
            this.fastenerRepository.save(fastenerEntity);

        }
    }

    private FastenerEntity mapFastenerEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new FastenerEntity())
                .map(fastenerEntity -> {
                    fastenerEntity.setDescription(createMaterialDTO.getDescription());
                    fastenerEntity.setDiameter(createMaterialDTO.getDiameter());
                    fastenerEntity.setLength(createMaterialDTO.getLength());
                    fastenerEntity.setModel(createMaterialDTO.getModel());
                    fastenerEntity.setClazz(createMaterialDTO.getClazz());
                    fastenerEntity.setQuantity(createMaterialDTO.getQuantity());
                    fastenerEntity.setNote(createMaterialDTO.getNote());
                    fastenerEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    fastenerEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.FASTENERS).orElse(null));
                    return fastenerEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map FastenerEntity"));
    }
}
