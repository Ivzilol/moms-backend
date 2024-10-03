package bg.mck.controller;

import bg.mck.dto.MaterialDTO;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.MaterialType;
import bg.mck.enums.WeightUnits;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.material.*;
import bg.mck.service.MaterialEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MaterialQueryControllerTest {

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
    private CacheManager cacheManager;



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
    }


    @Test
    public void testGetMaterialByCategoryAndId_Fasteners_Success() throws Exception {
        // Register a fastener entity
        registerFastener();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/FASTENERS/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("FastenerName"))
                .andExpect(jsonPath("$.type").value("Bolt"))
                .andExpect(jsonPath("$.diameter").value("5mm"))
                .andExpect(jsonPath("$.length").value("20mm"))
                .andExpect(jsonPath("$.lengthUnit").value("MM"))
                .andExpect(jsonPath("$.standard").value("ISO"))
                .andExpect(jsonPath("$.clazz").value("Class8"))
                .andExpect(jsonPath("$.description").value("A standard bolt"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));

    }

    @Test
    public void testGetMaterialByCategoryAndId_Fasteners_FromCache_Success() throws Exception {
        // Manually cache the fastener entity
        cacheFastener();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/FASTENERS/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedFastenerName"))
                .andExpect(jsonPath("$.type").value("CachedBolt"))
                .andExpect(jsonPath("$.diameter").value("6mm"))
                .andExpect(jsonPath("$.length").value("30mm"))
                .andExpect(jsonPath("$.lengthUnit").value("CM"))
                .andExpect(jsonPath("$.standard").value("DIN"))
                .andExpect(jsonPath("$.clazz").value("Class10"))
                .andExpect(jsonPath("$.description").value("A cached high-strength bolt"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));

    }

    @Test
    public void testGetMaterialByCategoryAndId_Fasteners_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/FASTENERS/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetMaterialByCategoryAndId_GalvanizedSheet_Success() throws Exception {
        // Register a galvanized sheet entity
        registerGalvanizedSheet();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/GALVANIZED_SHEET/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("GalvanizedSheetName"))
                .andExpect(jsonPath("$.type").value("Flat"))
                .andExpect(jsonPath("$.maxLength").value("2500mm"))
                .andExpect(jsonPath("$.maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$.numberOfSheets").value("10"))
                .andExpect(jsonPath("$.description").value("A standard galvanized sheet"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));
    }


    @Test
    public void testGetMaterialByCategoryAndId_GalvanizedSheet_FromCache_Success() throws Exception {
        // Manually cache the galvanized sheet entity
        cacheGalvanizedSheet();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/GALVANIZED_SHEET/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedGalvanizedSheetName"))
                .andExpect(jsonPath("$.type").value("Corrugated"))
                .andExpect(jsonPath("$.maxLength").value("3000mm"))
                .andExpect(jsonPath("$.maxLengthUnit").value("CM"))
                .andExpect(jsonPath("$.numberOfSheets").value("20"))
                .andExpect(jsonPath("$.description").value("A cached high-quality galvanized sheet"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Galvanized_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/GALVANIZED_SHEET/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetMaterialByCategoryAndId_Insulation_Success_Cached() throws Exception {
        // Cache the insulation entity
        cacheInsulationEntity();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/INSULATION/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedInsulationName"))
                .andExpect(jsonPath("$.type").value("Foam"))
                .andExpect(jsonPath("$.thickness").value("15mm"))
                .andExpect(jsonPath("$.thicknessUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("Cached insulation entity"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Insulation_Success_Reconstructed() throws Exception {
        // Register a insulation entity
        registerInsulation();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/INSULATION/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("InsulationName"))
                .andExpect(jsonPath("$.type").value("Foam"))
                .andExpect(jsonPath("$.thickness").value("10mm"))
                .andExpect(jsonPath("$.thicknessUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("High-quality foam insulation"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Insulation_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/INSULATION/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetMaterialByCategoryAndId_Metal_Success_Cached() throws Exception {
        // Cache the metal entity
        cacheMetalEntity();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/METAL/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedMetalName"))
                .andExpect(jsonPath("$.totalWeight").value("10000kg"))
                .andExpect(jsonPath("$.totalWeightUnit").value("T"))
                .andExpect(jsonPath("$.kind").value("Steel"))
                .andExpect(jsonPath("$.description").value("Cached steel metal"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Metal_Success_Reconstructed() throws Exception {
        // Register a metal entity
        registerMetal();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/METAL/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("MetalName"))
                .andExpect(jsonPath("$.totalWeight").value("5000kg"))
                .andExpect(jsonPath("$.totalWeightUnit").value("KG"))
                .andExpect(jsonPath("$.kind").value("Aluminum"))
                .andExpect(jsonPath("$.description").value("High-quality aluminum metal"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Metal_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/METAL/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetMaterialByCategoryAndId_Panel_Success_Cached() throws Exception {
        // Cache the panel entity
        cachePanelEntity();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/PANELS/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedPanelName"))
                .andExpect(jsonPath("$.type").value("WallPanel"))
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.length").value("4000mm"))
                .andExpect(jsonPath("$.lengthUnit").value("MM"))
                .andExpect(jsonPath("$.width").value("1200mm"))
                .andExpect(jsonPath("$.widthUnit").value("MM"))
                .andExpect(jsonPath("$.totalThickness").value("60mm"))
                .andExpect(jsonPath("$.totalThicknessUnit").value("MM"))
                .andExpect(jsonPath("$.frontSheetThickness").value("0.6mm"))
                .andExpect(jsonPath("$.frontSheetThicknessUnit").value("MM"))
                .andExpect(jsonPath("$.backSheetThickness").value("0.5mm"))
                .andExpect(jsonPath("$.backSheetThicknessUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("Cached high-quality wall panel"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Panel_Success_Reconstructed() throws Exception {
        // Register a panel entity
        registerPanel();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/PANELS/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("PanelName"))
                .andExpect(jsonPath("$.type").value("RoofPanel"))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.length").value("3000mm"))
                .andExpect(jsonPath("$.lengthUnit").value("MM"))
                .andExpect(jsonPath("$.width").value("1000mm"))
                .andExpect(jsonPath("$.widthUnit").value("MM"))
                .andExpect(jsonPath("$.totalThickness").value("50mm"))
                .andExpect(jsonPath("$.totalThicknessUnit").value("MM"))
                .andExpect(jsonPath("$.frontSheetThickness").value("0.5mm"))
                .andExpect(jsonPath("$.frontSheetThicknessUnit").value("MM"))
                .andExpect(jsonPath("$.backSheetThickness").value("0.4mm"))
                .andExpect(jsonPath("$.backSheetThicknessUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("High-quality roof panel"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Panel_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/PANELS/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMaterialByCategoryAndId_Rebar_Success_Cached() throws Exception {
        // Cache the rebar entity
        cacheRebarEntity();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/REBAR/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedRebarName"))
                .andExpect(jsonPath("$.maxLength").value("15000mm"))
                .andExpect(jsonPath("$.maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("Cached high-strength steel rebar"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Rebar_Success_Reconstructed() throws Exception {
        // Register a rebar entity
        registerRebar();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/REBAR/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("RebarName"))
                .andExpect(jsonPath("$.maxLength").value("12000mm"))
                .andExpect(jsonPath("$.maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("High-strength steel rebar"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Rebar_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/REBAR/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetMaterialByCategoryAndId_Set_Success_Cached() throws Exception {
        // Cache the set entity
        cacheSetEntity();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/SET/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedSetName"))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.maxLength").value("5500mm"))
                .andExpect(jsonPath("$.maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("Cached high-quality set"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Set_Success_Reconstructed() throws Exception {
        // Register a set entity
        registerSet();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/SET/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("SetName"))
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.maxLength").value("5000mm"))
                .andExpect(jsonPath("$.maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$.description").value("High-quality set"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Set_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/SET/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetMaterialByCategoryAndId_Unspecified_Success_Cached() throws Exception {
        // Cache the unspecified entity
        cacheUnspecifiedEntity();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/UNSPECIFIED/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("CachedUnspecifiedName"))
                .andExpect(jsonPath("$.description").value("Cached general unspecified material"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec_cached.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Unspecified_Success_Reconstructed() throws Exception {
        // Register an unspecified entity
        registerUnspecified();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/UNSPECIFIED/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("UnspecifiedName"))
                .andExpect(jsonPath("$.description").value("General unspecified material"))
                .andExpect(jsonPath("$.specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Unspecified_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/UNSPECIFIED/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetMaterialByCategoryAndId_InvalidCategory() throws Exception {

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/INVALID_CATEGORY/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllMaterialsByCategory_Fasteners_Success() throws Exception {
        // Register multiple fasteners
        registerFastener();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/FASTENERS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("FastenerName"))
                .andExpect(jsonPath("$[0].description").value("A standard bolt"))
                .andExpect(jsonPath("$[0].diameter").value("5mm"))
                .andExpect(jsonPath("$[0].length").value("20mm"))
                .andExpect(jsonPath("$[0].lengthUnit").value("MM"))
                .andExpect(jsonPath("$[0].standard").value("ISO"))
                .andExpect(jsonPath("$[0].clazz").value("Class8"))
                .andExpect(jsonPath("$[0].type").value("Bolt"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }


    @Test
    public void testGetAllMaterialsByCategory_GalvanizedSheet_Success() throws Exception {
        // Register a galvanized sheet
        registerGalvanizedSheet();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/GALVANIZED_SHEET")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("GalvanizedSheetName"))
                .andExpect(jsonPath("$[0].type").value("Flat"))
                .andExpect(jsonPath("$[0].maxLength").value("2500mm"))
                .andExpect(jsonPath("$[0].maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$[0].numberOfSheets").value("10"))
                .andExpect(jsonPath("$[0].description").value("A standard galvanized sheet"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetAllMaterialsByCategory_Insulation_Success() throws Exception {
        // Register insulation materials
        registerInsulation();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/INSULATION")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("InsulationName"))
                .andExpect(jsonPath("$[0].type").value("Foam"))
                .andExpect(jsonPath("$[0].thickness").value("10mm"))
                .andExpect(jsonPath("$[0].thicknessUnit").value("MM"))
                .andExpect(jsonPath("$[0].description").value("High-quality foam insulation"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetAllMaterialsByCategory_Metal_Success() throws Exception {
        // Register a metal entity
        registerMetal();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/METAL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("MetalName"))
                .andExpect(jsonPath("$[0].totalWeight").value("5000kg"))
                .andExpect(jsonPath("$[0].totalWeightUnit").value("KG"))
                .andExpect(jsonPath("$[0].kind").value("Aluminum"))
                .andExpect(jsonPath("$[0].description").value("High-quality aluminum metal"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetAllMaterialsByCategory_Panel_Success() throws Exception {
        // Register a panel entity
        registerPanel();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/PANELS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("PanelName"))
                .andExpect(jsonPath("$[0].type").value("RoofPanel"))
                .andExpect(jsonPath("$[0].color").value("Red"))
                .andExpect(jsonPath("$[0].length").value("3000mm"))
                .andExpect(jsonPath("$[0].lengthUnit").value("MM"))
                .andExpect(jsonPath("$[0].width").value("1000mm"))
                .andExpect(jsonPath("$[0].widthUnit").value("MM"))
                .andExpect(jsonPath("$[0].totalThickness").value("50mm"))
                .andExpect(jsonPath("$[0].totalThicknessUnit").value("MM"))
                .andExpect(jsonPath("$[0].frontSheetThickness").value("0.5mm"))
                .andExpect(jsonPath("$[0].frontSheetThicknessUnit").value("MM"))
                .andExpect(jsonPath("$[0].backSheetThickness").value("0.4mm"))
                .andExpect(jsonPath("$[0].backSheetThicknessUnit").value("MM"))
                .andExpect(jsonPath("$[0].description").value("High-quality roof panel"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetAllMaterialsByCategory_Rebar_Success() throws Exception {
        // Register a rebar entity
        registerRebar();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/REBAR")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("RebarName"))
                .andExpect(jsonPath("$[0].maxLength").value("12000mm"))
                .andExpect(jsonPath("$[0].maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$[0].description").value("High-strength steel rebar"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetAllMaterialsByCategory_Set_Success() throws Exception {
        // Register a set entity
        registerSet();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/SET")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("SetName"))
                .andExpect(jsonPath("$[0].color").value("Blue"))
                .andExpect(jsonPath("$[0].maxLength").value("5000mm"))
                .andExpect(jsonPath("$[0].maxLengthUnit").value("MM"))
                .andExpect(jsonPath("$[0].description").value("High-quality set"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetAllMaterialsByCategory_Unspecified_Success() throws Exception {
        // Register an unspecified entity
        registerUnspecified();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/UNSPECIFIED")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("UnspecifiedName"))
                .andExpect(jsonPath("$[0].description").value("General unspecified material"))
                .andExpect(jsonPath("$[0].specificationFileUrl").value("http://example.com/spec.pdf"));
    }

    @Test
    public void testGetAllMaterialsByCategory_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/FASTENERS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllMaterialsByCategory_InvalidCategory() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/INVALID_CATEGORY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testGetMaterialByPartOfName_Fasteners_Success() throws Exception {
        // Register a fastener entity
        Cache cache = cacheManager.getCache("materials");
        cache.evict("FASTENERS_Fastener");
        assertNull(cache.get("FASTENERS_Fastener"));

        registerFastener();


        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "FASTENERS")
                        .param("materialName", "Fastener"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("FastenerName"))
                .andExpect(jsonPath("$[0].type").value("Bolt"));

        // Check if the result is cached
        assertNotNull(cache.get("FASTENERS_Fastener"));
    }



    @Test
    public void testGetMaterialByPartOfName_GalvanizedSheet_Success() throws Exception {
        // Prepare cache key and ensure it's evicted before the test
        Cache cache = cacheManager.getCache("materials");
        cache.evict("GALVANIZED_SHEET_Galvanized");
        assertNull(cache.get("GALVANIZED_SHEET_Galvanized"));

        // Register a galvanized sheet entity
        registerGalvanizedSheet();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "GALVANIZED_SHEET")
                        .param("materialName", "Galvanized"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("GalvanizedSheetName"))
                .andExpect(jsonPath("$[0].type").value("Flat"));

        // Check if the result is cached
        assertNotNull(cache.get("GALVANIZED_SHEET_Galvanized"));
    }

    @Test
    public void testGetMaterialByPartOfName_Insulation_Success() throws Exception {
        // Prepare cache key and ensure it's evicted before the test
        Cache cache = cacheManager.getCache("materials");
        cache.evict("INSULATION_Insulation");
        assertNull(cache.get("INSULATION_Insulation"));

        // Register an insulation entity
        registerInsulation();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "INSULATION")
                        .param("materialName", "Insulation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("InsulationName"))
                .andExpect(jsonPath("$[0].type").value("Foam"));

        // Check if the result is cached
        assertNotNull(cache.get("INSULATION_Insulation"));
    }

    @Test
    public void testGetMaterialByPartOfName_Metal_Success() throws Exception {
        // Prepare cache key and ensure it's evicted before the test
        Cache cache = cacheManager.getCache("materials");
        cache.evict("METAL_Metal");
        assertNull(cache.get("METAL_Metal"));

        // Register a metal entity
        registerMetal();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "METAL")
                        .param("materialName", "Metal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("MetalName"))
                .andExpect(jsonPath("$[0].kind").value("Aluminum"));

        // Check if the result is cached
        assertNotNull(cache.get("METAL_Metal"));
    }

    @Test
    public void testGetMaterialByPartOfName_Panel_Success() throws Exception {
        // Prepare cache key and ensure it's evicted before the test
        Cache cache = cacheManager.getCache("materials");
        cache.evict("PANELS_Panel");
        assertNull(cache.get("PANELS_Panel"));

        // Register a panel entity
        registerPanel();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "PANELS")
                        .param("materialName", "Panel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("PanelName"))
                .andExpect(jsonPath("$[0].color").value("Red"));

        // Check if the result is cached
        assertNotNull(cache.get("PANELS_Panel"));
    }

    @Test
    public void testGetMaterialByPartOfName_Rebar_Success() throws Exception {
        // Prepare cache key and ensure it's evicted before the test
        Cache cache = cacheManager.getCache("materials");
        cache.evict("REBAR_Rebar");
        assertNull(cache.get("REBAR_Rebar"));

        // Register a rebar entity
        registerRebar();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "REBAR")
                        .param("materialName", "Rebar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("RebarName"))
                .andExpect(jsonPath("$[0].maxLength").value("12000mm"));

        // Check if the result is cached
        assertNotNull(cache.get("REBAR_Rebar"));
    }

    @Test
    public void testGetMaterialByPartOfName_Set_Success() throws Exception {
        // Prepare cache key and ensure it's evicted before the test
        Cache cache = cacheManager.getCache("materials");
        cache.evict("SET_Set");
        assertNull(cache.get("SET_Set"));

        // Register a set entity
        registerSet();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "SET")
                        .param("materialName", "Set"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("SetName"))
                .andExpect(jsonPath("$[0].color").value("Blue"));

        // Check if the result is cached
        assertNotNull(cache.get("SET_Set"));
    }

    @Test
    public void testGetMaterialByPartOfName_Unspecified_Success() throws Exception {
        // Prepare cache key and ensure it's evicted before the test
        Cache cache = cacheManager.getCache("materials");
        cache.evict("UNSPECIFIED_Unspecified");
        assertNull(cache.get("UNSPECIFIED_Unspecified"));

        // Register an unspecified entity
        registerUnspecified();

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "UNSPECIFIED")
                        .param("materialName", "Unspecified"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("UnspecifiedName"))
                .andExpect(jsonPath("$[0].description").value("General unspecified material"));

        // Check if the result is cached
        assertNotNull(cache.get("UNSPECIFIED_Unspecified"));
    }

    @Test
    public void testGetMaterialByPartOfName_InvalidCategory_ThrowsInvalidCategoryException() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", "INVALID_CATEGORY")
                        .param("materialName", "SomeName"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(InvalidCategoryException.class, result.getResolvedException()));
    }

    @Test
    public void testGetMaterialByPartOfName_ValidCategoryButInvalidName_ReturnsEmptyList() throws Exception {
        // Arrange
        String validCategory = "FASTENERS"; // Assuming FASTENERS is a valid category
        String invalidName = "NonExistentName";

        // Prepare cache key and ensure it's evicted before the test
        String cacheKey = validCategory + "_" + invalidName;
        Cache cache = cacheManager.getCache("materials");
        if (cache != null) {
            cache.evict(cacheKey);
        }
        assertNull(cache.get(cacheKey));

        // Act & Assert
        mockMvc.perform(get("/v1/user/inventory/query/materials/search")
                        .param("category", validCategory)
                        .param("materialName", invalidName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        // Verify the cache is now populated
        assertNotNull(cache.get(cacheKey));
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

    private void cacheFastener() {
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setId("1");
        fastenerEntity.setName("CachedFastenerName");
        fastenerEntity.setType("CachedBolt");
        fastenerEntity.setDiameter("6mm");
        fastenerEntity.setLength("30mm");
        fastenerEntity.setLengthUnit(LengthUnits.CM);
        fastenerEntity.setStandard("DIN");
        fastenerEntity.setClazz("Class10");
        fastenerEntity.setDescription("A cached high-strength bolt");
        fastenerEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }

    private void registerGalvanizedSheet() throws Exception {
        String registerRequestBody = """
                {
                    "eventType": "ItemRegistered",
                    "materialType": "GALVANIZED_SHEET",
                    "event": {
                        "materialId": 1,
                        "category": "GALVANIZED_SHEET",
                        "name": "GalvanizedSheetName",
                        "type": "Flat",
                        "maxLength": "2500mm",
                        "maxLengthUnit": "MM",
                        "numberOfSheets": "10",
                        "description": "A standard galvanized sheet",
                        "specificationFileUrl": "http://example.com/spec.pdf"
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

    private void cacheGalvanizedSheet() {
        GalvanisedSheetEntity galvanizedSheetEntity = new GalvanisedSheetEntity();
        galvanizedSheetEntity.setId("1");
        galvanizedSheetEntity.setName("CachedGalvanizedSheetName");
        galvanizedSheetEntity.setType("Corrugated");
        galvanizedSheetEntity.setMaxLength("3000mm");
        galvanizedSheetEntity.setMaxLengthUnit(LengthUnits.CM);
        galvanizedSheetEntity.setNumberOfSheets("20");
        galvanizedSheetEntity.setDescription("A cached high-quality galvanized sheet");
        galvanizedSheetEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }


    private void registerInsulation() throws Exception {
        String registerRequestBody = """
                {
                    "eventType": "ItemRegistered",
                    "materialType": "INSULATION",
                    "event": {
                        "materialId": 1,
                        "category": "INSULATION",
                        "name": "InsulationName",
                        "type": "Foam",
                        "thickness": "10mm",
                        "thicknessUnit": "MM",
                        "description": "High-quality foam insulation",
                        "specificationFileUrl": "http://example.com/spec.pdf"
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

    private void cacheInsulationEntity() {
        InsulationEntity insulationEntity = new InsulationEntity();
        insulationEntity.setId("1");
        insulationEntity.setName("CachedInsulationName");
        insulationEntity.setType("Foam");
        insulationEntity.setThickness("15mm");
        insulationEntity.setThicknessUnit(LengthUnits.MM);
        insulationEntity.setDescription("Cached insulation entity");
        insulationEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }


    private void registerMetal() throws Exception {
        String registerRequestBody = """
        {
            "eventType": "ItemRegistered",
            "materialType": "METAL",
            "event": {
                "materialId": 1,
                "category": "METAL",
                "name": "MetalName",
                "totalWeight": "5000kg",
                "totalWeightUnit": "KG",
                "kind": "Aluminum",
                "description": "High-quality aluminum metal",
                "specificationFileUrl": "http://example.com/spec.pdf"
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

    private void cacheMetalEntity() {
        MetalEntity metalEntity = new MetalEntity();
        metalEntity.setId("1");
        metalEntity.setName("CachedMetalName");
        metalEntity.setTotalWeight("10000kg");
        metalEntity.setTotalWeightUnit(WeightUnits.T);
        metalEntity.setKind("Steel");
        metalEntity.setDescription("Cached steel metal");
        metalEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }


    private void registerPanel() throws Exception {
        String registerRequestBody = """
        {
            "eventType": "ItemRegistered",
            "materialType": "PANELS",
            "event": {
                "materialId": 1,
                "category": "PANELS",
                "name": "PanelName",
                "type": "RoofPanel",
                "color": "Red",
                "length": "3000mm",
                "lengthUnit": "MM",
                "width": "1000mm",
                "widthUnit": "MM",
                "totalThickness": "50mm",
                "totalThicknessUnit": "MM",
                "frontSheetThickness": "0.5mm",
                "frontSheetThicknessUnit": "MM",
                "backSheetThickness": "0.4mm",
                "backSheetThicknessUnit": "MM",
                "description": "High-quality roof panel",
                "specificationFileUrl": "http://example.com/spec.pdf"
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

    private void cachePanelEntity() {
        PanelEntity panelEntity = new PanelEntity();
        panelEntity.setId("1");
        panelEntity.setName("CachedPanelName");
        panelEntity.setType("WallPanel");
        panelEntity.setColor("Blue");
        panelEntity.setLength("4000mm");
        panelEntity.setLengthUnit(LengthUnits.MM);
        panelEntity.setWidth("1200mm");
        panelEntity.setWidthUnit(LengthUnits.MM);
        panelEntity.setTotalThickness("60mm");
        panelEntity.setTotalThicknessUnit(LengthUnits.MM);
        panelEntity.setFrontSheetThickness("0.6mm");
        panelEntity.setFrontSheetThicknessUnit(LengthUnits.MM);
        panelEntity.setBackSheetThickness("0.5mm");
        panelEntity.setBackSheetThicknessUnit(LengthUnits.MM);
        panelEntity.setDescription("Cached high-quality wall panel");
        panelEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }

    private void registerRebar() throws Exception {
        String registerRequestBody = """
        {
            "eventType": "ItemRegistered",
            "materialType": "REBAR",
            "event": {
                "materialId": 1,
                "category": "REBAR",
                "name": "RebarName",
                "maxLength": "12000mm",
                "maxLengthUnit": "MM",
                "description": "High-strength steel rebar",
                "specificationFileUrl": "http://example.com/spec.pdf"
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

    private void cacheRebarEntity() {
        RebarEntity rebarEntity = new RebarEntity();
        rebarEntity.setId("1");
        rebarEntity.setName("CachedRebarName");
        rebarEntity.setMaxLength("15000mm");
        rebarEntity.setMaxLengthUnit(LengthUnits.MM);
        rebarEntity.setDescription("Cached high-strength steel rebar");
        rebarEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }

    private void registerSet() throws Exception {
        String registerRequestBody = """
        {
            "eventType": "ItemRegistered",
            "materialType": "SET",
            "event": {
                "materialId": 1,
                "category": "SET",
                "name": "SetName",
                "color": "Blue",
                "maxLength": "5000mm",
                "maxLengthUnit": "MM",
                "description": "High-quality set",
                "specificationFileUrl": "http://example.com/spec.pdf"
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

    private void cacheSetEntity() {
        SetEntity setEntity = new SetEntity();
        setEntity.setId("1");
        setEntity.setName("CachedSetName");
        setEntity.setColor("Red");
        setEntity.setMaxLength("5500mm");
        setEntity.setMaxLengthUnit(LengthUnits.MM);
        setEntity.setDescription("Cached high-quality set");
        setEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }

    private void registerUnspecified() throws Exception {
        String registerRequestBody = """
        {
            "eventType": "ItemRegistered",
            "materialType": "UNSPECIFIED",
            "event": {
                "materialId": 1,
                "category": "UNSPECIFIED",
                "name": "UnspecifiedName",
                "description": "General unspecified material",
                "specificationFileUrl": "http://example.com/spec.pdf"
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

    private void cacheUnspecifiedEntity() {
        UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
        unspecifiedEntity.setId("1");
        unspecifiedEntity.setName("CachedUnspecifiedName");
        unspecifiedEntity.setDescription("Cached general unspecified material");
        unspecifiedEntity.setSpecificationFileUrl("http://example.com/spec_cached.pdf");

    }
}


