package bg.mck.controller;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.WeightUnits;
import bg.mck.events.material.*;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.material.*;
import bg.mck.service.MaterialSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MaterialEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FastenerRepository fastenerRepository;

    @Autowired
    private GalvanisedSheetRepository galvanisedSheetRepository;

    @Autowired
    private InsulationRepository insulationRepository;

    @Autowired
    private MetalRepository metalRepository;

    @Autowired
    private RebarRepository rebarRepository;

    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private UnspecifiedRepository unspecifiedRepository;

    @Autowired
    private EventMaterialRepository materialEventRepository;

    @Autowired
    private MaterialSearchService materialSearchService;

    @Autowired
    private CacheManager cacheManager;

    private String cacheKey;

    @BeforeEach
    public void setUp() {
        fastenerRepository.deleteAll();
        galvanisedSheetRepository.deleteAll();
        insulationRepository.deleteAll();
        metalRepository.deleteAll();
        rebarRepository.deleteAll();
        panelRepository.deleteAll();
        setRepository.deleteAll();
        unspecifiedRepository.deleteAll();
        materialEventRepository.deleteAll();
    }


    @Test
    public void testCacheEvictionAfterFastenerRegistration() throws Exception {
        cacheFastenerEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        registerFastener();

        // Assert
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(1L, "FASTENERS");
        assertFalse(savedEvents.isEmpty());

        RegisterFastenerEvent registerFastenerEvent = (RegisterFastenerEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterFastenerEvent
        assertEquals("FastenerName", registerFastenerEvent.getName());
        assertEquals("FASTENERS", registerFastenerEvent.getCategory());
        assertEquals("A standard bolt", registerFastenerEvent.getDescription());
        assertEquals("5mm", registerFastenerEvent.getDiameter());
        assertEquals("20mm", registerFastenerEvent.getLength());
        assertEquals("MM", registerFastenerEvent.getLengthUnit().name());
        assertEquals("ISO", registerFastenerEvent.getStandard());
        assertEquals("Class8", registerFastenerEvent.getClazz());
        assertEquals("Bolt", registerFastenerEvent.getType());
        assertEquals("http://example.com/spec.pdf", registerFastenerEvent.getSpecificationFileUrl());

        // Verify the FastenerEntity is updated in the repository
        Optional<FastenerEntity> fastenerEntity = fastenerRepository.findById("1");
        assertTrue(fastenerEntity.isPresent());

        FastenerEntity entity = fastenerEntity.get();

        // Verify all fields of the FastenerEntity
        assertEquals("FastenerName", entity.getName());
        assertEquals("A standard bolt", entity.getDescription());
        assertEquals("5mm", entity.getDiameter());
        assertEquals("20mm", entity.getLength());
        assertEquals("MM", entity.getLengthUnit().name());
        assertEquals("ISO", entity.getStandard());
        assertEquals("Class8", entity.getClazz());
        assertEquals("Bolt", entity.getType());
        assertEquals("http://example.com/spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterGalvanizedSheetRegistration() throws Exception {
        cacheGalvanizedSheetEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        registerGalvanizedSheet();

        // Assert
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(2L, "GALVANIZED_SHEET");
        assertFalse(savedEvents.isEmpty());

        RegisterGalvanizedEvent registerGalvanizedEvent = (RegisterGalvanizedEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterGalvanizedEvent
        assertEquals("GalvanizedSheetName", registerGalvanizedEvent.getName());
        assertEquals("GALVANIZED_SHEET", registerGalvanizedEvent.getCategory());
        assertEquals("A standard sheet", registerGalvanizedEvent.getDescription());
        assertEquals("SheetType", registerGalvanizedEvent.getType());
        assertEquals("1000mm", registerGalvanizedEvent.getMaxLength());
        assertEquals("MM", registerGalvanizedEvent.getMaxLengthUnit().name());
        assertEquals("10", registerGalvanizedEvent.getNumberOfSheets());
        assertEquals("http://example.com/sheet_spec.pdf", registerGalvanizedEvent.getSpecificationFileUrl());

        // Verify the GalvanizedSheetEntity is updated in the repository
        Optional<GalvanisedSheetEntity> galvanizedSheetEntity = galvanisedSheetRepository.findById("2");
        assertTrue(galvanizedSheetEntity.isPresent());

        GalvanisedSheetEntity entity = galvanizedSheetEntity.get();

        // Verify all fields of the GalvanizedSheetEntity
        assertEquals("GalvanizedSheetName", entity.getName());
        assertEquals("A standard sheet", entity.getDescription());
        assertEquals("SheetType", entity.getType());
        assertEquals("1000mm", entity.getMaxLength());
        assertEquals("MM", entity.getMaxLengthUnit().name());
        assertEquals("10", entity.getNumberOfSheets());
        assertEquals("http://example.com/sheet_spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterInsulationRegistration() throws Exception {
        // Cache the insulation entity before the registration
        cacheInsulationEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Register the insulation
        registerInsulation();

        // Assert that the event is saved and the cache is evicted
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(3L, "INSULATION");
        assertFalse(savedEvents.isEmpty());

        RegisterInsulationEvent registerInsulationEvent = (RegisterInsulationEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterInsulationEvent
        assertEquals("InsulationName", registerInsulationEvent.getName());
        assertEquals("INSULATION", registerInsulationEvent.getCategory());
        assertEquals("A standard insulation", registerInsulationEvent.getDescription());
        assertEquals("Foam", registerInsulationEvent.getType());
        assertEquals("40mm", registerInsulationEvent.getThickness());
        assertEquals("MM", registerInsulationEvent.getThicknessUnit().name());
        assertEquals("http://example.com/insulation_spec.pdf", registerInsulationEvent.getSpecificationFileUrl());

        // Verify the InsulationEntity is updated in the repository
        Optional<InsulationEntity> insulationEntity = insulationRepository.findById("3");
        assertTrue(insulationEntity.isPresent());

        InsulationEntity entity = insulationEntity.get();

        // Verify all fields of the InsulationEntity
        assertEquals("InsulationName", entity.getName());
        assertEquals("A standard insulation", entity.getDescription());
        assertEquals("Foam", entity.getType());
        assertEquals("40mm", entity.getThickness());
        assertEquals("MM", entity.getThicknessUnit().name());
        assertEquals("http://example.com/insulation_spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterMetalRegistration() throws Exception {
        // Cache the metal entity before the registration
        cacheMetalEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Register the metal
        registerMetal();

        // Assert that the event is saved and the cache is evicted
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(4L, "METAL");
        assertFalse(savedEvents.isEmpty());

        RegisterMetalEvent registerMetalEvent = (RegisterMetalEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterMetalEvent
        assertEquals("MetalName", registerMetalEvent.getName());
        assertEquals("METAL", registerMetalEvent.getCategory());
        assertEquals("A standard metal", registerMetalEvent.getDescription());
        assertEquals("Steel", registerMetalEvent.getKind());
        assertEquals("100kg", registerMetalEvent.getTotalWeight());
        assertEquals("KG", registerMetalEvent.getTotalWeightUnit().name());
        assertEquals("http://example.com/metal_spec.pdf", registerMetalEvent.getSpecificationFileUrl());

        // Verify the MetalEntity is updated in the repository
        Optional<MetalEntity> metalEntity = metalRepository.findById("4");
        assertTrue(metalEntity.isPresent());

        MetalEntity entity = metalEntity.get();

        // Verify all fields of the MetalEntity
        assertEquals("MetalName", entity.getName());
        assertEquals("A standard metal", entity.getDescription());
        assertEquals("Steel", entity.getKind());
        assertEquals("100kg", entity.getTotalWeight());
        assertEquals("KG", entity.getTotalWeightUnit().name());
        assertEquals("http://example.com/metal_spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterPanelRegistration() throws Exception {
        // Cache the panel entity before the registration
        cachePanelEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Register the panel
        registerPanel();

        // Assert that the event is saved and the cache is evicted
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(5L, "PANELS");
        assertFalse(savedEvents.isEmpty());

        RegisterPanelEvent registerPanelEvent = (RegisterPanelEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterPanelEvent
        assertEquals("PanelName", registerPanelEvent.getName());
        assertEquals("PANELS", registerPanelEvent.getCategory());
        assertEquals("A standard panel", registerPanelEvent.getDescription());
        assertEquals("White", registerPanelEvent.getColor());
        assertEquals("200cm", registerPanelEvent.getLength());
        assertEquals("CM", registerPanelEvent.getLengthUnit().name());
        assertEquals("100cm", registerPanelEvent.getWidth());
        assertEquals("CM", registerPanelEvent.getWidthUnit().name());
        assertEquals("5cm", registerPanelEvent.getTotalThickness());
        assertEquals("CM", registerPanelEvent.getTotalThicknessUnit().name());
        assertEquals("1mm", registerPanelEvent.getFrontSheetThickness());
        assertEquals("MM", registerPanelEvent.getFrontSheetThicknessUnit().name());
        assertEquals("1mm", registerPanelEvent.getBackSheetThickness());
        assertEquals("MM", registerPanelEvent.getBackSheetThicknessUnit().name());
        assertEquals("http://example.com/panel_spec.pdf", registerPanelEvent.getSpecificationFileUrl());

        // Verify the PanelEntity is updated in the repository
        Optional<PanelEntity> panelEntity = panelRepository.findById("5");
        assertTrue(panelEntity.isPresent());

        PanelEntity entity = panelEntity.get();

        // Verify all fields of the PanelEntity
        assertEquals("PanelName", entity.getName());
        assertEquals("A standard panel", entity.getDescription());
        assertEquals("White", entity.getColor());
        assertEquals("200cm", entity.getLength());
        assertEquals("CM", entity.getLengthUnit().name());
        assertEquals("100cm", entity.getWidth());
        assertEquals("CM", entity.getWidthUnit().name());
        assertEquals("5cm", entity.getTotalThickness());
        assertEquals("CM", entity.getTotalThicknessUnit().name());
        assertEquals("1mm", entity.getFrontSheetThickness());
        assertEquals("MM", entity.getFrontSheetThicknessUnit().name());
        assertEquals("1mm", entity.getBackSheetThickness());
        assertEquals("MM", entity.getBackSheetThicknessUnit().name());
        assertEquals("http://example.com/panel_spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterRebarRegistration() throws Exception {
        // Cache the rebar entity before the registration
        cacheRebarEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Register the rebar
        registerRebar();

        // Assert that the event is saved and the cache is evicted
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(6L, "REBAR");
        assertFalse(savedEvents.isEmpty());

        RegisterRebarEvent registerRebarEvent = (RegisterRebarEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterRebarEvent
        assertEquals("RebarName", registerRebarEvent.getName());
        assertEquals("REBAR", registerRebarEvent.getCategory());
        assertEquals("A standard rebar", registerRebarEvent.getDescription());
        assertEquals("500cm", registerRebarEvent.getMaxLength());
        assertEquals("CM", registerRebarEvent.getMaxLengthUnit().name());
        assertEquals("http://example.com/rebar_spec.pdf", registerRebarEvent.getSpecificationFileUrl());

        // Verify the RebarEntity is updated in the repository
        Optional<RebarEntity> rebarEntity = rebarRepository.findById("6");
        assertTrue(rebarEntity.isPresent());

        RebarEntity entity = rebarEntity.get();

        // Verify all fields of the RebarEntity
        assertEquals("RebarName", entity.getName());
        assertEquals("A standard rebar", entity.getDescription());
        assertEquals("500cm", entity.getMaxLength());
        assertEquals("CM", entity.getMaxLengthUnit().name());
        assertEquals("http://example.com/rebar_spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterSetRegistration() throws Exception {
        // Cache the Set entity before the registration
        cacheSetEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Register the Set
        registerSet();

        // Assert that the event is saved and the cache is evicted
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(7L, "SET");
        assertFalse(savedEvents.isEmpty());

        RegisterSetEvent registerSetEvent = (RegisterSetEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterSetEvent
        assertEquals("SetName", registerSetEvent.getName());
        assertEquals("SET", registerSetEvent.getCategory());
        assertEquals("A standard set", registerSetEvent.getDescription());
        assertEquals("Red", registerSetEvent.getColor());
        assertEquals("100cm", registerSetEvent.getMaxLength());
        assertEquals("CM", registerSetEvent.getMaxLengthUnit().name());
        assertEquals("http://example.com/set_spec.pdf", registerSetEvent.getSpecificationFileUrl());

        // Verify the SetEntity is updated in the repository
        Optional<SetEntity> setEntity = setRepository.findById("7");
        assertTrue(setEntity.isPresent());

        SetEntity entity = setEntity.get();

        // Verify all fields of the SetEntity
        assertEquals("SetName", entity.getName());
        assertEquals("A standard set", entity.getDescription());
        assertEquals("Red", entity.getColor());
        assertEquals("100cm", entity.getMaxLength());
        assertEquals("CM", entity.getMaxLengthUnit().name());
        assertEquals("http://example.com/set_spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterUnspecifiedRegistration() throws Exception {
        // Cache the Unspecified entity before the registration
        cacheUnspecifiedEntity();

        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Register the Unspecified entity
        registerUnspecified();

        // Assert that the event is saved and the cache is evicted
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(8L, "UNSPECIFIED");
        assertFalse(savedEvents.isEmpty());

        RegisterUnspecifiedEvent registerUnspecifiedEvent = (RegisterUnspecifiedEvent) savedEvents.get(0).getEvent();

        // Verify all fields of the RegisterUnspecifiedEvent
        assertEquals("UnspecifiedName", registerUnspecifiedEvent.getName());
        assertEquals("UNSPECIFIED", registerUnspecifiedEvent.getCategory());
        assertEquals("An unspecified material", registerUnspecifiedEvent.getDescription());
        assertEquals("http://example.com/unspecified_spec.pdf", registerUnspecifiedEvent.getSpecificationFileUrl());

        // Verify the UnspecifiedEntity is updated in the repository
        Optional<UnspecifiedEntity> unspecifiedEntity = unspecifiedRepository.findById("8");
        assertTrue(unspecifiedEntity.isPresent());

        UnspecifiedEntity entity = unspecifiedEntity.get();

        // Verify all fields of the UnspecifiedEntity
        assertEquals("UnspecifiedName", entity.getName());
        assertEquals("An unspecified material", entity.getDescription());
        assertEquals("http://example.com/unspecified_spec.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterFastenerUpdate() throws Exception {
        // First, register the fastener
        registerFastener();

        // Cache the fastener entity
        cacheFastenerEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "FASTENERS",
        "event": {
            "materialId": 1,
            "category": "FASTENERS",
            "name": "FastenerNameUpdated",
            "description": "An updated high-strength bolt",
            "diameter": "6mm",
            "length": "25mm",
            "lengthUnit": "CM",
            "standard": "DIN",
            "clazz": "Class10",
            "type": "HexBolt",
            "specificationFileUrl": "http://example.com/spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "FASTENERS")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(1L, "FASTENERS");
        assertFalse(savedEvents.isEmpty());

        UpdateFastenerEvent updatedFastenerEvent = (UpdateFastenerEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the RegisterFastenerEvent after update
        assertEquals("FastenerNameUpdated", updatedFastenerEvent.getName());
        assertEquals("FASTENERS", updatedFastenerEvent.getCategory());
        assertEquals("An updated high-strength bolt", updatedFastenerEvent.getDescription());
        assertEquals("6mm", updatedFastenerEvent.getDiameter());
        assertEquals("25mm", updatedFastenerEvent.getLength());
        assertEquals("CM", updatedFastenerEvent.getLengthUnit().name());
        assertEquals("DIN", updatedFastenerEvent.getStandard());
        assertEquals("Class10", updatedFastenerEvent.getClazz());
        assertEquals("HexBolt", updatedFastenerEvent.getType());
        assertEquals("http://example.com/spec_updated.pdf", updatedFastenerEvent.getSpecificationFileUrl());

        // Verify the FastenerEntity is updated in the repository
        Optional<FastenerEntity> fastenerEntity = fastenerRepository.findById("1");
        assertTrue(fastenerEntity.isPresent());

        FastenerEntity entity = fastenerEntity.get();

        // Verify all fields of the FastenerEntity after update
        assertEquals("FastenerNameUpdated", entity.getName());
        assertEquals("An updated high-strength bolt", entity.getDescription());
        assertEquals("6mm", entity.getDiameter());
        assertEquals("25mm", entity.getLength());
        assertEquals("CM", entity.getLengthUnit().name());
        assertEquals("DIN", entity.getStandard());
        assertEquals("Class10", entity.getClazz());
        assertEquals("HexBolt", entity.getType());
        assertEquals("http://example.com/spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterGalvanizedSheetUpdate() throws Exception {
        // First, register the galvanized sheet
        registerGalvanizedSheet();

        // Cache the galvanized sheet entity
        cacheGalvanizedSheetEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "GALVANIZED_SHEET",
        "event": {
            "materialId": 2,
            "category": "GALVANIZED_SHEET",
            "name": "GalvanizedSheetNameUpdated",
            "description": "An updated high-strength sheet",
            "type": "CorrugatedSheet",
            "maxLength": "1200mm",
            "maxLengthUnit": "CM",
            "numberOfSheets": "20",
            "specificationFileUrl": "http://example.com/sheet_spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "GALVANIZED_SHEET")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(2L, "GALVANIZED_SHEET");
        assertFalse(savedEvents.isEmpty());

        UpdateGalvanizedSheetEvent updatedGalvanizedEvent = (UpdateGalvanizedSheetEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the UpdateGalvanizedSheetEvent after update
        assertEquals("GalvanizedSheetNameUpdated", updatedGalvanizedEvent.getName());
        assertEquals("GALVANIZED_SHEET", updatedGalvanizedEvent.getCategory());
        assertEquals("An updated high-strength sheet", updatedGalvanizedEvent.getDescription());
        assertEquals("CorrugatedSheet", updatedGalvanizedEvent.getType());
        assertEquals("1200mm", updatedGalvanizedEvent.getMaxLength());
        assertEquals("CM", updatedGalvanizedEvent.getMaxLengthUnit().name());
        assertEquals("20", updatedGalvanizedEvent.getNumberOfSheets());
        assertEquals("http://example.com/sheet_spec_updated.pdf", updatedGalvanizedEvent.getSpecificationFileUrl());

        // Verify the GalvanizedSheetEntity is updated in the repository
        Optional<GalvanisedSheetEntity> galvanizedSheetEntity = galvanisedSheetRepository.findById("2");
        assertTrue(galvanizedSheetEntity.isPresent());

        GalvanisedSheetEntity entity = galvanizedSheetEntity.get();

        // Verify all fields of the GalvanizedSheetEntity after update
        assertEquals("GalvanizedSheetNameUpdated", entity.getName());
        assertEquals("An updated high-strength sheet", entity.getDescription());
        assertEquals("CorrugatedSheet", entity.getType());
        assertEquals("1200mm", entity.getMaxLength());
        assertEquals("CM", entity.getMaxLengthUnit().name());
        assertEquals("20", entity.getNumberOfSheets());
        assertEquals("http://example.com/sheet_spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterInsulationUpdate() throws Exception {
        // First, register the insulation
        registerInsulation();

        // Cache the insulation entity
        cacheInsulationEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "INSULATION",
        "event": {
            "materialId": 3,
            "category": "INSULATION",
            "name": "InsulationNameUpdated",
            "description": "An updated thermal insulation",
            "type": "Fiberglass",
            "thickness": "50mm",
            "thicknessUnit": "CM",
            "specificationFileUrl": "http://example.com/insulation_spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "INSULATION")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(3L, "INSULATION");
        assertFalse(savedEvents.isEmpty());

        UpdateInsulationEvent updatedInsulationEvent = (UpdateInsulationEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the UpdateInsulationEvent after update
        assertEquals("InsulationNameUpdated", updatedInsulationEvent.getName());
        assertEquals("INSULATION", updatedInsulationEvent.getCategory());
        assertEquals("An updated thermal insulation", updatedInsulationEvent.getDescription());
        assertEquals("Fiberglass", updatedInsulationEvent.getType());
        assertEquals("50mm", updatedInsulationEvent.getThickness());
        assertEquals("CM", updatedInsulationEvent.getThicknessUnit().name());
        assertEquals("http://example.com/insulation_spec_updated.pdf", updatedInsulationEvent.getSpecificationFileUrl());

        // Verify the InsulationEntity is updated in the repository
        Optional<InsulationEntity> insulationEntity = insulationRepository.findById("3");
        assertTrue(insulationEntity.isPresent());

        InsulationEntity entity = insulationEntity.get();

        // Verify all fields of the InsulationEntity after update
        assertEquals("InsulationNameUpdated", entity.getName());
        assertEquals("An updated thermal insulation", entity.getDescription());
        assertEquals("Fiberglass", entity.getType());
        assertEquals("50mm", entity.getThickness());
        assertEquals("CM", entity.getThicknessUnit().name());
        assertEquals("http://example.com/insulation_spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterMetalUpdate() throws Exception {
        // First, register the metal
        registerMetal();

        // Cache the metal entity
        cacheMetalEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "METAL",
        "event": {
            "materialId": 4,
            "category": "METAL",
            "name": "MetalNameUpdated",
            "description": "An updated high-strength steel",
            "kind": "Aluminium",
            "totalWeight": "120kg",
            "totalWeightUnit": "KG",
            "specificationFileUrl": "http://example.com/metal_spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "METAL")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(4L, "METAL");
        assertFalse(savedEvents.isEmpty());

        UpdateMetalEvent updatedMetalEvent = (UpdateMetalEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the UpdateMetalEvent after update
        assertEquals("MetalNameUpdated", updatedMetalEvent.getName());
        assertEquals("METAL", updatedMetalEvent.getCategory());
        assertEquals("An updated high-strength steel", updatedMetalEvent.getDescription());
        assertEquals("Aluminium", updatedMetalEvent.getKind());
        assertEquals("120kg", updatedMetalEvent.getTotalWeight());
        assertEquals("KG", updatedMetalEvent.getTotalWeightUnit().name());
        assertEquals("http://example.com/metal_spec_updated.pdf", updatedMetalEvent.getSpecificationFileUrl());

        // Verify the MetalEntity is updated in the repository
        Optional<MetalEntity> metalEntity = metalRepository.findById("4");
        assertTrue(metalEntity.isPresent());

        MetalEntity entity = metalEntity.get();

        // Verify all fields of the MetalEntity after update
        assertEquals("MetalNameUpdated", entity.getName());
        assertEquals("An updated high-strength steel", entity.getDescription());
        assertEquals("Aluminium", entity.getKind());
        assertEquals("120kg", entity.getTotalWeight());
        assertEquals("KG", entity.getTotalWeightUnit().name());
        assertEquals("http://example.com/metal_spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterPanelUpdate() throws Exception {
        // First, register the panel
        registerPanel();

        // Cache the panel entity
        cachePanelEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "PANELS",
        "event": {
            "materialId": 5,
            "category": "PANELS",
            "name": "PanelNameUpdated",
            "description": "An updated high-quality panel",
            "color": "Blue",
            "length": "250cm",
            "lengthUnit": "CM",
            "width": "120cm",
            "widthUnit": "CM",
            "totalThickness": "6cm",
            "totalThicknessUnit": "CM",
            "frontSheetThickness": "2mm",
            "frontSheetThicknessUnit": "MM",
            "backSheetThickness": "2mm",
            "backSheetThicknessUnit": "MM",
            "specificationFileUrl": "http://example.com/panel_spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "PANELS")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(5L, "PANELS");
        assertFalse(savedEvents.isEmpty());

        UpdatePanelEvent updatedPanelEvent = (UpdatePanelEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the UpdatePanelEvent after update
        assertEquals("PanelNameUpdated", updatedPanelEvent.getName());
        assertEquals("PANELS", updatedPanelEvent.getCategory());
        assertEquals("An updated high-quality panel", updatedPanelEvent.getDescription());
        assertEquals("Blue", updatedPanelEvent.getColor());
        assertEquals("250cm", updatedPanelEvent.getLength());
        assertEquals("CM", updatedPanelEvent.getLengthUnit().name());
        assertEquals("120cm", updatedPanelEvent.getWidth());
        assertEquals("CM", updatedPanelEvent.getWidthUnit().name());
        assertEquals("6cm", updatedPanelEvent.getTotalThickness());
        assertEquals("CM", updatedPanelEvent.getTotalThicknessUnit().name());
        assertEquals("2mm", updatedPanelEvent.getFrontSheetThickness());
        assertEquals("MM", updatedPanelEvent.getFrontSheetThicknessUnit().name());
        assertEquals("2mm", updatedPanelEvent.getBackSheetThickness());
        assertEquals("MM", updatedPanelEvent.getBackSheetThicknessUnit().name());
        assertEquals("http://example.com/panel_spec_updated.pdf", updatedPanelEvent.getSpecificationFileUrl());

        // Verify the PanelEntity is updated in the repository
        Optional<PanelEntity> panelEntity = panelRepository.findById("5");
        assertTrue(panelEntity.isPresent());

        PanelEntity entity = panelEntity.get();

        // Verify all fields of the PanelEntity after update
        assertEquals("PanelNameUpdated", entity.getName());
        assertEquals("An updated high-quality panel", entity.getDescription());
        assertEquals("Blue", entity.getColor());
        assertEquals("250cm", entity.getLength());
        assertEquals("CM", entity.getLengthUnit().name());
        assertEquals("120cm", entity.getWidth());
        assertEquals("CM", entity.getWidthUnit().name());
        assertEquals("6cm", entity.getTotalThickness());
        assertEquals("CM", entity.getTotalThicknessUnit().name());
        assertEquals("2mm", entity.getFrontSheetThickness());
        assertEquals("MM", entity.getFrontSheetThicknessUnit().name());
        assertEquals("2mm", entity.getBackSheetThickness());
        assertEquals("MM", entity.getBackSheetThicknessUnit().name());
        assertEquals("http://example.com/panel_spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterRebarUpdate() throws Exception {
        // First, register the rebar
        registerRebar();

        // Cache the rebar entity
        cacheRebarEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "REBAR",
        "event": {
            "materialId": 6,
            "category": "REBAR",
            "name": "RebarNameUpdated",
            "description": "An updated high-strength rebar",
            "maxLength": "600cm",
            "maxLengthUnit": "CM",
            "specificationFileUrl": "http://example.com/rebar_spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "REBAR")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(6L, "REBAR");
        assertFalse(savedEvents.isEmpty());

        UpdateRebarEvent updatedRebarEvent = (UpdateRebarEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the UpdateRebarEvent after update
        assertEquals("RebarNameUpdated", updatedRebarEvent.getName());
        assertEquals("REBAR", updatedRebarEvent.getCategory());
        assertEquals("An updated high-strength rebar", updatedRebarEvent.getDescription());
        assertEquals("600cm", updatedRebarEvent.getMaxLength());
        assertEquals("CM", updatedRebarEvent.getMaxLengthUnit().name());
        assertEquals("http://example.com/rebar_spec_updated.pdf", updatedRebarEvent.getSpecificationFileUrl());

        // Verify the RebarEntity is updated in the repository
        Optional<RebarEntity> rebarEntity = rebarRepository.findById("6");
        assertTrue(rebarEntity.isPresent());

        RebarEntity entity = rebarEntity.get();

        // Verify all fields of the RebarEntity after update
        assertEquals("RebarNameUpdated", entity.getName());
        assertEquals("An updated high-strength rebar", entity.getDescription());
        assertEquals("600cm", entity.getMaxLength());
        assertEquals("CM", entity.getMaxLengthUnit().name());
        assertEquals("http://example.com/rebar_spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterSetUpdate() throws Exception {
        // First, register the Set
        registerSet();

        // Cache the Set entity
        cacheSetEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "SET",
        "event": {
            "materialId": 7,
            "category": "SET",
            "name": "SetNameUpdated",
            "description": "An updated high-quality set",
            "color": "Blue",
            "maxLength": "150cm",
            "maxLengthUnit": "CM",
            "specificationFileUrl": "http://example.com/set_spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "SET")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(7L, "SET");
        assertFalse(savedEvents.isEmpty());

        UpdateSetEvent updatedSetEvent = (UpdateSetEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the UpdateSetEvent after update
        assertEquals("SetNameUpdated", updatedSetEvent.getName());
        assertEquals("SET", updatedSetEvent.getCategory());
        assertEquals("An updated high-quality set", updatedSetEvent.getDescription());
        assertEquals("Blue", updatedSetEvent.getColor());
        assertEquals("150cm", updatedSetEvent.getMaxLength());
        assertEquals("CM", updatedSetEvent.getMaxLengthUnit().name());
        assertEquals("http://example.com/set_spec_updated.pdf", updatedSetEvent.getSpecificationFileUrl());

        // Verify the SetEntity is updated in the repository
        Optional<SetEntity> setEntity = setRepository.findById("7");
        assertTrue(setEntity.isPresent());

        SetEntity entity = setEntity.get();

        // Verify all fields of the SetEntity after update
        assertEquals("SetNameUpdated", entity.getName());
        assertEquals("An updated high-quality set", entity.getDescription());
        assertEquals("Blue", entity.getColor());
        assertEquals("150cm", entity.getMaxLength());
        assertEquals("CM", entity.getMaxLengthUnit().name());
        assertEquals("http://example.com/set_spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterUnspecifiedUpdate() throws Exception {
        // First, register the Unspecified entity
        registerUnspecified();

        // Cache the Unspecified entity
        cacheUnspecifiedEntity();

        // Arrange the update event with different fields
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "UNSPECIFIED",
        "event": {
            "materialId": 8,
            "category": "UNSPECIFIED",
            "name": "UnspecifiedNameUpdated",
            "description": "An updated unspecified material",
            "specificationFileUrl": "http://example.com/unspecified_spec_updated.pdf"
        }
    }
    """;

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        // Act: Send the update event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "UNSPECIFIED")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(8L, "UNSPECIFIED");
        assertFalse(savedEvents.isEmpty());

        UpdateUnspecifiedEvent updatedUnspecifiedEvent = (UpdateUnspecifiedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the UpdateUnspecifiedEvent after update
        assertEquals("UnspecifiedNameUpdated", updatedUnspecifiedEvent.getName());
        assertEquals("UNSPECIFIED", updatedUnspecifiedEvent.getCategory());
        assertEquals("An updated unspecified material", updatedUnspecifiedEvent.getDescription());
        assertEquals("http://example.com/unspecified_spec_updated.pdf", updatedUnspecifiedEvent.getSpecificationFileUrl());

        // Verify the UnspecifiedEntity is updated in the repository
        Optional<UnspecifiedEntity> unspecifiedEntity = unspecifiedRepository.findById("8");
        assertTrue(unspecifiedEntity.isPresent());

        UnspecifiedEntity entity = unspecifiedEntity.get();

        // Verify all fields of the UnspecifiedEntity after update
        assertEquals("UnspecifiedNameUpdated", entity.getName());
        assertEquals("An updated unspecified material", entity.getDescription());
        assertEquals("http://example.com/unspecified_spec_updated.pdf", entity.getSpecificationFileUrl());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterFastenerDeletion() throws Exception {
        // First, register the fastener
        registerFastener();

        // Cache the fastener entity
        cacheFastenerEntity();

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        String materialId = "1";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "FASTENERS",
        "event": {
            "materialId": 1,
            "category": "FASTENERS",
            "name": "FastenerName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "FASTENERS")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "FASTENERS");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("FastenerName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the FastenerEntity is deleted from the repository
        Optional<FastenerEntity> fastenerEntity = fastenerRepository.findById(materialId);
        assertFalse(fastenerEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterGalvanizedSheetDeletion() throws Exception {
        // First, register the galvanized sheet
        registerGalvanizedSheet();

        // Cache the galvanized sheet entity
        cacheGalvanizedSheetEntity();

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        String materialId = "2";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "GALVANIZED_SHEET",
        "event": {
            "materialId": 2,
            "category": "GALVANIZED_SHEET",
            "name": "GalvanizedSheetName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "GALVANIZED_SHEET")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "GALVANIZED_SHEET");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("GalvanizedSheetName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the GalvanizedSheetEntity is deleted from the repository
        Optional<GalvanisedSheetEntity> galvanizedSheetEntity = galvanisedSheetRepository.findById(materialId);
        assertFalse(galvanizedSheetEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterInsulationDeletion() throws Exception {
        // First, register the insulation
        registerInsulation();

        // Cache the insulation entity
        cacheInsulationEntity();

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        String materialId = "3";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "INSULATION",
        "event": {
            "materialId": 3,
            "category": "INSULATION",
            "name": "InsulationName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "INSULATION")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "INSULATION");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("InsulationName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the InsulationEntity is deleted from the repository
        Optional<InsulationEntity> insulationEntity = insulationRepository.findById(materialId);
        assertFalse(insulationEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterMetalDeletion() throws Exception {
        // First, register the metal
        registerMetal();

        // Cache the metal entity
        cacheMetalEntity();


        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        String materialId = "4";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "METAL",
        "event": {
            "materialId": 4,
            "category": "METAL",
            "name": "MetalName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "METAL")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "METAL");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("MetalName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the MetalEntity is deleted from the repository
        Optional<MetalEntity> metalEntity = metalRepository.findById(materialId);
        assertFalse(metalEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterPanelDeletion() throws Exception {
        // First, register the panel
        registerPanel();

        // Cache the panel entity
        cachePanelEntity();

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        String materialId = "5";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "PANELS",
        "event": {
            "materialId": 5,
            "category": "PANELS",
            "name": "PanelName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "PANELS")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "PANELS");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("PanelName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the PanelEntity is deleted from the repository
        Optional<PanelEntity> panelEntity = panelRepository.findById(materialId);
        assertFalse(panelEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterRebarDeletion() throws Exception {
        // First, register the rebar
        registerRebar();

        // Cache the rebar entity
        cacheRebarEntity();

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));


        String materialId = "6";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "REBAR",
        "event": {
            "materialId": 6,
            "category": "REBAR",
            "name": "RebarName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "REBAR")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "REBAR");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("RebarName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the RebarEntity is deleted from the repository
        Optional<RebarEntity> rebarEntity = rebarRepository.findById(materialId);
        assertFalse(rebarEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }


    @Test
    public void testCacheEvictionAfterSetDeletion() throws Exception {
        // First, register the set
        registerSet();

        // Cache the set entity
        cacheSetEntity();

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        String materialId = "7";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "SET",
        "event": {
            "materialId": 7,
            "category": "SET",
            "name": "SetName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "SET")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "SET");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("SetName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the SetEntity is deleted from the repository
        Optional<SetEntity> setEntity = setRepository.findById(materialId);
        assertFalse(setEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testCacheEvictionAfterUnspecifiedDeletion() throws Exception {
        // First, register the unspecified entity
        registerUnspecified();

        // Cache the unspecified entity
        cacheUnspecifiedEntity();

        // Verify cache is populated before the operation
        Cache cache = cacheManager.getCache("materials");
        assertNotNull(cache.get(cacheKey));

        String materialId = "8";
        // Arrange the delete event
        String deleteRequestBody = """
    {
        "eventType": "ItemDeleted",
        "materialType": "UNSPECIFIED",
        "event": {
            "materialId": 8,
            "category": "UNSPECIFIED",
            "name": "UnspecifiedName"
        }
    }
    """;

        // Act: Send the delete event
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "UNSPECIFIED")
                        .header("Event-Type", "ItemDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteRequestBody))
                .andExpect(status().isOk());

        // Assert: Verify the material event is saved in the repository
        List<MaterialEvent<? extends BaseMaterialEvent>> savedEvents = materialEventRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(materialId), "UNSPECIFIED");
        assertFalse(savedEvents.isEmpty());

        MaterialDeletedEvent deletedEvent = (MaterialDeletedEvent) savedEvents.get(savedEvents.size() - 1).getEvent();

        // Verify all fields of the MaterialDeletedEvent
        assertEquals("UnspecifiedName", deletedEvent.getName());
        assertEquals(Long.valueOf(materialId), deletedEvent.getMaterialId());

        // Verify the UnspecifiedEntity is deleted from the repository
        Optional<UnspecifiedEntity> unspecifiedEntity = unspecifiedRepository.findById(materialId);
        assertFalse(unspecifiedEntity.isPresent());

        // Verify cache eviction after the operation
        assertNull(cache.get(cacheKey));
    }

    @Test
    public void testExceptionForInvalidCategory() throws Exception {
        // Arrange the invalid category event
        String invalidCategoryRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "INVALID_CATEGORY",
        "event": {
            "materialId": 1,
            "category": "INVALID_CATEGORY",
            "name": "InvalidMaterial",
            "description": "Invalid description"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "INVALID_CATEGORY")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCategoryRequestBody))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(InvalidCategoryException.class, result.getResolvedException()));
    }

    @Test
    public void testExceptionForFastenerNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "FASTENERS",
        "event": {
            "materialId": 999, 
            "category": "FASTENERS",
            "name": "FastenerNameUpdated",
            "description": "An updated high-strength bolt"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "FASTENERS")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }

    @Test
    public void testExceptionForGalvanizedSheetNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "GALVANIZED_SHEET",
        "event": {
            "materialId": 999,
            "category": "GALVANIZED_SHEET",
            "name": "GalvanizedSheetUpdated",
            "description": "An updated galvanized sheet"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "GALVANIZED_SHEET")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }

    @Test
    public void testExceptionForInsulationNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "INSULATION",
        "event": {
            "materialId": 999, 
            "category": "INSULATION",
            "name": "InsulationUpdated",
            "description": "An updated insulation"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "INSULATION")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }

    @Test
    public void testExceptionForMetalNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "METAL",
        "event": {
            "materialId": 999,
            "category": "METAL",
            "name": "MetalUpdated",
            "description": "An updated metal"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "METAL")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }

    @Test
    public void testExceptionForPanelNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "PANELS",
        "event": {
            "materialId": 999,
            "category": "PANELS",
            "name": "PanelUpdated",
            "description": "An updated panel"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "PANELS")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }

    @Test
    public void testExceptionForRebarNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "REBAR",
        "event": {
            "materialId": 999, 
            "category": "REBAR",
            "name": "RebarUpdated",
            "description": "An updated rebar"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "REBAR")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }


    @Test
    public void testExceptionForSetNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "SET",
        "event": {
            "materialId": 999,
            "category": "SET",
            "name": "SetUpdated",
            "description": "An updated set"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "SET")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }

    @Test
    public void testExceptionForUnspecifiedNotFound() throws Exception {
        // Arrange the update event with a non-existent materialId
        String updateRequestBody = """
    {
        "eventType": "ItemUpdated",
        "materialType": "UNSPECIFIED",
        "event": {
            "materialId": 999,
            "category": "UNSPECIFIED",
            "name": "UnspecifiedUpdated",
            "description": "An updated unspecified material"
        }
    }
    """;

        // Act and Assert
        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "UNSPECIFIED")
                        .header("Event-Type", "ItemUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(InventoryItemNotFoundException.class, result.getResolvedException()));
    }

    private void cacheFastenerEntity() {
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setId("1");
        fastenerEntity.setName("FastenerName");
        fastenerEntity.setType("Bolt");
        fastenerEntity.setDiameter("5mm");
        fastenerEntity.setLength("20mm");
        fastenerEntity.setLengthUnit(LengthUnits.MM);
        fastenerEntity.setStandard("ISO");
        fastenerEntity.setClazz("Class8");
        fastenerEntity.setDescription("A standard bolt");
        fastenerEntity.setSpecificationFileUrl("http://example.com/spec.pdf");
        fastenerRepository.save(fastenerEntity);

        cacheKey = "FASTENERS_Fa";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, fastenerEntity);
        }
    }

    private void cacheGalvanizedSheetEntity() {
        GalvanisedSheetEntity sheetEntity = new GalvanisedSheetEntity();
        sheetEntity.setId("2");
        sheetEntity.setName("GalvanizedSheetName");
        sheetEntity.setType("SheetType");
        sheetEntity.setMaxLength("1000mm");
        sheetEntity.setMaxLengthUnit(LengthUnits.MM);
        sheetEntity.setNumberOfSheets("10");
        sheetEntity.setDescription("A standard sheet");
        sheetEntity.setSpecificationFileUrl("http://example.com/sheet_spec.pdf");
        galvanisedSheetRepository.save(sheetEntity);

        cacheKey = "GALVANIZED_SHEET_Ga";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, sheetEntity);
        }
    }

    private void cacheInsulationEntity() {
        InsulationEntity insulationEntity = new InsulationEntity();
        insulationEntity.setId("3");
        insulationEntity.setName("InsulationName");
        insulationEntity.setType("Foam");
        insulationEntity.setThickness("40mm");
        insulationEntity.setThicknessUnit(LengthUnits.MM);
        insulationEntity.setDescription("A standard insulation");
        insulationEntity.setSpecificationFileUrl("http://example.com/insulation_spec.pdf");
        insulationRepository.save(insulationEntity);

        cacheKey = "INSULATION_In";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, insulationEntity);
        }
    }


    private void cacheMetalEntity() {
        MetalEntity metalEntity = new MetalEntity();
        metalEntity.setId("4");
        metalEntity.setName("MetalName");
        metalEntity.setKind("Steel");
        metalEntity.setTotalWeight("100kg");
        metalEntity.setTotalWeightUnit(WeightUnits.KG);
        metalEntity.setDescription("A standard metal");
        metalEntity.setSpecificationFileUrl("http://example.com/metal_spec.pdf");
        metalRepository.save(metalEntity);

        cacheKey = "METAL_Me";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, metalEntity);
        }
    }

    private void cachePanelEntity() {
        PanelEntity panelEntity = new PanelEntity();
        panelEntity.setId("5");
        panelEntity.setName("PanelName");
        panelEntity.setColor("White");
        panelEntity.setLength("200cm");
        panelEntity.setLengthUnit(LengthUnits.CM);
        panelEntity.setWidth("100cm");
        panelEntity.setWidthUnit(LengthUnits.CM);
        panelEntity.setTotalThickness("5cm");
        panelEntity.setTotalThicknessUnit(LengthUnits.CM);
        panelEntity.setFrontSheetThickness("1mm");
        panelEntity.setFrontSheetThicknessUnit(LengthUnits.MM);
        panelEntity.setBackSheetThickness("1mm");
        panelEntity.setBackSheetThicknessUnit(LengthUnits.MM);
        panelEntity.setDescription("A standard panel");
        panelEntity.setSpecificationFileUrl("http://example.com/panel_spec.pdf");
        panelRepository.save(panelEntity);

        cacheKey = "PANELS_Pa";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, panelEntity);
        }
    }


    private void cacheRebarEntity() {
        RebarEntity rebarEntity = new RebarEntity();
        rebarEntity.setId("6");
        rebarEntity.setName("RebarName");
        rebarEntity.setMaxLength("500cm");
        rebarEntity.setMaxLengthUnit(LengthUnits.CM);
        rebarEntity.setDescription("A standard rebar");
        rebarEntity.setSpecificationFileUrl("http://example.com/rebar_spec.pdf");
        rebarRepository.save(rebarEntity);

        cacheKey = "REBAR_Re";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, rebarEntity);
        }
    }


    private void cacheSetEntity() {
        SetEntity setEntity = new SetEntity();
        setEntity.setId("7");
        setEntity.setName("SetName");
        setEntity.setColor("Red");
        setEntity.setMaxLength("100cm");
        setEntity.setMaxLengthUnit(LengthUnits.CM);
        setEntity.setDescription("A standard set");
        setEntity.setSpecificationFileUrl("http://example.com/set_spec.pdf");
        setRepository.save(setEntity);

        cacheKey = "SET_Se";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, setEntity);
        }
    }

    private void cacheUnspecifiedEntity() {
        UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
        unspecifiedEntity.setId("8");
        unspecifiedEntity.setName("UnspecifiedName");
        unspecifiedEntity.setDescription("An unspecified material");
        unspecifiedEntity.setSpecificationFileUrl("http://example.com/unspecified_spec.pdf");
        unspecifiedRepository.save(unspecifiedEntity);

        cacheKey = "UNSPECIFIED_Un";
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.put(cacheKey, unspecifiedEntity);
        }
    }


    private void registerFastener() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "FASTENERS",
        "event": {
            "materialId": 1,
            "category": "FASTENERS",
            "name": "FastenerName",
            "description": "A standard bolt",
            "diameter": "5mm",
            "length": "20mm",
            "lengthUnit": "MM",
            "standard": "ISO",
            "clazz": "Class8",
            "type": "Bolt",
            "specificationFileUrl": "http://example.com/spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "FASTENERS")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }

    private void registerGalvanizedSheet() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "GALVANIZED_SHEET",
        "event": {
            "materialId": 2,
            "category": "GALVANIZED_SHEET",
            "name": "GalvanizedSheetName",
            "description": "A standard sheet",
            "type": "SheetType",
            "maxLength": "1000mm",
            "maxLengthUnit": "MM",
            "numberOfSheets": "10",
            "specificationFileUrl": "http://example.com/sheet_spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "GALVANIZED_SHEET")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }

    private void registerInsulation() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "INSULATION",
        "event": {
            "materialId": 3,
            "category": "INSULATION",
            "name": "InsulationName",
            "description": "A standard insulation",
            "type": "Foam",
            "thickness": "40mm",
            "thicknessUnit": "MM",
            "specificationFileUrl": "http://example.com/insulation_spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "INSULATION")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }

    private void registerMetal() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "METAL",
        "event": {
            "materialId": 4,
            "category": "METAL",
            "name": "MetalName",
            "description": "A standard metal",
            "kind": "Steel",
            "totalWeight": "100kg",
            "totalWeightUnit": "KG",
            "specificationFileUrl": "http://example.com/metal_spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "METAL")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }

    private void registerPanel() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "PANELS",
        "event": {
            "materialId": 5,
            "category": "PANELS",
            "name": "PanelName",
            "description": "A standard panel",
            "color": "White",
            "length": "200cm",
            "lengthUnit": "CM",
            "width": "100cm",
            "widthUnit": "CM",
            "totalThickness": "5cm",
            "totalThicknessUnit": "CM",
            "frontSheetThickness": "1mm",
            "frontSheetThicknessUnit": "MM",
            "backSheetThickness": "1mm",
            "backSheetThicknessUnit": "MM",
            "specificationFileUrl": "http://example.com/panel_spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "PANELS")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }


    private void registerRebar() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "REBAR",
        "event": {
            "materialId": 6,
            "category": "REBAR",
            "name": "RebarName",
            "description": "A standard rebar",
            "maxLength": "500cm",
            "maxLengthUnit": "CM",
            "specificationFileUrl": "http://example.com/rebar_spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "REBAR")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }


    private void registerSet() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "SET",
        "event": {
            "materialId": 7,
            "category": "SET",
            "name": "SetName",
            "description": "A standard set",
            "color": "Red",
            "maxLength": "100cm",
            "maxLengthUnit": "CM",
            "specificationFileUrl": "http://example.com/set_spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "SET")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }


    private void registerUnspecified() throws Exception {
        String registerRequestBody = """
    {
        "eventType": "ItemRegistered",
        "materialType": "UNSPECIFIED",
        "event": {
            "materialId": 8,
            "category": "UNSPECIFIED",
            "name": "UnspecifiedName",
            "description": "An unspecified material",
            "specificationFileUrl": "http://example.com/unspecified_spec.pdf"
        }
    }
    """;

        mockMvc.perform(post("/inventory/materials/events")
                        .header("Material-Type", "UNSPECIFIED")
                        .header("Event-Type", "ItemRegistered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isOk());
    }

}
