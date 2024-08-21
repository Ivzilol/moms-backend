package bg.mck.service;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.*;
import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.*;
import bg.mck.exceptions.DuplicatedInventoryItemException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.*;
import bg.mck.utils.EventCreationHelper;
import bg.mck.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
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

    private final MaterialMapper materialMapper;

    public MaterialRegisterService(CategoryRepository categoryRepository,
                                   FastenerRepository fastenerRepository,
                                   GalvanisedSheetRepository galvanisedSheetRepository,
                                   InventoryQueryServiceClient inventoryQueryServiceClient,
                                   InsulationRepository insulationRepository,
                                   MetalRepository metalRepository,
                                   PanelRepository panelRepository,
                                   RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, MaterialMapper materialMapper) {
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
        this.materialMapper = materialMapper;
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

    public void createMaterial(CreateMaterialDTO createMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        if (materialExists(createMaterialDTO)) {
            throw new DuplicatedInventoryItemException("Material already exists.");
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.FASTENERS)) {
            FastenerRegisterDTO fastenerDTO = materialMapper.mapFastenerRegisterDtoFromCreateMaterialDTO(createMaterialDTO);

            validateRegisterDTO(fastenerDTO);

            FastenerEntity fastenerEntity = mapFastenerEntity(fastenerDTO);
            this.fastenerRepository.save(fastenerEntity);
            FastenerEntity createdFastener = this.fastenerRepository
                    .findByName(createMaterialDTO.getType() + " " +
                            createMaterialDTO.getDiameter() + " " + createMaterialDTO.getLength() + " " + createMaterialDTO.getLengthUnit());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.FASTENERS);
            String materialType = byMaterialType.get().getMaterialType().name();

            createFastenersEvent(createdFastener, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.GALVANIZED_SHEET)) {
            GalvanizedSheetRegisterDTO galvanizedSheetRegisterDTO = materialMapper.mapGalvanizedSheetRegisterDtoFromCreateMaterialDTO(createMaterialDTO);


            validateRegisterDTO(galvanizedSheetRegisterDTO);

            GalvanisedSheetEntity galvanisedSheetEntity = mapGalvanizedEntity(galvanizedSheetRegisterDTO);
            this.galvanisedSheetRepository.save(galvanisedSheetEntity);
            GalvanisedSheetEntity createdGalvanized = this.galvanisedSheetRepository
                    .findByName(createMaterialDTO.getType());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.GALVANIZED_SHEET);
            String materialType = byMaterialType.get().getMaterialType().name();

            createGalvanizedEvent(createdGalvanized, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.INSULATION)) {
            InsulationRegisterDTO insulationRegisterDTO = materialMapper.mapInsulationRegisterDtoFromCreateMaterialDTO(createMaterialDTO);


            validateRegisterDTO(insulationRegisterDTO);

            InsulationEntity insulationEntity = mapInsulationEntity(insulationRegisterDTO);
            this.insulationRepository.save(insulationEntity);
            InsulationEntity createInsulation = this.insulationRepository
                    .findByName(createMaterialDTO.getType() + " "
                            + createMaterialDTO.getThickness() + " " + createMaterialDTO.getThicknessUnit());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.INSULATION);
            String materialType = byMaterialType.get().getMaterialType().name();

            createInsulationEvent(createInsulation, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.METAL)) {
            MetalRegisterDTO metalRegisterDTO = materialMapper.mapMetalRegisterDtoFromCreateMaterialDTO(createMaterialDTO);


            validateRegisterDTO(metalRegisterDTO);

            MetalEntity metalEntity = mapMetalEntity(metalRegisterDTO);
            this.metalRepository.save(metalEntity);
            MetalEntity createMetal = this.metalRepository
                    .findByName(createMaterialDTO.getDescription());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.METAL);
            String materialType = byMaterialType.get().getMaterialType().name();

            createMetalEvent(createMetal, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.PANELS)) {
            PanelRegisterDTO panelRegisterDTO = materialMapper.mapPanelRegisterDtoFromCreateMaterialDTO(createMaterialDTO);


            validateRegisterDTO(panelRegisterDTO);

            PanelEntity panelEntity = mapPanelEntity(panelRegisterDTO);
            this.panelRepository.save(panelEntity);
            PanelEntity createPanel = this.panelRepository
                    .findByName(createMaterialDTO.getType() + " " + createMaterialDTO.getLength() + " " + createMaterialDTO.getLengthUnit()
                            + " " + createMaterialDTO.getTotalThickness() + " " + createMaterialDTO.getTotalThicknessUnit());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.PANELS);
            String materialType = byMaterialType.get().getMaterialType().name();

            createPanelEvent(createPanel, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.REBAR)) {
            RebarRegisterDTO rebarRegisterDTO = materialMapper.mapRebarRegisterDtoFromCreateMaterialDTO(createMaterialDTO);

            validateRegisterDTO(rebarRegisterDTO);

            RebarEntity rebarEntity = mapRebarEntity(rebarRegisterDTO);
            this.rebarRepository.save(rebarEntity);
            RebarEntity createRebar = this.rebarRepository
                    .findByName(createMaterialDTO.getDescription());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.REBAR);
            String materialType = byMaterialType.get().getMaterialType().name();

            createRebarEvent(createRebar, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.SET)) {
            SetRegisterDTO setRegisterDTO = materialMapper.mapSetRegisterDtoFromCreateMaterialDTO(createMaterialDTO);

            validateRegisterDTO(setRegisterDTO);

            SetEntity setEntity = mapSetEntity(setRegisterDTO);
            this.setRepository.save(setEntity);
            SetEntity createSet = this.setRepository
                    .findByName(setRegisterDTO.getColor() + " " + setRegisterDTO.getMaxLength() + " " + setRegisterDTO.getMaxLengthUnit());

            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.SET);
            String materialType = byMaterialType.get().getMaterialType().name();

            createSetEvent(createSet, materialType);
        }

        if (createMaterialDTO.getMaterialType().equals(MaterialType.UNSPECIFIED)) {
            UnspecifiedRegisterDTO unspecifiedRegisterDTO = materialMapper.mapUnspecifiedRegisterDtoFromCreateMaterialDTO(createMaterialDTO);

            validateRegisterDTO(unspecifiedRegisterDTO);

            UnspecifiedEntity unspecifiedEntity = mapUnspecifiedEntity(unspecifiedRegisterDTO);
            this.unspecifiedRepository.save(unspecifiedEntity);
            UnspecifiedEntity createUnspecified =
                    this.unspecifiedRepository.findByName(createMaterialDTO.getDescription());
            Optional<CategoryEntity> byMaterialType = this.categoryRepository
                    .findByMaterialType(MaterialType.UNSPECIFIED);
            String materialType = byMaterialType.get().getMaterialType().name();

            createUnspecifiedEvent(createUnspecified, materialType);
        }
    }

    public void createMaterialsFromOrder(CreateOrderMaterialsDto createOrderMaterialsDto) throws MethodArgumentNotValidException, NoSuchMethodException {
        for (var entry : createOrderMaterialsDto.getMaterials().entrySet()) {
            String category = entry.getKey();
            List<CreateMaterialDTO> materials = entry.getValue();

            for (CreateMaterialDTO material : materials) {
                if (category.equals(MaterialType.FASTENERS.name())) {
                    material.setMaterialType(MaterialType.FASTENERS);
                } else if (category.equals(MaterialType.GALVANIZED_SHEET.name())) {
                    material.setMaterialType(MaterialType.GALVANIZED_SHEET);
                } else if (category.equals(MaterialType.INSULATION.name())) {
                    material.setMaterialType(MaterialType.INSULATION);
                } else if (category.equals(MaterialType.METAL.name())) {
                    material.setMaterialType(MaterialType.METAL);
                } else if (category.equals(MaterialType.REBAR.name())) {
                    material.setMaterialType(MaterialType.REBAR);
                } else if (category.equals(MaterialType.SET.name())) {
                    material.setMaterialType(MaterialType.SET);
                } else if (category.equals(MaterialType.UNSPECIFIED.name())) {
                    material.setMaterialType(MaterialType.UNSPECIFIED);
                } else if (category.equals(MaterialType.PANELS.name())) {
                    material.setMaterialType(MaterialType.PANELS);
                }

                boolean doesExist = materialExists(material);
                if (!doesExist) {
                    createMaterial(material);
                }

            }
        }
    }

    private void validateRegisterDTO(BaseDTO registerDto) throws MethodArgumentNotValidException, NoSuchMethodException {
         ValidationUtil.isValid(registerDto, "registerDto");
    }


    private void createUnspecifiedEvent(UnspecifiedEntity createUnspecified, String materialType) {
        RegisterUnspecifiedEvent registerUnspecifiedEvent = new RegisterUnspecifiedEvent(
                createUnspecified.getId(),
                EventType.ItemRegistered,
                materialType,
                createUnspecified.getName(),
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
                createSet.getColor(),
                createSet.getMaxLength(),
                createSet.getMaxLengthUnit(),
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
                createRebar.getMaxLengthUnit(),
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
                createPanel.getLengthUnit(),
                createPanel.getWidth(),
                createPanel.getWidthUnit(),
                createPanel.getTotalThickness(),
                createPanel.getTotalThicknessUnit(),
                createPanel.getFrontSheetThickness(),
                createPanel.getFrontSheetThicknessUnit(),
                createPanel.getBackSheetThickness(),
                createPanel.getBackSheetThicknessUnit(),
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
                createMetal.getTotalWeightUnit(),
                createMetal.getKind(),
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
                createInsulation.getThicknessUnit(),
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
                createdGalvanized.getMaxLengthUnit(),
                createdGalvanized.getNumberOfSheets(),
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
                createdFastener.getDescription(),
                createdFastener.getDiameter(),
                createdFastener.getLength(),
                createdFastener.getLengthUnit(),
                createdFastener.getStandard(),
                createdFastener.getClazz(),
                createdFastener.getType(),
                createdFastener.getSpecificationFileUrl()
        );

        MaterialEvent<RegisterFastenerEvent> materialEvent =
                EventCreationHelper.toMaterialEvent(registerMaterialEvent);
        materialEvent.setMaterialType(MaterialType.FASTENERS);

        inventoryQueryServiceClient.sendMaterialEvent(materialEvent,
                String.valueOf(EventType.ItemRegistered), materialType);
    }

    private InsulationEntity mapInsulationEntity(InsulationRegisterDTO insulationRegisterDTO) {
        return Optional.of(new InsulationEntity())
                .map(insulationEntity -> {
                    insulationEntity.setName(insulationRegisterDTO.getType() + " "
                            + insulationRegisterDTO.getThickness() + " " + insulationRegisterDTO.getThicknessUnit());
                    insulationEntity.setType(insulationRegisterDTO.getType());
                    insulationEntity.setThickness(insulationRegisterDTO.getThickness());
                    insulationEntity.setThicknessUnit(insulationRegisterDTO.getThicknessUnit());
                    insulationEntity.setDescription(insulationRegisterDTO.getDescription());
                    insulationEntity.setSpecificationFileUrl(insulationRegisterDTO.getSpecificationFileUrl());
                    insulationEntity.setCategory(this.categoryRepository.findByMaterialType(
                            MaterialType.INSULATION).orElse(null));
                    return insulationEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map InsulationEntity"));
    }

    private GalvanisedSheetEntity mapGalvanizedEntity(GalvanizedSheetRegisterDTO galvanizedSheetRegisterDTO) {
        return Optional.of(new GalvanisedSheetEntity())
                .map(galvanisedEntity -> {
                    galvanisedEntity.setName(galvanizedSheetRegisterDTO.getType());
                    galvanisedEntity.setType(galvanizedSheetRegisterDTO.getType());
                    galvanisedEntity.setMaxLength(galvanizedSheetRegisterDTO.getMaxLength());
                    galvanisedEntity.setMaxLengthUnit(galvanizedSheetRegisterDTO.getMaxLengthUnit());
                    galvanisedEntity.setNumberOfSheets(galvanizedSheetRegisterDTO.getNumberOfSheets());
                    galvanisedEntity.setDescription(galvanizedSheetRegisterDTO.getDescription());
                    galvanisedEntity.setSpecificationFileUrl(galvanizedSheetRegisterDTO.getSpecificationFileUrl());
                    galvanisedEntity.setCategory(this.categoryRepository.findByMaterialType(
                            MaterialType.GALVANIZED_SHEET).orElse(null));
                    return galvanisedEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map GalvanisedSheetEntity"));
    }


    private FastenerEntity mapFastenerEntity(FastenerRegisterDTO fastenerRegisterDTO) {
        return Optional.of(new FastenerEntity())
                .map(fastenerEntity -> {
                    fastenerEntity.setName(fastenerRegisterDTO.getType() + " " +
                            fastenerRegisterDTO.getDiameter() + " " + fastenerRegisterDTO.getLength() + " " + fastenerRegisterDTO.getLengthUnit());
                    fastenerEntity.setType(fastenerRegisterDTO.getType());
                    fastenerEntity.setDescription(fastenerRegisterDTO.getDescription());
                    fastenerEntity.setDiameter(fastenerRegisterDTO.getDiameter());
                    fastenerEntity.setLength(fastenerRegisterDTO.getLength());
                    fastenerEntity.setLengthUnit(fastenerRegisterDTO.getLengthUnit());
                    fastenerEntity.setStandard(fastenerRegisterDTO.getStandard());
                    fastenerEntity.setClazz(fastenerRegisterDTO.getClazz());
                    fastenerEntity.setSpecificationFileUrl(fastenerRegisterDTO.getSpecificationFileUrl());
                    fastenerEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.FASTENERS).orElse(null));
                    return fastenerEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map FastenerEntity"));
    }

    private MetalEntity mapMetalEntity(MetalRegisterDTO metalRegisterDTO) {
        return Optional.of(new MetalEntity())
                .map(metalEntity -> {
                    metalEntity.setName(metalRegisterDTO.getDescription());
                    metalEntity.setTotalWeight(metalRegisterDTO.getTotalWeight());
                    metalEntity.setTotalWeightUnit(metalRegisterDTO.getTotalWeightUnit());
                    metalEntity.setDescription(metalRegisterDTO.getDescription());
                    metalEntity.setKind(metalRegisterDTO.getKind());
                    metalEntity.setSpecificationFileUrl(metalRegisterDTO.getSpecificationFileUrl());
                    metalEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.METAL).orElse(null));
                    return metalEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map MetalEntity"));
    }

    private PanelEntity mapPanelEntity(PanelRegisterDTO panelRegisterDTO) {
        return Optional.of(new PanelEntity())
                .map(panelEntity -> {
                    panelEntity.setName(panelRegisterDTO.getType() + " " + panelRegisterDTO.getLength() + " " + panelRegisterDTO.getLengthUnit()
                            + " " + panelRegisterDTO.getTotalThickness() + " " + panelRegisterDTO.getTotalThicknessUnit());
                    panelEntity.setType(panelRegisterDTO.getType());
                    panelEntity.setColor(panelRegisterDTO.getColor());
                    panelEntity.setLength(panelRegisterDTO.getLength());
                    panelEntity.setLengthUnit(panelRegisterDTO.getLengthUnit());
                    panelEntity.setWidth(panelRegisterDTO.getWidth());
                    panelEntity.setWidthUnit(panelRegisterDTO.getWidthUnit());
                    panelEntity.setTotalThickness(panelRegisterDTO.getTotalThickness());
                    panelEntity.setTotalThicknessUnit(panelRegisterDTO.getTotalThicknessUnit());
                    panelEntity.setFrontSheetThickness(panelRegisterDTO.getFrontSheetThickness());
                    panelEntity.setFrontSheetThicknessUnit(panelRegisterDTO.getFrontSheetThicknessUnit());
                    panelEntity.setBackSheetThickness(panelRegisterDTO.getBackSheetThickness());
                    panelEntity.setBackSheetThicknessUnit(panelRegisterDTO.getBackSheetThicknessUnit());
                    panelEntity.setDescription(panelRegisterDTO.getDescription());
                    panelEntity.setSpecificationFileUrl(panelRegisterDTO.getSpecificationFileUrl());
                    panelEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.PANELS).orElse(null));
                    return panelEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map PanelEntity"));
    }

    private RebarEntity mapRebarEntity(RebarRegisterDTO rebarRegisterDTO) {
        return Optional.of(new RebarEntity())
                .map(rebarEntity -> {
                    rebarEntity.setName(rebarRegisterDTO.getDescription());
                    rebarEntity.setMaxLength(rebarRegisterDTO.getMaxLength());
                    rebarEntity.setMaxLengthUnit(rebarRegisterDTO.getMaxLengthUnit());
                    rebarEntity.setDescription(rebarRegisterDTO.getDescription());
                    rebarEntity.setSpecificationFileUrl(rebarRegisterDTO.getSpecificationFileUrl());
                    rebarEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.REBAR).orElse(null));
                    return rebarEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map RebarEntity"));
    }

    private SetEntity mapSetEntity(SetRegisterDTO setRegisterDTO) {
        return Optional.of(new SetEntity())
                .map(setEntity -> {
                    setEntity.setName(setRegisterDTO.getColor() + " " + setRegisterDTO.getMaxLength() + " " + setRegisterDTO.getMaxLengthUnit());
                    setEntity.setColor(setRegisterDTO.getColor());
                    setEntity.setMaxLength(setRegisterDTO.getMaxLength());
                    setEntity.setMaxLengthUnit(setRegisterDTO.getMaxLengthUnit());
                    setEntity.setDescription(setRegisterDTO.getDescription());
                    setEntity.setSpecificationFileUrl(setRegisterDTO.getSpecificationFileUrl());
                    setEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.SET).orElse(null));
                    return setEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map SetEntity"));
    }

    private UnspecifiedEntity mapUnspecifiedEntity(UnspecifiedRegisterDTO unspecifiedRegisterDTO) {
        return Optional.of(new UnspecifiedEntity())
                .map(unspecifiedEntity -> {
                    unspecifiedEntity.setName(unspecifiedRegisterDTO.getDescription());
                    unspecifiedEntity.setDescription(unspecifiedRegisterDTO.getDescription());
                    unspecifiedEntity.setSpecificationFileUrl(unspecifiedRegisterDTO.getSpecificationFileUrl());
                    unspecifiedEntity.setCategory(categoryRepository.findByMaterialType(
                            MaterialType.UNSPECIFIED).orElse(null));
                    return unspecifiedEntity;
                }).orElseThrow(() -> new RuntimeException("Failed to map UnspecifiedEntity"));
    }

    boolean materialExists(CreateMaterialDTO createMaterialDTO) {
        if (createMaterialDTO.getMaterialType().equals(MaterialType.FASTENERS)) {
            if (createMaterialDTO.getType() == null || createMaterialDTO.getDiameter() == null || createMaterialDTO.getLength() == null ||
            createMaterialDTO.getLengthUnit() == null) {
                return true;
            }
            FastenerEntity byName = this.fastenerRepository.findByName(createMaterialDTO.getType() + " " +
                    createMaterialDTO.getDiameter() + " " + createMaterialDTO.getLength() + " " + createMaterialDTO.getLengthUnit());
            return byName != null;
        }
        if (createMaterialDTO.getMaterialType().equals(MaterialType.GALVANIZED_SHEET)) {
            if (createMaterialDTO.getType() == null) {
                return true;
            }
            GalvanisedSheetEntity byName = this.galvanisedSheetRepository.findByName(createMaterialDTO.getType());
            return byName != null;
        }
        if (createMaterialDTO.getMaterialType().equals(MaterialType.INSULATION)) {
            if (createMaterialDTO.getType() == null || createMaterialDTO.getThickness() == null || createMaterialDTO.getThicknessUnit() == null) {
                return true;
            }
            InsulationEntity byName = this.insulationRepository.findByName(createMaterialDTO.getType() + " "
                    + createMaterialDTO.getThickness() + " " + createMaterialDTO.getThicknessUnit());
            return byName != null;
        }
        if (createMaterialDTO.getMaterialType().equals(MaterialType.METAL)) {
            if (createMaterialDTO.getDescription() == null) {
                return true;
            }
            MetalEntity byName = this.metalRepository.findByName(createMaterialDTO.getDescription());
            return byName != null;
        }
        if (createMaterialDTO.getMaterialType().equals(MaterialType.PANELS)) {
            if (createMaterialDTO.getType() == null || createMaterialDTO.getLength() == null || createMaterialDTO.getLengthUnit() == null ||
                    createMaterialDTO.getTotalThickness() == null || createMaterialDTO.getTotalThicknessUnit() == null) {
                return true;
            }
            PanelEntity byName = this.panelRepository.findByName(createMaterialDTO.getType() + " " + createMaterialDTO.getLength() + " " + createMaterialDTO.getLengthUnit()
                    + " " + createMaterialDTO.getTotalThickness() + " " + createMaterialDTO.getTotalThicknessUnit());
            return byName != null;
        }
        if (createMaterialDTO.getMaterialType().equals(MaterialType.REBAR)) {
            if (createMaterialDTO.getDescription() == null) {
                return true;
            }
            RebarEntity byName = this.rebarRepository.findByName(createMaterialDTO.getDescription());
            return byName != null;
        }
        if (createMaterialDTO.getMaterialType().equals(MaterialType.SET)) {
            if (createMaterialDTO.getColor() == null || createMaterialDTO.getMaxLength() == null || createMaterialDTO.getMaxLengthUnit() == null) {
                return true;
            }
            SetEntity byName = this.setRepository.findByName(createMaterialDTO.getColor() + " " + createMaterialDTO.getMaxLength() + " " + createMaterialDTO.getMaxLengthUnit());
            return byName != null;
        }
        if (createMaterialDTO.getMaterialType().equals(MaterialType.UNSPECIFIED)) {
            if (createMaterialDTO.getDescription() == null) {
                return true;
            }
            UnspecifiedEntity byName = this.unspecifiedRepository
                    .findByName(createMaterialDTO.getDescription());
            return byName != null;
        }
        return false;
    }


}
