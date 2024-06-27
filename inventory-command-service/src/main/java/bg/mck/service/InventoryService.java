package bg.mck.service;

import bg.mck.dto.CreateMaterialDTO;
import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.EventCreationHelper;
import bg.mck.events.MaterialEvent;
import bg.mck.events.RegisterMaterialEvent;
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
            FastenerEntity createdFastener = this.fastenerRepository
                    .findByName(createMaterialDTO.getName());
            String materialType = String.valueOf(this.categoryRepository.findByMaterialType(MaterialType.FASTENERS));

            RegisterMaterialEvent registerMaterialEvent = new RegisterMaterialEvent(
                    createdFastener.getId(),
                    EventType.MaterialRegister,
                    materialType,
                    createdFastener.getName(),
                    createdFastener.getDescription(),
                    createdFastener.getDiameter(),
                    createdFastener.getLength(),
                    createdFastener.getModel(),
                    createdFastener.getClazz(),
                    createdFastener.getQuantity(),
                    createdFastener.getNote(),
                    createdFastener.getSpecificationFileUrl()
            );
            MaterialEvent<RegisterMaterialEvent> materialEvent =
                    EventCreationHelper.toMaterialEvent(registerMaterialEvent);

        }

    }

    private FastenerEntity mapFastenerEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new FastenerEntity())
                .map(fastenerEntity -> {
                    fastenerEntity.setName(createMaterialDTO.getName());
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
