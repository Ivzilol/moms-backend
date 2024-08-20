package bg.mck.controller;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.CreateMaterialDTO;
import bg.mck.dto.CreateOrderMaterialsDto;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.MaterialType;
import bg.mck.enums.WeightUnits;
import bg.mck.events.material.*;
import bg.mck.repository.*;
import bg.mck.service.MaterialRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MaterialRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MaterialRegisterService materialRegisterService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FastenerRepository fastenerRepository;

    @Autowired
    private GalvanisedSheetRepository galvanisedSheetRepository;

    @Autowired
    private InsulationRepository insulationRepository;

    @Autowired
    private MetalRepository metalRepository;

    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private RebarRepository rebarRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private UnspecifiedRepository unspecifiedRepository;

    @MockBean
    private InventoryQueryServiceClient inventoryQueryServiceClient;

    @BeforeEach
    void setUp() {
        fastenerRepository.deleteAll();
        galvanisedSheetRepository.deleteAll();
        insulationRepository.deleteAll();
        metalRepository.deleteAll();
        panelRepository.deleteAll();
        rebarRepository.deleteAll();
        setRepository.deleteAll();
        unspecifiedRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        fastenerRepository.deleteAll();
        galvanisedSheetRepository.deleteAll();
        insulationRepository.deleteAll();
        metalRepository.deleteAll();
        panelRepository.deleteAll();
        rebarRepository.deleteAll();
        setRepository.deleteAll();
        unspecifiedRepository.deleteAll();
    }


    @Test
    void testCreateMaterialsFromOrder_ValidMaterials_ShouldReturnOk() throws Exception {
        // Arrange
        doNothing().when(inventoryQueryServiceClient).sendMaterialEvent(
                any(MaterialEvent.class),
                eq(String.valueOf(EventType.ItemRegistered)),
                anyString()
        );
        // Fastener
        CreateMaterialDTO fastener = new CreateMaterialDTO();
        fastener.setType("Bolt");
        fastener.setDiameter("10mm");
        fastener.setLength("50");
        fastener.setLengthUnit(LengthUnits.MM);
        fastener.setStandard("ISO 898-1");
        fastener.setClazz("8.8");
        fastener.setDescription("High strength bolt");
        fastener.setSpecificationFileUrl("http://example.com/fastener-spec.pdf");

        // Galvanized Sheet
        CreateMaterialDTO galvanizedSheet = new CreateMaterialDTO();
        galvanizedSheet.setType("Sheet");
        galvanizedSheet.setMaxLength("2000");
        galvanizedSheet.setMaxLengthUnit(LengthUnits.MM);
        galvanizedSheet.setNumberOfSheets("100");
        galvanizedSheet.setDescription("Galvanized steel sheets");
        galvanizedSheet.setSpecificationFileUrl("http://example.com/galvanized-sheet-spec.pdf");

        // Insulation
        CreateMaterialDTO insulation = new CreateMaterialDTO();
        insulation.setType("Foam");
        insulation.setThickness("50");
        insulation.setThicknessUnit(LengthUnits.MM);
        insulation.setDescription("Thermal insulation foam");
        insulation.setSpecificationFileUrl("http://example.com/insulation-spec.pdf");

        // Metal
        CreateMaterialDTO metal = new CreateMaterialDTO();
        metal.setTotalWeight("5000");
        metal.setTotalWeightUnit(WeightUnits.KG);
        metal.setKind("Steel");
        metal.setDescription("Heavy-duty steel");
        metal.setSpecificationFileUrl("http://example.com/metal-spec.pdf");

        // Rebar
        CreateMaterialDTO rebar = new CreateMaterialDTO();
        rebar.setMaxLength("6000");
        rebar.setMaxLengthUnit(LengthUnits.MM);
        rebar.setDescription("Reinforcing steel bar");
        rebar.setSpecificationFileUrl("http://example.com/rebar-spec.pdf");

        // Set
        CreateMaterialDTO set = new CreateMaterialDTO();
        set.setColor("Red");
        set.setMaxLength("1000");
        set.setMaxLengthUnit(LengthUnits.MM);
        set.setDescription("Tool set with case");
        set.setSpecificationFileUrl("http://example.com/set-spec.pdf");

        // Unspecified
        CreateMaterialDTO unspecified = new CreateMaterialDTO();
        unspecified.setDescription("Miscellaneous items");
        unspecified.setSpecificationFileUrl("http://example.com/unspecified-spec.pdf");

        // Panels
        CreateMaterialDTO panels = new CreateMaterialDTO();
        panels.setType("Wall Panel");
        panels.setColor("White");
        panels.setLength("2400");
        panels.setLengthUnit(LengthUnits.MM);
        panels.setWidth("1200");
        panels.setWidthUnit(LengthUnits.MM);
        panels.setTotalThickness("50");
        panels.setTotalThicknessUnit(LengthUnits.MM);
        panels.setFrontSheetThickness("0.5");
        panels.setFrontSheetThicknessUnit(LengthUnits.MM);
        panels.setBackSheetThickness("0.4");
        panels.setBackSheetThicknessUnit(LengthUnits.MM);
        panels.setDescription("Fire-resistant wall panels");
        panels.setSpecificationFileUrl("http://example.com/panels-spec.pdf");

        // Map materials by category
        Map<String, List<CreateMaterialDTO>> materialsMap = new HashMap<>();
        materialsMap.put(MaterialType.FASTENERS.name(), List.of(fastener));
        materialsMap.put(MaterialType.GALVANIZED_SHEET.name(), List.of(galvanizedSheet));
        materialsMap.put(MaterialType.INSULATION.name(), List.of(insulation));
        materialsMap.put(MaterialType.METAL.name(), List.of(metal));
        materialsMap.put(MaterialType.REBAR.name(), List.of(rebar));
        materialsMap.put(MaterialType.SET.name(), List.of(set));
        materialsMap.put(MaterialType.UNSPECIFIED.name(), List.of(unspecified));
        materialsMap.put(MaterialType.PANELS.name(), List.of(panels));

        CreateOrderMaterialsDto createOrderMaterialsDto = new CreateOrderMaterialsDto();
        createOrderMaterialsDto.setMaterials(materialsMap);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/materials/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderMaterialsDto)))
                .andExpect(status().isOk());

        ArgumentCaptor<MaterialEvent> eventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(inventoryQueryServiceClient, times(8)).sendMaterialEvent(
                eventCaptor.capture(),
                eq(String.valueOf(EventType.ItemRegistered)),
                anyString()
        );

        List<MaterialEvent> capturedEvents = eventCaptor.getAllValues();



        for (MaterialEvent event : capturedEvents) {

            if (event.getEvent() instanceof RegisterFastenerEvent registerFastenerEvent) {
                assertEquals(fastener.getDiameter(), registerFastenerEvent.getDiameter());
                assertEquals(fastener.getLength(), registerFastenerEvent.getLength());
                assertEquals(fastener.getLengthUnit(), registerFastenerEvent.getLengthUnit());
                assertEquals(fastener.getStandard(), registerFastenerEvent.getStandard());
                assertEquals(fastener.getClazz(), registerFastenerEvent.getClazz());
                assertEquals(fastener.getType(), registerFastenerEvent.getType());
                assertEquals(fastener.getSpecificationFileUrl(), registerFastenerEvent.getSpecificationFileUrl());
                assertEquals(fastener.getDescription(), registerFastenerEvent.getDescription());
            }

            else if (event.getEvent() instanceof RegisterGalvanizedEvent registerGalvanizedSheetEvent) {
                assertEquals(galvanizedSheet.getMaxLength(), registerGalvanizedSheetEvent.getMaxLength());
                assertEquals(galvanizedSheet.getMaxLengthUnit(), registerGalvanizedSheetEvent.getMaxLengthUnit());
                assertEquals(galvanizedSheet.getNumberOfSheets(), registerGalvanizedSheetEvent.getNumberOfSheets());
                assertEquals(galvanizedSheet.getType(), registerGalvanizedSheetEvent.getType());
                assertEquals(galvanizedSheet.getSpecificationFileUrl(), registerGalvanizedSheetEvent.getSpecificationFileUrl());
                assertEquals(galvanizedSheet.getDescription(), registerGalvanizedSheetEvent.getDescription());
            }

            else if (event.getEvent() instanceof RegisterInsulationEvent registerInsulationEvent) {
                assertEquals(insulation.getThickness(), registerInsulationEvent.getThickness());
                assertEquals(insulation.getThicknessUnit(), registerInsulationEvent.getThicknessUnit());
                assertEquals(insulation.getType(), registerInsulationEvent.getType());
                assertEquals(insulation.getSpecificationFileUrl(), registerInsulationEvent.getSpecificationFileUrl());
                assertEquals(insulation.getDescription(), registerInsulationEvent.getDescription());
            }

            else if (event.getEvent() instanceof RegisterMetalEvent registerMetalEvent) {
                assertEquals(metal.getTotalWeight(), registerMetalEvent.getTotalWeight());
                assertEquals(metal.getTotalWeightUnit(), registerMetalEvent.getTotalWeightUnit());
                assertEquals(metal.getKind(), registerMetalEvent.getKind());
                assertEquals(metal.getSpecificationFileUrl(), registerMetalEvent.getSpecificationFileUrl());
                assertEquals(metal.getDescription(), registerMetalEvent.getDescription());
            }

            else if (event.getEvent() instanceof RegisterRebarEvent registerRebarEvent) {
                assertEquals(rebar.getMaxLength(), registerRebarEvent.getMaxLength());
                assertEquals(rebar.getMaxLengthUnit(), registerRebarEvent.getMaxLengthUnit());
                assertEquals(rebar.getSpecificationFileUrl(), registerRebarEvent.getSpecificationFileUrl());
                assertEquals(rebar.getDescription(), registerRebarEvent.getDescription());
            }

            else if (event.getEvent() instanceof RegisterSetEvent registerSetEvent) {
                assertEquals(set.getColor(), registerSetEvent.getColor());
                assertEquals(set.getMaxLength(), registerSetEvent.getMaxLength());
                assertEquals(set.getMaxLengthUnit(), registerSetEvent.getMaxLengthUnit());
                assertEquals(set.getSpecificationFileUrl(), registerSetEvent.getSpecificationFileUrl());
                assertEquals(set.getDescription(), registerSetEvent.getDescription());
            }

            else if (event.getEvent() instanceof RegisterUnspecifiedEvent registerUnspecifiedEvent) {
                assertEquals(unspecified.getDescription(), registerUnspecifiedEvent.getDescription());
                assertEquals(unspecified.getSpecificationFileUrl(), registerUnspecifiedEvent.getSpecificationFileUrl());
            }

            else if (event.getEvent() instanceof RegisterPanelEvent registerPanelEvent) {
                assertEquals(panels.getType(), registerPanelEvent.getType());
                assertEquals(panels.getColor(), registerPanelEvent.getColor());
                assertEquals(panels.getLength(), registerPanelEvent.getLength());
                assertEquals(panels.getLengthUnit(), registerPanelEvent.getLengthUnit());
                assertEquals(panels.getWidth(), registerPanelEvent.getWidth());
                assertEquals(panels.getWidthUnit(), registerPanelEvent.getWidthUnit());
                assertEquals(panels.getTotalThickness(), registerPanelEvent.getTotalThickness());
                assertEquals(panels.getTotalThicknessUnit(), registerPanelEvent.getTotalThicknessUnit());
                assertEquals(panels.getFrontSheetThickness(), registerPanelEvent.getFrontSheetThickness());
                assertEquals(panels.getFrontSheetThicknessUnit(), registerPanelEvent.getFrontSheetThicknessUnit());
                assertEquals(panels.getBackSheetThickness(), registerPanelEvent.getBackSheetThickness());
                assertEquals(panels.getBackSheetThicknessUnit(), registerPanelEvent.getBackSheetThicknessUnit());
                assertEquals(panels.getSpecificationFileUrl(), registerPanelEvent.getSpecificationFileUrl());
                assertEquals(panels.getDescription(), registerPanelEvent.getDescription());
            }
        }
        // Assert
        // Verify that the materials were stored correctly
        assertFastener(fastenerRepository.findAll().get(0), fastener);
        assertGalvanizedSheet(galvanisedSheetRepository.findAll().get(0), galvanizedSheet);
        assertInsulation(insulationRepository.findAll().get(0), insulation);
        assertMetal(metalRepository.findAll().get(0), metal);
        assertRebar(rebarRepository.findAll().get(0), rebar);
        assertSet(setRepository.findAll().get(0), set);
        assertUnspecified(unspecifiedRepository.findAll().get(0), unspecified);
        assertPanel(panelRepository.findAll().get(0), panels);
    }

    // Helper assertion methods
    private void assertFastener(FastenerEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getDiameter(), actual.getDiameter());
        assertEquals(expected.getLength(), actual.getLength());
        assertEquals(expected.getLengthUnit(), actual.getLengthUnit());
        assertEquals(expected.getStandard(), actual.getStandard());
        assertEquals(expected.getClazz(), actual.getClazz());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }

    private void assertGalvanizedSheet(GalvanisedSheetEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getMaxLength(), actual.getMaxLength());
        assertEquals(expected.getMaxLengthUnit(), actual.getMaxLengthUnit());
        assertEquals(expected.getNumberOfSheets(), actual.getNumberOfSheets());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }

    private void assertInsulation(InsulationEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getThickness(), actual.getThickness());
        assertEquals(expected.getThicknessUnit(), actual.getThicknessUnit());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }

    private void assertMetal(MetalEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getTotalWeight(), actual.getTotalWeight());
        assertEquals(expected.getTotalWeightUnit(), actual.getTotalWeightUnit());
        assertEquals(expected.getKind(), actual.getKind());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }

    private void assertRebar(RebarEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getMaxLength(), actual.getMaxLength());
        assertEquals(expected.getMaxLengthUnit(), actual.getMaxLengthUnit());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }

    private void assertSet(SetEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getColor(), actual.getColor());
        assertEquals(expected.getMaxLength(), actual.getMaxLength());
        assertEquals(expected.getMaxLengthUnit(), actual.getMaxLengthUnit());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }

    private void assertUnspecified(UnspecifiedEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }

    private void assertPanel(PanelEntity actual, CreateMaterialDTO expected) {
        assertNotNull(actual);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getColor(), actual.getColor());
        assertEquals(expected.getLength(), actual.getLength());
        assertEquals(expected.getLengthUnit(), actual.getLengthUnit());
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getWidthUnit(), actual.getWidthUnit());
        assertEquals(expected.getTotalThickness(), actual.getTotalThickness());
        assertEquals(expected.getTotalThicknessUnit(), actual.getTotalThicknessUnit());
        assertEquals(expected.getFrontSheetThickness(), actual.getFrontSheetThickness());
        assertEquals(expected.getFrontSheetThicknessUnit(), actual.getFrontSheetThicknessUnit());
        assertEquals(expected.getBackSheetThickness(), actual.getBackSheetThickness());
        assertEquals(expected.getBackSheetThicknessUnit(), actual.getBackSheetThicknessUnit());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getSpecificationFileUrl(), actual.getSpecificationFileUrl());
    }


    @Test
    void testCreateMaterialsFromOrder_NullMaterials_ShouldReturnBadRequest() throws Exception {
        // Arrange
        CreateOrderMaterialsDto createOrderMaterialsDto = new CreateOrderMaterialsDto();
        createOrderMaterialsDto.setMaterials(null);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/materials/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderMaterialsDto)))
                .andExpect(status().isBadRequest());
    }


}