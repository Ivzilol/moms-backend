package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.CreateMaterialDTO;
import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.*;
import bg.mck.repository.*;
import bg.mck.utils.EventCreationHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialRegisterService {

    private final CategoryRepository categoryRepository;

    private final FastenerRepository fastenerRepository;

    private final GalvanisedSheetRepository galvanisedSheetRepository;

    private final InventoryQueryServiceClient inventoryQueryServiceClient;

    private final InsulationRepository insulationRepository;

    private final MetalRepository metalRepository;

    private final PanelRepository panelRepository;

    private final RebarRepository rebarRepository;

    private final SetRepository setRepository;

    private final UnspecifiedRepository unspecifiedRepository;

    public MaterialRegisterService(CategoryRepository categoryRepository,
                                   FastenerRepository fastenerRepository,
                                   GalvanisedSheetRepository galvanisedSheetRepository,
                                   InventoryQueryServiceClient inventoryQueryServiceClient,
                                   InsulationRepository insulationRepository,
                                   MetalRepository metalRepository,
                                   PanelRepository panelRepository,
                                   RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository) {
        this.categoryRepository = categoryRepository;
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.inventoryQueryServiceClient = inventoryQueryServiceClient;
        this.insulationRepository = insulationRepository;
        this.metalRepository = metalRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
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
            CategoryEntity categoryMetal = new CategoryEntity();
            categoryMetal.setMaterialType(MaterialType.METAL);
            categoryRepository.save(categoryMetal);
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
                    .findByName(createMaterialDTO.getType() + " " +
                            createMaterialDTO.getDiameter() + " " + createMaterialDTO.getLength());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.FASTENERS);
            String materialType = byMaterialType.get().getMaterialType().name();

            createFastenersEvent(createdFastener, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.GALVANIZED_SHEET)) {
            GalvanisedSheetEntity galvanisedSheetEntity = mapGalvanizedEntity(createMaterialDTO);
            this.galvanisedSheetRepository.save(galvanisedSheetEntity);
            GalvanisedSheetEntity createdGalvanized = this.galvanisedSheetRepository
                    .findByName(createMaterialDTO.getType());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.GALVANIZED_SHEET);
            String materialType = byMaterialType.get().getMaterialType().name();

            createGalvanizedEvent(createdGalvanized, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.INSULATION)) {
            InsulationEntity insulationEntity = mapInsulationEntity(createMaterialDTO);
            this.insulationRepository.save(insulationEntity);
            InsulationEntity createInsulation = this.insulationRepository
                    .findByName(createMaterialDTO.getType() + " " + createMaterialDTO.getThickness());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.INSULATION);
            String materialType = byMaterialType.get().getMaterialType().name();

            createInsulationEvent(createInsulation, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.METAL)) {
            MetalEntity metalEntity = mapMetalEntity(createMaterialDTO);
            this.metalRepository.save(metalEntity);
            MetalEntity createMetal = this.metalRepository
                    .findByName(createMaterialDTO.getDescription());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.METAL);
            String materialType = byMaterialType.get().getMaterialType().name();

            createMetalEvent(createMetal, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.PANELS)) {
            PanelEntity panelEntity = mapPanelEntity(createMaterialDTO);
            this.panelRepository.save(panelEntity);
            PanelEntity createPanel = this.panelRepository
                    .findByName(createMaterialDTO.getType() + " " + createMaterialDTO.getLength()
                            + " " + createMaterialDTO.getTotalThickness());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.PANELS);
            String materialType = byMaterialType.get().getMaterialType().name();

            createPanelEvent(createPanel, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.REBAR)) {
            RebarEntity rebarEntity = mapRebarEntity(createMaterialDTO);
            this.rebarRepository.save(rebarEntity);
            RebarEntity createRebar = this.rebarRepository
                    .findByName(createMaterialDTO.getDescription());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.REBAR);
            String materialType = byMaterialType.get().getMaterialType().name();

            createRebarEvent(createRebar, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.SET)) {
            SetEntity setEntity = mapSetEntity(createMaterialDTO);
            this.setRepository.save(setEntity);
            SetEntity createSet = this.setRepository
                    .findByName(createMaterialDTO.getGalvanisedSheetThickness() + " " +
                            createMaterialDTO.getColor());

            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.SET);
            String materialType = byMaterialType.get().getMaterialType().name();

            createSetEvent(createSet, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.UNSPECIFIED)) {
            UnspecifiedEntity unspecifiedEntity = mapUnspecifiedEntity(createMaterialDTO);
            this.unspecifiedRepository.save(unspecifiedEntity);
            UnspecifiedEntity createUnspecified =
                    this.unspecifiedRepository.findByName(createMaterialDTO.getDescription());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.UNSPECIFIED);
            String materialType = byMaterialType.get().getMaterialType().name();

            createUnspecifiedEvent(createUnspecified, materialType);
        }
    }

    private void createUnspecifiedEvent(UnspecifiedEntity createUnspecified, String materialType) {
        RegisterUnspecifiedEvent registerUnspecifiedEvent = new RegisterUnspecifiedEvent(
                createUnspecified.getId(),
                EventType.ItemRegistered,
                materialType,
                createUnspecified.getName(),
                createUnspecified.getQuantity(),
                createUnspecified.getDescription(),
                createUnspecified.getSpecificationFileUrl()
        );
        MaterialEvent<RegisterUnspecifiedEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerUnspecifiedEvent);
        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }


    private void createSetEvent(SetEntity createSet, String materialType) {
        RegisterSetEvent registerSetEvent = new RegisterSetEvent(
                createSet.getId(),
                EventType.ItemRegistered,
                materialType,
                createSet.getName(),
                createSet.getGalvanisedSheetThickness(),
                createSet.getColor(),
                createSet.getMaxLength(),
                createSet.getQuantity(),
                createSet.getDescription(),
                createSet.getSpecificationFileUrl()
        );
        MaterialEvent<RegisterSetEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerSetEvent);
        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }


    private void createRebarEvent(RebarEntity createRebar, String materialType) {
        RegisterRebarEvent registerRebarEvent = new RegisterRebarEvent(
                createRebar.getId(),
                EventType.ItemRegistered,
                materialType,
                createRebar.getName(),
                createRebar.getMaxLength(),
                createRebar.getWeight(),
                createRebar.getQuantity(),
                createRebar.getDescription(),
                createRebar.getSpecificationFileUrl()
        );
        MaterialEvent<RegisterRebarEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerRebarEvent);
        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }


    private void createPanelEvent(PanelEntity createPanel, String materialType) {
        RegisterPanelEvent registerPanelEvent = new RegisterPanelEvent (
                createPanel.getId(),
                EventType.ItemRegistered,
                materialType,
                createPanel.getName(),
                createPanel.getType(),
                createPanel.getColor(),
                createPanel.getLength(),
                createPanel.getWidth(),
                createPanel.getTotalThickness(),
                createPanel.getFrontSheetThickness(),
                createPanel.getBackSheetThickness(),
                createPanel.getQuantity(),
                createPanel.getDescription(),
                createPanel.getSpecificationFileUrl()
        );
        MaterialEvent<RegisterPanelEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerPanelEvent);
        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);

    }


    private void createMetalEvent(MetalEntity createMetal, String materialType) {
        RegisterMetalEvent registerMetalEvent = new RegisterMetalEvent(
                createMetal.getId(),
                EventType.ItemRegistered,
                materialType,
                createMetal.getName(),
                createMetal.getTotalWeight(),
                createMetal.getQuantity(),
                createMetal.getDescription(),
                createMetal.getSpecificationFileUrl()
        );
        MaterialEvent<RegisterMetalEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerMetalEvent);
        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }


    private void createInsulationEvent(InsulationEntity createInsulation, String materialType) {
        RegisterInsulationEvent registerInsulationEvent = new RegisterInsulationEvent(
                createInsulation.getId(),
                EventType.ItemRegistered,
                materialType,
                createInsulation.getName(),
                createInsulation.getType(),
                createInsulation.getThickness(),
                createInsulation.getQuantity(),
                createInsulation.getDescription(),
                createInsulation.getSpecificationFileUrl()

        );
        MaterialEvent<RegisterInsulationEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerInsulationEvent);
        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }

    private void createGalvanizedEvent(GalvanisedSheetEntity createdGalvanized, String materialType) {
        RegisterGalvanizedEvent registerGalvanizedEvent = new RegisterGalvanizedEvent(
                createdGalvanized.getId(),
                EventType.ItemRegistered,
                materialType,
                createdGalvanized.getName(),
                createdGalvanized.getType(),
                createdGalvanized.getMaxLength(),
                createdGalvanized.getArea(),
                createdGalvanized.getQuantity(),
                createdGalvanized.getDescription(),
                createdGalvanized.getSpecificationFileUrl()
        );
        MaterialEvent<RegisterGalvanizedEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerGalvanizedEvent);
        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }

    private void createFastenersEvent(FastenerEntity createdFastener, String materialType) {
        RegisterFastenerEvent registerMaterialEvent = new RegisterFastenerEvent(
                createdFastener.getId(),
                EventType.ItemRegistered,
                materialType,
                createdFastener.getName(),
                createdFastener.getType(),
                createdFastener.getDescription(),
                createdFastener.getDiameter(),
                createdFastener.getLength(),
                createdFastener.getModel(),
                createdFastener.getClazz(),
                createdFastener.getQuantity(),
                createdFastener.getSpecificationFileUrl()
        );

        MaterialEvent<RegisterFastenerEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerMaterialEvent);
        materialEvent.setMaterialType(MaterialType.FASTENERS);

        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }

    private InsulationEntity mapInsulationEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new InsulationEntity())
                .map(insulationEntity -> {
                    insulationEntity.setName(createMaterialDTO.getType() + " "
                            + createMaterialDTO.getThickness());
                    insulationEntity.setType(createMaterialDTO.getType());
                    insulationEntity.setThickness(createMaterialDTO.getThickness());
                    insulationEntity.setQuantity(createMaterialDTO.getQuantity());
                    insulationEntity.setDescription(createMaterialDTO.getDescription());
                    insulationEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    insulationEntity.setCategory(this.categoryRepository.findByMaterialType(
                            MaterialType.INSULATION).orElse(null));
                    return insulationEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map InsulationEntity"));
    }

    private GalvanisedSheetEntity mapGalvanizedEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new GalvanisedSheetEntity())
                .map(galvanisedEntity -> {
                    galvanisedEntity.setName(createMaterialDTO.getType());
                    galvanisedEntity.setType(createMaterialDTO.getType());
                    galvanisedEntity.setMaxLength(createMaterialDTO.getMaxLength());
                    galvanisedEntity.setArea(createMaterialDTO.getArea());
                    galvanisedEntity.setQuantity(createMaterialDTO.getQuantity());
                    galvanisedEntity.setDescription(createMaterialDTO.getDescription());
                    galvanisedEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    galvanisedEntity.setCategory(this.categoryRepository.findByMaterialType(
                            MaterialType.GALVANIZED_SHEET).orElse(null));
                    return galvanisedEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map GalvanisedSheetEntity"));
    }


    private FastenerEntity mapFastenerEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new FastenerEntity())
                .map(fastenerEntity -> {
                    fastenerEntity.setName(createMaterialDTO.getType() + " " +
                            createMaterialDTO.getDiameter() + " " + createMaterialDTO.getLength());
                    fastenerEntity.setType(createMaterialDTO.getType());
                    fastenerEntity.setDescription(createMaterialDTO.getDescription());
                    fastenerEntity.setDiameter(createMaterialDTO.getDiameter());
                    fastenerEntity.setLength(createMaterialDTO.getLength());
                    fastenerEntity.setModel(createMaterialDTO.getModel());
                    fastenerEntity.setClazz(createMaterialDTO.getClazz());
                    fastenerEntity.setQuantity(createMaterialDTO.getQuantity());
                    fastenerEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    fastenerEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.FASTENERS).orElse(null));
                    return fastenerEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map FastenerEntity"));
    }

    private MetalEntity mapMetalEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new MetalEntity())
                .map(metalEntity -> {
                    metalEntity.setName(createMaterialDTO.getDescription());
                    metalEntity.setTotalWeight(createMaterialDTO.getTotalWeight());
                    metalEntity.setQuantity(createMaterialDTO.getQuantity());
                    metalEntity.setDescription(createMaterialDTO.getDescription());
                    metalEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    metalEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.METAL).orElse(null));
                    return metalEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map MetalEntity"));
    }

    private PanelEntity mapPanelEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new PanelEntity())
                .map(panelEntity -> {
                    panelEntity.setName(createMaterialDTO.getType() + " " + createMaterialDTO.getLength()
                     + " " + createMaterialDTO.getTotalThickness());
                    panelEntity.setType(createMaterialDTO.getType());
                    panelEntity.setColor(createMaterialDTO.getColor());
                    panelEntity.setLength(createMaterialDTO.getLength());
                    panelEntity.setWidth(createMaterialDTO.getWidth());
                    panelEntity.setTotalThickness(createMaterialDTO.getTotalThickness());
                    panelEntity.setFrontSheetThickness(createMaterialDTO.getFrontSheetThickness());
                    panelEntity.setBackSheetThickness(createMaterialDTO.getBackSheetThickness());
                    panelEntity.setQuantity(createMaterialDTO.getQuantity());
                    panelEntity.setDescription(createMaterialDTO.getDescription());
                    panelEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    panelEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.PANELS).orElse(null));
                    return panelEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map PanelEntity"));
    }

    private RebarEntity mapRebarEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new RebarEntity())
                .map(rebarEntity -> {
                    rebarEntity.setName(createMaterialDTO.getDescription());
                    rebarEntity.setMaxLength(createMaterialDTO.getMaxLength());
                    rebarEntity.setWeight(createMaterialDTO.getWeight());
                    rebarEntity.setQuantity(createMaterialDTO.getQuantity());
                    rebarEntity.setDescription(createMaterialDTO.getDescription());
                    rebarEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    rebarEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.REBAR).orElse(null));
                    return rebarEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map RebarEntity"));
    }

    private SetEntity mapSetEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new SetEntity())
                .map(setEntity -> {
                    setEntity.setName(createMaterialDTO.getGalvanisedSheetThickness() + " " +
                            createMaterialDTO.getColor());
                    setEntity.setGalvanisedSheetThickness(createMaterialDTO.getGalvanisedSheetThickness());
                    setEntity.setColor(createMaterialDTO.getColor());
                    setEntity.setMaxLength(String.valueOf(createMaterialDTO.getMaxLength()));
                    setEntity.setQuantity(createMaterialDTO.getQuantity());
                    setEntity.setDescription(createMaterialDTO.getDescription());
                    setEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    setEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.SET).orElse(null));
                    return setEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map SetEntity"));
    }

    private UnspecifiedEntity mapUnspecifiedEntity(CreateMaterialDTO createMaterialDTO) {
        return Optional.of(new UnspecifiedEntity())
                .map(unspecifiedEntity -> {
                    unspecifiedEntity.setName(createMaterialDTO.getDescription());
                    unspecifiedEntity.setQuantity(createMaterialDTO.getQuantity());
                    unspecifiedEntity.setDescription(createMaterialDTO.getDescription());
                    unspecifiedEntity.setSpecificationFileUrl(createMaterialDTO.getSpecificationFileUrl());
                    unspecifiedEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.UNSPECIFIED).orElse(null));
                    return unspecifiedEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map UnspecifiedEntity"));
    }
}
