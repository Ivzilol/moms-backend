package bg.mck.service;

import bg.mck.dto.*;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.MaterialType;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.material.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialQueryServiceTest {

    @Mock
    private FastenerRepository fastenerRepository;
    @Mock
    private GalvanisedSheetRepository galvanisedSheetRepository;
    @Mock
    private InsulationRepository insulationRepository;
    @Mock
    private PanelRepository panelRepository;
    @Mock
    private RebarRepository rebarRepository;
    @Mock
    private SetRepository setRepository;
    @Mock
    private UnspecifiedRepository unspecifiedRepository;
    @Mock
    private MetalRepository metalRepository;
    @Mock
    private MaterialRedisService redisService;
    @Mock
    private MaterialEventService materialEventService;
    @Mock
    private MaterialMapper materialMapper;

    @InjectMocks
    @Spy
    private MaterialQueryService materialQueryService;

    @Test
    public void testGetMaterialByCategoryAndId_Fastener_FoundInCache() {
        String id = "1";
        String category = "FASTENERS";
        FastenersDTO cachedDto = new FastenersDTO();
        when(redisService.getCachedObject(id, category, FastenerEntity.class)).thenReturn(new FastenerEntity());
        when(materialMapper.toFastenerDTO(any(FastenerEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, FastenerEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(FastenerEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Fastener_NotFoundInCache() {
        String id = "1";
        String category = "FASTENERS";
        FastenerEntity entity = new FastenerEntity();
        when(redisService.getCachedObject(id, category, FastenerEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, FastenerEntity.class)).thenReturn(entity);
        when(materialMapper.toFastenerDTO(entity)).thenReturn(new FastenersDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, FastenerEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, FastenerEntity.class);
        verify(materialMapper, times(1)).toFastenerDTO(entity);
    }

    @Test
    public void testGetMaterialByCategoryAndId_GalvanizedSheet_FoundInCache() {
        String id = "2";
        String category = "GALVANIZED_SHEET";
        GalvanisedDTO cachedDto = new GalvanisedDTO();
        when(redisService.getCachedObject(id, category, GalvanisedSheetEntity.class)).thenReturn(new GalvanisedSheetEntity());
        when(materialMapper.toGalvanizedDTO(any(GalvanisedSheetEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, GalvanisedSheetEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(GalvanisedSheetEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_GalvanizedSheet_NotFoundInCache() {
        String id = "2";
        String category = "GALVANIZED_SHEET";
        GalvanisedSheetEntity entity = new GalvanisedSheetEntity();
        when(redisService.getCachedObject(id, category, GalvanisedSheetEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, GalvanisedSheetEntity.class)).thenReturn(entity);
        when(materialMapper.toGalvanizedDTO(entity)).thenReturn(new GalvanisedDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, GalvanisedSheetEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, GalvanisedSheetEntity.class);
        verify(materialMapper, times(1)).toGalvanizedDTO(entity);
    }

    @Test
    public void testGetMaterialByCategoryAndId_Insulation_FoundInCache() {
        String id = "3";
        String category = "INSULATION";
        InsulationDTO cachedDto = new InsulationDTO();
        when(redisService.getCachedObject(id, category, InsulationEntity.class)).thenReturn(new InsulationEntity());
        when(materialMapper.toInsulationDTO(any(InsulationEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, InsulationEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(InsulationEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Insulation_NotFoundInCache() {
        String id = "3";
        String category = "INSULATION";
        InsulationEntity entity = new InsulationEntity();
        when(redisService.getCachedObject(id, category, InsulationEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, InsulationEntity.class)).thenReturn(entity);
        when(materialMapper.toInsulationDTO(entity)).thenReturn(new InsulationDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, InsulationEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, InsulationEntity.class);
        verify(materialMapper, times(1)).toInsulationDTO(entity);
    }

    // MetalEntity tests
    @Test
    public void testGetMaterialByCategoryAndId_Metal_FoundInCache() {
        String id = "4";
        String category = "METAL";
        MetalDTO cachedDto = new MetalDTO();
        when(redisService.getCachedObject(id, category, MetalEntity.class)).thenReturn(new MetalEntity());
        when(materialMapper.toMetalDTO(any(MetalEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, MetalEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(MetalEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Metal_NotFoundInCache() {
        String id = "4";
        String category = "METAL";
        MetalEntity entity = new MetalEntity();
        when(redisService.getCachedObject(id, category, MetalEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, MetalEntity.class)).thenReturn(entity);
        when(materialMapper.toMetalDTO(entity)).thenReturn(new MetalDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, MetalEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, MetalEntity.class);
        verify(materialMapper, times(1)).toMetalDTO(entity);
    }

    // PanelEntity tests
    @Test
    public void testGetMaterialByCategoryAndId_Panel_FoundInCache() {
        String id = "5";
        String category = "PANELS";
        PanelsDTO cachedDto = new PanelsDTO();
        when(redisService.getCachedObject(id, category, PanelEntity.class)).thenReturn(new PanelEntity());
        when(materialMapper.toPanelDTO(any(PanelEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, PanelEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(PanelEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Panel_NotFoundInCache() {
        String id = "5";
        String category = "PANELS";
        PanelEntity entity = new PanelEntity();
        when(redisService.getCachedObject(id, category, PanelEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, PanelEntity.class)).thenReturn(entity);
        when(materialMapper.toPanelDTO(entity)).thenReturn(new PanelsDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, PanelEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, PanelEntity.class);
        verify(materialMapper, times(1)).toPanelDTO(entity);
    }

    // RebarEntity tests
    @Test
    public void testGetMaterialByCategoryAndId_Rebar_FoundInCache() {
        String id = "6";
        String category = "REBAR";
        RebarDTO cachedDto = new RebarDTO();
        when(redisService.getCachedObject(id, category, RebarEntity.class)).thenReturn(new RebarEntity());
        when(materialMapper.toRebarDTO(any(RebarEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, RebarEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(RebarEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Rebar_NotFoundInCache() {
        String id = "6";
        String category = "REBAR";
        RebarEntity entity = new RebarEntity();
        when(redisService.getCachedObject(id, category, RebarEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, RebarEntity.class)).thenReturn(entity);
        when(materialMapper.toRebarDTO(entity)).thenReturn(new RebarDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, RebarEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, RebarEntity.class);
        verify(materialMapper, times(1)).toRebarDTO(entity);
    }

    // SetEntity tests
    @Test
    public void testGetMaterialByCategoryAndId_Set_FoundInCache() {
        String id = "7";
        String category = "SET";
        SetDTO cachedDto = new SetDTO();
        when(redisService.getCachedObject(id, category, SetEntity.class)).thenReturn(new SetEntity());
        when(materialMapper.toSetDTO(any(SetEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, SetEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(SetEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Set_NotFoundInCache() {
        String id = "7";
        String category = "SET";
        SetEntity entity = new SetEntity();
        when(redisService.getCachedObject(id, category, SetEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, SetEntity.class)).thenReturn(entity);
        when(materialMapper.toSetDTO(entity)).thenReturn(new SetDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, SetEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, SetEntity.class);
        verify(materialMapper, times(1)).toSetDTO(entity);
    }

    // UnspecifiedEntity tests
    @Test
    public void testGetMaterialByCategoryAndId_Unspecified_FoundInCache() {
        String id = "8";
        String category = "UNSPECIFIED";
        UnspecifiedDTO cachedDto = new UnspecifiedDTO();
        when(redisService.getCachedObject(id, category, UnspecifiedEntity.class)).thenReturn(new UnspecifiedEntity());
        when(materialMapper.toUnspecifiedDTO(any(UnspecifiedEntity.class))).thenReturn(cachedDto);

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        assertEquals(cachedDto, result);
        verify(redisService, times(1)).getCachedObject(id, category, UnspecifiedEntity.class);
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(UnspecifiedEntity.class));
    }

    @Test
    public void testGetMaterialByCategoryAndId_Unspecified_NotFoundInCache() {
        String id = "8";
        String category = "UNSPECIFIED";
        UnspecifiedEntity entity = new UnspecifiedEntity();
        when(redisService.getCachedObject(id, category, UnspecifiedEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(id, category, UnspecifiedEntity.class)).thenReturn(entity);
        when(materialMapper.toUnspecifiedDTO(entity)).thenReturn(new UnspecifiedDTO());

        MaterialDTO result = materialQueryService.getMaterialByCategoryAndId(category, id);

        assertNotNull(result);
        verify(redisService, times(1)).getCachedObject(id, category, UnspecifiedEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(id, category, UnspecifiedEntity.class);
        verify(materialMapper, times(1)).toUnspecifiedDTO(entity);
    }

    @Test
    public void testGetMaterialByCategoryAndId_InvalidCategory() {
        String id = "1";
        String category = "INVALID";

        assertThrows(InvalidCategoryException.class, () -> materialQueryService.getMaterialByCategoryAndId(category, id));

        verifyNoInteractions(redisService, materialEventService, materialMapper);
    }

    @Test
    public void testGetAllMaterialsByCategory_Fasteners_FoundInCache() {
        String category = "FASTENERS";
        FastenerEntity fastener1 = new FastenerEntity();
        fastener1.setId("1");
        fastener1.setName("name1");
        FastenerEntity fastener2 = new FastenerEntity();
        fastener2.setId("2");
        fastener2.setName("name2");

        FastenersDTO cachedDto1 = new FastenersDTO();
        cachedDto1.setId(fastener1.getId());
        cachedDto1.setName(fastener1.getName());
        FastenersDTO cachedDto2 = new FastenersDTO();
        cachedDto2.setId(fastener2.getId());
        cachedDto2.setName(fastener2.getName());

        // Mock repository to return a list of fasteners
        when(fastenerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(fastener1, fastener2));

        // Mock Redis service to return cached entities
        when(redisService.getCachedObject(fastener1.getId(), category, FastenerEntity.class)).thenReturn(fastener1);
        when(redisService.getCachedObject(fastener2.getId(), category, FastenerEntity.class)).thenReturn(fastener2);

        // Mock the mapper to convert entities to DTOs
        when(materialMapper.toFastenerDTO(any(FastenerEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        // Execute the method
        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        FastenersDTO resultDto1 = (FastenersDTO) result.get(0);
        FastenersDTO resultDto2 = (FastenersDTO) result.get(1);

        assertEquals(fastener1.getId(), resultDto1.getId());
        assertEquals(fastener1.getName(), resultDto1.getName());
        assertEquals(fastener2.getId(), resultDto2.getId());
        assertEquals(fastener2.getName(), resultDto2.getName());

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify interactions
        verify(redisService, times(1)).getCachedObject(fastener1.getId(), category, FastenerEntity.class);
        verify(redisService, times(1)).getCachedObject(fastener2.getId(), category, FastenerEntity.class);
        verify(materialMapper, times(2)).toFastenerDTO(any(FastenerEntity.class)); // Adjusted to expect 2 invocations
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(FastenerEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Fasteners_NotFoundInCache() {
        String category = "FASTENERS";
        FastenerEntity fastener1 = new FastenerEntity();
        fastener1.setId("1");
        fastener1.setName("name1");
        FastenerEntity fastener2 = new FastenerEntity();
        fastener2.setId("2");
        fastener2.setName("name2");

        FastenersDTO dto1 = new FastenersDTO();
        dto1.setId(fastener1.getId());
        dto1.setName(fastener1.getName());
        FastenersDTO dto2 = new FastenersDTO();
        dto2.setId(fastener2.getId());
        dto2.setName(fastener2.getName());

        // Mock repository to return a list of fasteners
        when(fastenerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(fastener1, fastener2));

        // Mock Redis service to return null, simulating a cache miss
        when(redisService.getCachedObject(fastener1.getId(), category, FastenerEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(fastener2.getId(), category, FastenerEntity.class)).thenReturn(null);

        // Mock the event service to reconstruct entities
        when(materialEventService.reconstructMaterialEntity(fastener1.getId(), category, FastenerEntity.class)).thenReturn(fastener1);
        when(materialEventService.reconstructMaterialEntity(fastener2.getId(), category, FastenerEntity.class)).thenReturn(fastener2);

        // Mock the mapper to convert entities to DTOs
        when(materialMapper.toFastenerDTO(fastener1)).thenReturn(dto1);
        when(materialMapper.toFastenerDTO(fastener2)).thenReturn(dto2);

        // Execute the method
        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify that the names and IDs match
        FastenersDTO resultDto1 = (FastenersDTO) result.get(0);
        FastenersDTO resultDto2 = (FastenersDTO) result.get(1);

        assertEquals(fastener1.getId(), resultDto1.getId());
        assertEquals(fastener1.getName(), resultDto1.getName());
        assertEquals(fastener2.getId(), resultDto2.getId());
        assertEquals(fastener2.getName(), resultDto2.getName());

        // Verify interactions
        verify(redisService, times(1)).getCachedObject(fastener1.getId(), category, FastenerEntity.class);
        verify(redisService, times(1)).getCachedObject(fastener2.getId(), category, FastenerEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(fastener1.getId(), category, FastenerEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(fastener2.getId(), category, FastenerEntity.class);
        verify(materialMapper, times(2)).toFastenerDTO(any(FastenerEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_GalvanizedSheet_FoundInCache() {
        String category = "GALVANIZED_SHEET";
        GalvanisedSheetEntity sheet1 = new GalvanisedSheetEntity();
        sheet1.setId("1");
        sheet1.setName("name1");
        GalvanisedSheetEntity sheet2 = new GalvanisedSheetEntity();
        sheet2.setId("2");
        sheet2.setName("name2");

        GalvanisedDTO cachedDto1 = new GalvanisedDTO();
        cachedDto1.setId(Long.valueOf(sheet1.getId()));
        cachedDto1.setName(sheet1.getName());
        GalvanisedDTO cachedDto2 = new GalvanisedDTO();
        cachedDto2.setId(Long.valueOf(sheet2.getId()));
        cachedDto2.setName(sheet2.getName());

        when(galvanisedSheetRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(sheet1, sheet2));
        when(redisService.getCachedObject(sheet1.getId(), category, GalvanisedSheetEntity.class)).thenReturn(sheet1);
        when(redisService.getCachedObject(sheet2.getId(), category, GalvanisedSheetEntity.class)).thenReturn(sheet2);
        when(materialMapper.toGalvanizedDTO(any(GalvanisedSheetEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        GalvanisedDTO resultDto1 = (GalvanisedDTO) result.get(0);
        GalvanisedDTO resultDto2 = (GalvanisedDTO) result.get(1);

        assertEquals(sheet1.getId(), resultDto1.getId().toString());
        assertEquals(sheet1.getName(), resultDto1.getName());
        assertEquals(sheet2.getId(), resultDto2.getId().toString());
        assertEquals(sheet2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(sheet1.getId(), category, GalvanisedSheetEntity.class);
        verify(redisService, times(1)).getCachedObject(sheet2.getId(), category, GalvanisedSheetEntity.class);
        verify(materialMapper, times(2)).toGalvanizedDTO(any(GalvanisedSheetEntity.class));
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(GalvanisedSheetEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_GalvanizedSheet_NotFoundInCache() {
        String category = "GALVANIZED_SHEET";
        GalvanisedSheetEntity sheet1 = new GalvanisedSheetEntity();
        sheet1.setId("1");
        sheet1.setName("name1");
        GalvanisedSheetEntity sheet2 = new GalvanisedSheetEntity();
        sheet2.setId("2");
        sheet2.setName("name2");

        GalvanisedDTO dto1 = new GalvanisedDTO();
        dto1.setId(Long.valueOf(sheet1.getId()));
        dto1.setName(sheet1.getName());
        GalvanisedDTO dto2 = new GalvanisedDTO();
        dto2.setId(Long.valueOf(sheet2.getId()));
        dto2.setName(sheet2.getName());

        when(galvanisedSheetRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(sheet1, sheet2));
        when(redisService.getCachedObject(sheet1.getId(), category, GalvanisedSheetEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(sheet2.getId(), category, GalvanisedSheetEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(sheet1.getId(), category, GalvanisedSheetEntity.class)).thenReturn(sheet1);
        when(materialEventService.reconstructMaterialEntity(sheet2.getId(), category, GalvanisedSheetEntity.class)).thenReturn(sheet2);
        when(materialMapper.toGalvanizedDTO(sheet1)).thenReturn(dto1);
        when(materialMapper.toGalvanizedDTO(sheet2)).thenReturn(dto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        GalvanisedDTO resultDto1 = (GalvanisedDTO) result.get(0);
        GalvanisedDTO resultDto2 = (GalvanisedDTO) result.get(1);

        assertEquals(sheet1.getId(), resultDto1.getId().toString());
        assertEquals(sheet1.getName(), resultDto1.getName());
        assertEquals(sheet2.getId(), resultDto2.getId().toString());
        assertEquals(sheet2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(sheet1.getId(), category, GalvanisedSheetEntity.class);
        verify(redisService, times(1)).getCachedObject(sheet2.getId(), category, GalvanisedSheetEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(sheet1.getId(), category, GalvanisedSheetEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(sheet2.getId(), category, GalvanisedSheetEntity.class);
        verify(materialMapper, times(2)).toGalvanizedDTO(any(GalvanisedSheetEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Insulation_FoundInCache() {
        String category = "INSULATION";
        InsulationEntity insulation1 = new InsulationEntity();
        insulation1.setId("1");
        insulation1.setName("name1");
        InsulationEntity insulation2 = new InsulationEntity();
        insulation2.setId("2");
        insulation2.setName("name2");

        InsulationDTO cachedDto1 = new InsulationDTO();
        cachedDto1.setId(Long.valueOf(insulation1.getId()));
        cachedDto1.setName(insulation1.getName());
        InsulationDTO cachedDto2 = new InsulationDTO();
        cachedDto2.setId(Long.valueOf(insulation2.getId()));
        cachedDto2.setName(insulation2.getName());

        when(insulationRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(insulation1, insulation2));
        when(redisService.getCachedObject(insulation1.getId(), category, InsulationEntity.class)).thenReturn(insulation1);
        when(redisService.getCachedObject(insulation2.getId(), category, InsulationEntity.class)).thenReturn(insulation2);
        when(materialMapper.toInsulationDTO(any(InsulationEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        InsulationDTO resultDto1 = (InsulationDTO) result.get(0);
        InsulationDTO resultDto2 = (InsulationDTO) result.get(1);

        assertEquals(insulation1.getId(), resultDto1.getId().toString());
        assertEquals(insulation1.getName(), resultDto1.getName());
        assertEquals(insulation2.getId(), resultDto2.getId().toString());
        assertEquals(insulation2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(insulation1.getId(), category, InsulationEntity.class);
        verify(redisService, times(1)).getCachedObject(insulation2.getId(), category, InsulationEntity.class);
        verify(materialMapper, times(2)).toInsulationDTO(any(InsulationEntity.class));
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(InsulationEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Insulation_NotFoundInCache() {
        String category = "INSULATION";
        InsulationEntity insulation1 = new InsulationEntity();
        insulation1.setId("1");
        insulation1.setName("name1");
        InsulationEntity insulation2 = new InsulationEntity();
        insulation2.setId("2");
        insulation2.setName("name2");

        InsulationDTO dto1 = new InsulationDTO();
        dto1.setId(Long.valueOf(insulation1.getId()));
        dto1.setName(insulation1.getName());
        InsulationDTO dto2 = new InsulationDTO();
        dto2.setId(Long.valueOf(insulation2.getId()));
        dto2.setName(insulation2.getName());

        when(insulationRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(insulation1, insulation2));
        when(redisService.getCachedObject(insulation1.getId(), category, InsulationEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(insulation2.getId(), category, InsulationEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(insulation1.getId(), category, InsulationEntity.class)).thenReturn(insulation1);
        when(materialEventService.reconstructMaterialEntity(insulation2.getId(), category, InsulationEntity.class)).thenReturn(insulation2);
        when(materialMapper.toInsulationDTO(insulation1)).thenReturn(dto1);
        when(materialMapper.toInsulationDTO(insulation2)).thenReturn(dto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        InsulationDTO resultDto1 = (InsulationDTO) result.get(0);
        InsulationDTO resultDto2 = (InsulationDTO) result.get(1);

        assertEquals(insulation1.getId(), resultDto1.getId().toString());
        assertEquals(insulation1.getName(), resultDto1.getName());
        assertEquals(insulation2.getId(), resultDto2.getId().toString());
        assertEquals(insulation2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(insulation1.getId(), category, InsulationEntity.class);
        verify(redisService, times(1)).getCachedObject(insulation2.getId(), category, InsulationEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(insulation1.getId(), category, InsulationEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(insulation2.getId(), category, InsulationEntity.class);
        verify(materialMapper, times(2)).toInsulationDTO(any(InsulationEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Metal_FoundInCache() {
        String category = "METAL";
        MetalEntity metal1 = new MetalEntity();
        metal1.setId("1");
        metal1.setName("name1");
        MetalEntity metal2 = new MetalEntity();
        metal2.setId("2");
        metal2.setName("name2");

        MetalDTO cachedDto1 = new MetalDTO();
        cachedDto1.setId(metal1.getId());
        cachedDto1.setName(metal1.getName());
        MetalDTO cachedDto2 = new MetalDTO();
        cachedDto2.setId(metal2.getId());
        cachedDto2.setName(metal2.getName());

        when(metalRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(metal1, metal2));
        when(redisService.getCachedObject(metal1.getId(), category, MetalEntity.class)).thenReturn(metal1);
        when(redisService.getCachedObject(metal2.getId(), category, MetalEntity.class)).thenReturn(metal2);
        when(materialMapper.toMetalDTO(any(MetalEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        MetalDTO resultDto1 = (MetalDTO) result.get(0);
        MetalDTO resultDto2 = (MetalDTO) result.get(1);

        assertEquals(metal1.getId(), resultDto1.getId());
        assertEquals(metal1.getName(), resultDto1.getName());
        assertEquals(metal2.getId(), resultDto2.getId());
        assertEquals(metal2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(metal1.getId(), category, MetalEntity.class);
        verify(redisService, times(1)).getCachedObject(metal2.getId(), category, MetalEntity.class);
        verify(materialMapper, times(2)).toMetalDTO(any(MetalEntity.class));
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(MetalEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Metal_NotFoundInCache() {
        String category = "METAL";
        MetalEntity metal1 = new MetalEntity();
        metal1.setId("1");
        metal1.setName("name1");
        MetalEntity metal2 = new MetalEntity();
        metal2.setId("2");
        metal2.setName("name2");

        MetalDTO dto1 = new MetalDTO();
        dto1.setId(metal1.getId());
        dto1.setName(metal1.getName());
        MetalDTO dto2 = new MetalDTO();
        dto2.setId(metal2.getId());
        dto2.setName(metal2.getName());

        when(metalRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(metal1, metal2));
        when(redisService.getCachedObject(metal1.getId(), category, MetalEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(metal2.getId(), category, MetalEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(metal1.getId(), category, MetalEntity.class)).thenReturn(metal1);
        when(materialEventService.reconstructMaterialEntity(metal2.getId(), category, MetalEntity.class)).thenReturn(metal2);
        when(materialMapper.toMetalDTO(metal1)).thenReturn(dto1);
        when(materialMapper.toMetalDTO(metal2)).thenReturn(dto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        MetalDTO resultDto1 = (MetalDTO) result.get(0);
        MetalDTO resultDto2 = (MetalDTO) result.get(1);

        assertEquals(metal1.getId(), resultDto1.getId());
        assertEquals(metal1.getName(), resultDto1.getName());
        assertEquals(metal2.getId(), resultDto2.getId());
        assertEquals(metal2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(metal1.getId(), category, MetalEntity.class);
        verify(redisService, times(1)).getCachedObject(metal2.getId(), category, MetalEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(metal1.getId(), category, MetalEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(metal2.getId(), category, MetalEntity.class);
        verify(materialMapper, times(2)).toMetalDTO(any(MetalEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Panel_FoundInCache() {
        String category = "PANELS";
        PanelEntity panel1 = new PanelEntity();
        panel1.setId("1");
        panel1.setName("name1");
        PanelEntity panel2 = new PanelEntity();
        panel2.setId("2");
        panel2.setName("name2");

        PanelsDTO cachedDto1 = new PanelsDTO();
        cachedDto1.setId(panel1.getId());
        cachedDto1.setName(panel1.getName());
        PanelsDTO cachedDto2 = new PanelsDTO();
        cachedDto2.setId(panel2.getId());
        cachedDto2.setName(panel2.getName());

        when(panelRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(panel1, panel2));
        when(redisService.getCachedObject(panel1.getId(), category, PanelEntity.class)).thenReturn(panel1);
        when(redisService.getCachedObject(panel2.getId(), category, PanelEntity.class)).thenReturn(panel2);
        when(materialMapper.toPanelDTO(any(PanelEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        PanelsDTO resultDto1 = (PanelsDTO) result.get(0);
        PanelsDTO resultDto2 = (PanelsDTO) result.get(1);

        assertEquals(panel1.getId(), resultDto1.getId());
        assertEquals(panel1.getName(), resultDto1.getName());
        assertEquals(panel2.getId(), resultDto2.getId());
        assertEquals(panel2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(panel1.getId(), category, PanelEntity.class);
        verify(redisService, times(1)).getCachedObject(panel2.getId(), category, PanelEntity.class);
        verify(materialMapper, times(2)).toPanelDTO(any(PanelEntity.class));
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(PanelEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Panel_NotFoundInCache() {
        String category = "PANELS";
        PanelEntity panel1 = new PanelEntity();
        panel1.setId("1");
        panel1.setName("name1");
        PanelEntity panel2 = new PanelEntity();
        panel2.setId("2");
        panel2.setName("name2");

        PanelsDTO dto1 = new PanelsDTO();
        dto1.setId(panel1.getId());
        dto1.setName(panel1.getName());
        PanelsDTO dto2 = new PanelsDTO();
        dto2.setId(panel2.getId());
        dto2.setName(panel2.getName());

        when(panelRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(panel1, panel2));
        when(redisService.getCachedObject(panel1.getId(), category, PanelEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(panel2.getId(), category, PanelEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(panel1.getId(), category, PanelEntity.class)).thenReturn(panel1);
        when(materialEventService.reconstructMaterialEntity(panel2.getId(), category, PanelEntity.class)).thenReturn(panel2);
        when(materialMapper.toPanelDTO(panel1)).thenReturn(dto1);
        when(materialMapper.toPanelDTO(panel2)).thenReturn(dto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        PanelsDTO resultDto1 = (PanelsDTO) result.get(0);
        PanelsDTO resultDto2 = (PanelsDTO) result.get(1);

        assertEquals(panel1.getId(), resultDto1.getId());
        assertEquals(panel1.getName(), resultDto1.getName());
        assertEquals(panel2.getId(), resultDto2.getId());
        assertEquals(panel2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(panel1.getId(), category, PanelEntity.class);
        verify(redisService, times(1)).getCachedObject(panel2.getId(), category, PanelEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(panel1.getId(), category, PanelEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(panel2.getId(), category, PanelEntity.class);
        verify(materialMapper, times(2)).toPanelDTO(any(PanelEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Rebar_FoundInCache() {
        String category = "REBAR";
        RebarEntity rebar1 = new RebarEntity();
        rebar1.setId("1");
        rebar1.setName("name1");
        RebarEntity rebar2 = new RebarEntity();
        rebar2.setId("2");
        rebar2.setName("name2");

        RebarDTO cachedDto1 = new RebarDTO();
        cachedDto1.setId(rebar1.getId());
        cachedDto1.setName(rebar1.getName());
        RebarDTO cachedDto2 = new RebarDTO();
        cachedDto2.setId(rebar2.getId());
        cachedDto2.setName(rebar2.getName());

        when(rebarRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(rebar1, rebar2));
        when(redisService.getCachedObject(rebar1.getId(), category, RebarEntity.class)).thenReturn(rebar1);
        when(redisService.getCachedObject(rebar2.getId(), category, RebarEntity.class)).thenReturn(rebar2);
        when(materialMapper.toRebarDTO(any(RebarEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        RebarDTO resultDto1 = (RebarDTO) result.get(0);
        RebarDTO resultDto2 = (RebarDTO) result.get(1);

        assertEquals(rebar1.getId(), resultDto1.getId());
        assertEquals(rebar1.getName(), resultDto1.getName());
        assertEquals(rebar2.getId(), resultDto2.getId());
        assertEquals(rebar2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(rebar1.getId(), category, RebarEntity.class);
        verify(redisService, times(1)).getCachedObject(rebar2.getId(), category, RebarEntity.class);
        verify(materialMapper, times(2)).toRebarDTO(any(RebarEntity.class));
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(RebarEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Rebar_NotFoundInCache() {
        String category = "REBAR";
        RebarEntity rebar1 = new RebarEntity();
        rebar1.setId("1");
        rebar1.setName("name1");
        RebarEntity rebar2 = new RebarEntity();
        rebar2.setId("2");
        rebar2.setName("name2");

        RebarDTO dto1 = new RebarDTO();
        dto1.setId(rebar1.getId());
        dto1.setName(rebar1.getName());
        RebarDTO dto2 = new RebarDTO();
        dto2.setId(rebar2.getId());
        dto2.setName(rebar2.getName());

        when(rebarRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(rebar1, rebar2));
        when(redisService.getCachedObject(rebar1.getId(), category, RebarEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(rebar2.getId(), category, RebarEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(rebar1.getId(), category, RebarEntity.class)).thenReturn(rebar1);
        when(materialEventService.reconstructMaterialEntity(rebar2.getId(), category, RebarEntity.class)).thenReturn(rebar2);
        when(materialMapper.toRebarDTO(rebar1)).thenReturn(dto1);
        when(materialMapper.toRebarDTO(rebar2)).thenReturn(dto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        RebarDTO resultDto1 = (RebarDTO) result.get(0);
        RebarDTO resultDto2 = (RebarDTO) result.get(1);

        assertEquals(rebar1.getId(), resultDto1.getId());
        assertEquals(rebar1.getName(), resultDto1.getName());
        assertEquals(rebar2.getId(), resultDto2.getId());
        assertEquals(rebar2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(rebar1.getId(), category, RebarEntity.class);
        verify(redisService, times(1)).getCachedObject(rebar2.getId(), category, RebarEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(rebar1.getId(), category, RebarEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(rebar2.getId(), category, RebarEntity.class);
        verify(materialMapper, times(2)).toRebarDTO(any(RebarEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Set_FoundInCache() {
        String category = "SET";
        SetEntity set1 = new SetEntity();
        set1.setId("1");
        set1.setName("name1");
        SetEntity set2 = new SetEntity();
        set2.setId("2");
        set2.setName("name2");

        SetDTO cachedDto1 = new SetDTO();
        cachedDto1.setId(set1.getId());
        cachedDto1.setName(set1.getName());
        SetDTO cachedDto2 = new SetDTO();
        cachedDto2.setId(set2.getId());
        cachedDto2.setName(set2.getName());

        when(setRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(set1, set2));
        when(redisService.getCachedObject(set1.getId(), category, SetEntity.class)).thenReturn(set1);
        when(redisService.getCachedObject(set2.getId(), category, SetEntity.class)).thenReturn(set2);
        when(materialMapper.toSetDTO(any(SetEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        SetDTO resultDto1 = (SetDTO) result.get(0);
        SetDTO resultDto2 = (SetDTO) result.get(1);

        assertEquals(set1.getId(), resultDto1.getId());
        assertEquals(set1.getName(), resultDto1.getName());
        assertEquals(set2.getId(), resultDto2.getId());
        assertEquals(set2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(set1.getId(), category, SetEntity.class);
        verify(redisService, times(1)).getCachedObject(set2.getId(), category, SetEntity.class);
        verify(materialMapper, times(2)).toSetDTO(any(SetEntity.class));
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(SetEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Set_NotFoundInCache() {
        String category = "SET";
        SetEntity set1 = new SetEntity();
        set1.setId("1");
        set1.setName("name1");
        SetEntity set2 = new SetEntity();
        set2.setId("2");
        set2.setName("name2");

        SetDTO dto1 = new SetDTO();
        dto1.setId(set1.getId());
        dto1.setName(set1.getName());
        SetDTO dto2 = new SetDTO();
        dto2.setId(set2.getId());
        dto2.setName(set2.getName());

        when(setRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(set1, set2));
        when(redisService.getCachedObject(set1.getId(), category, SetEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(set2.getId(), category, SetEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(set1.getId(), category, SetEntity.class)).thenReturn(set1);
        when(materialEventService.reconstructMaterialEntity(set2.getId(), category, SetEntity.class)).thenReturn(set2);
        when(materialMapper.toSetDTO(set1)).thenReturn(dto1);
        when(materialMapper.toSetDTO(set2)).thenReturn(dto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        SetDTO resultDto1 = (SetDTO) result.get(0);
        SetDTO resultDto2 = (SetDTO) result.get(1);

        assertEquals(set1.getId(), resultDto1.getId());
        assertEquals(set1.getName(), resultDto1.getName());
        assertEquals(set2.getId(), resultDto2.getId());
        assertEquals(set2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(set1.getId(), category, SetEntity.class);
        verify(redisService, times(1)).getCachedObject(set2.getId(), category, SetEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(set1.getId(), category, SetEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(set2.getId(), category, SetEntity.class);
        verify(materialMapper, times(2)).toSetDTO(any(SetEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Unspecified_FoundInCache() {
        String category = "UNSPECIFIED";
        UnspecifiedEntity unspecified1 = new UnspecifiedEntity();
        unspecified1.setId("1");
        unspecified1.setName("name1");
        UnspecifiedEntity unspecified2 = new UnspecifiedEntity();
        unspecified2.setId("2");
        unspecified2.setName("name2");

        UnspecifiedDTO cachedDto1 = new UnspecifiedDTO();
        cachedDto1.setId(unspecified1.getId());
        cachedDto1.setName(unspecified1.getName());
        UnspecifiedDTO cachedDto2 = new UnspecifiedDTO();
        cachedDto2.setId(unspecified2.getId());
        cachedDto2.setName(unspecified2.getName());

        when(unspecifiedRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(unspecified1, unspecified2));
        when(redisService.getCachedObject(unspecified1.getId(), category, UnspecifiedEntity.class)).thenReturn(unspecified1);
        when(redisService.getCachedObject(unspecified2.getId(), category, UnspecifiedEntity.class)).thenReturn(unspecified2);
        when(materialMapper.toUnspecifiedDTO(any(UnspecifiedEntity.class))).thenReturn(cachedDto1).thenReturn(cachedDto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        UnspecifiedDTO resultDto1 = (UnspecifiedDTO) result.get(0);
        UnspecifiedDTO resultDto2 = (UnspecifiedDTO) result.get(1);

        assertEquals(unspecified1.getId(), resultDto1.getId());
        assertEquals(unspecified1.getName(), resultDto1.getName());
        assertEquals(unspecified2.getId(), resultDto2.getId());
        assertEquals(unspecified2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(unspecified1.getId(), category, UnspecifiedEntity.class);
        verify(redisService, times(1)).getCachedObject(unspecified2.getId(), category, UnspecifiedEntity.class);
        verify(materialMapper, times(2)).toUnspecifiedDTO(any(UnspecifiedEntity.class));
        verify(materialEventService, never()).reconstructMaterialEntity(anyString(), anyString(), eq(UnspecifiedEntity.class));
    }

    @Test
    public void testGetAllMaterialsByCategory_Unspecified_NotFoundInCache() {
        String category = "UNSPECIFIED";
        UnspecifiedEntity unspecified1 = new UnspecifiedEntity();
        unspecified1.setId("1");
        unspecified1.setName("name1");
        UnspecifiedEntity unspecified2 = new UnspecifiedEntity();
        unspecified2.setId("2");
        unspecified2.setName("name2");

        UnspecifiedDTO dto1 = new UnspecifiedDTO();
        dto1.setId(unspecified1.getId());
        dto1.setName(unspecified1.getName());
        UnspecifiedDTO dto2 = new UnspecifiedDTO();
        dto2.setId(unspecified2.getId());
        dto2.setName(unspecified2.getName());

        when(unspecifiedRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(List.of(unspecified1, unspecified2));
        when(redisService.getCachedObject(unspecified1.getId(), category, UnspecifiedEntity.class)).thenReturn(null);
        when(redisService.getCachedObject(unspecified2.getId(), category, UnspecifiedEntity.class)).thenReturn(null);
        when(materialEventService.reconstructMaterialEntity(unspecified1.getId(), category, UnspecifiedEntity.class)).thenReturn(unspecified1);
        when(materialEventService.reconstructMaterialEntity(unspecified2.getId(), category, UnspecifiedEntity.class)).thenReturn(unspecified2);
        when(materialMapper.toUnspecifiedDTO(unspecified1)).thenReturn(dto1);
        when(materialMapper.toUnspecifiedDTO(unspecified2)).thenReturn(dto2);

        List<MaterialDTO> result = materialQueryService.getAllMaterialsByCategory(category);

        UnspecifiedDTO resultDto1 = (UnspecifiedDTO) result.get(0);
        UnspecifiedDTO resultDto2 = (UnspecifiedDTO) result.get(1);

        assertEquals(unspecified1.getId(), resultDto1.getId());
        assertEquals(unspecified1.getName(), resultDto1.getName());
        assertEquals(unspecified2.getId(), resultDto2.getId());
        assertEquals(unspecified2.getName(), resultDto2.getName());

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(redisService, times(1)).getCachedObject(unspecified1.getId(), category, UnspecifiedEntity.class);
        verify(redisService, times(1)).getCachedObject(unspecified2.getId(), category, UnspecifiedEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(unspecified1.getId(), category, UnspecifiedEntity.class);
        verify(materialEventService, times(1)).reconstructMaterialEntity(unspecified2.getId(), category, UnspecifiedEntity.class);
        verify(materialMapper, times(2)).toUnspecifiedDTO(any(UnspecifiedEntity.class));
    }


    @Test
    public void testGetAllMaterialsByCategory_InvalidCategory() {
        String category = "INVALID";

        assertThrows(InvalidCategoryException.class, () -> materialQueryService.getAllMaterialsByCategory(category));

        verifyNoInteractions(redisService, materialEventService, materialMapper, fastenerRepository);
    }

}
