package bg.mck.service;

import bg.mck.dto.*;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.MaterialType;
import bg.mck.enums.WeightUnits;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.*;
import bg.mck.service.MaterialRegisterService;
import bg.mck.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MaterialRegisterServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private FastenerRepository fastenerRepository;

    @Mock
    private GalvanisedSheetRepository galvanisedSheetRepository;

    @Mock
    private InsulationRepository insulationRepository;

    @Mock
    private MetalRepository metalRepository;

    @Mock
    private PanelRepository panelRepository;

    @Mock
    private RebarRepository rebarRepository;

    @Mock
    private SetRepository setRepository;

    @Mock
    private UnspecifiedRepository unspecifiedRepository;

    @Mock
    private ValidationUtil validationUtil;

    @Spy
    @InjectMocks
    private MaterialRegisterService materialRegisterService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMaterialsFromOrder_ValidMaterials_AllTypesWithCorrectFields() throws MethodArgumentNotValidException, NoSuchMethodException {

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

        // Mocking the material existence check to return false (indicating they don't exist yet)
        doReturn(false).when(materialRegisterService).materialExists(any(CreateMaterialDTO.class));

        // Mocking the creation process
        doNothing().when(materialRegisterService).createMaterial(any(CreateMaterialDTO.class));

        // Act
        materialRegisterService.createMaterialsFromOrder(createOrderMaterialsDto);

        // Assert
        verify(materialRegisterService).createMaterial(fastener);
        verify(materialRegisterService).createMaterial(galvanizedSheet);
        verify(materialRegisterService).createMaterial(insulation);
        verify(materialRegisterService).createMaterial(metal);
        verify(materialRegisterService).createMaterial(rebar);
        verify(materialRegisterService).createMaterial(set);
        verify(materialRegisterService).createMaterial(unspecified);
        verify(materialRegisterService).createMaterial(panels);
    }

    @Test
    void testCreateMaterialsFromOrder_MaterialAlreadyExists() throws Exception {


        CreateMaterialDTO fastener = new CreateMaterialDTO();
        fastener.setType("Bolt");
        fastener.setDiameter("10mm");
        fastener.setLength("50");
        fastener.setLengthUnit(LengthUnits.MM);
        fastener.setStandard("ISO 898-1");
        fastener.setClazz("8.8");
        fastener.setDescription("High strength bolt");
        fastener.setSpecificationFileUrl("http://example.com/fastener-spec.pdf");

        Map<String, List<CreateMaterialDTO>> materialsMap = new HashMap<>();
        materialsMap.put(MaterialType.FASTENERS.name(), List.of(fastener));

        CreateOrderMaterialsDto createOrderMaterialsDto = new CreateOrderMaterialsDto();
        createOrderMaterialsDto.setMaterials(materialsMap);

        doReturn(true).when(materialRegisterService).materialExists(any(CreateMaterialDTO.class));


        materialRegisterService.createMaterialsFromOrder(createOrderMaterialsDto);

        Mockito.verify(materialRegisterService, Mockito.never()).createMaterial(fastener);
    }

}
