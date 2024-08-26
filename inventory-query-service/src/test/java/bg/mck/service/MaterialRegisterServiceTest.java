package bg.mck.service;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.*;
import bg.mck.repository.material.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialRegisterServiceTest {

    @Mock
    private FastenerRepository fastenerRepository;

    @Mock
    private GalvaniseRepository galvaniseRepository;

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

    @InjectMocks
    private MaterialRegisterService materialRegisterService;

    private CacheManager cacheManager;



    @Test
    public void testProcessingRegisterMaterial_Fastener() {
        RegisterFastenerEvent event = new RegisterFastenerEvent();
        event.setMaterialId(1L);
        event.setName("FastenerName");
        event.setCategory(MaterialType.FASTENERS.name());

        materialRegisterService.processingRegisterMaterial(event);

        verify(fastenerRepository, times(1)).save(any(FastenerEntity.class));
        verify(fastenerRepository).save(argThat(entity ->
                entity.getId().equals("1") &&
                        entity.getName().equals("FastenerName")
        ));
    }

    @Test
    public void testProcessingRegisterGalvanized() {
        RegisterGalvanizedEvent event = new RegisterGalvanizedEvent();
        event.setMaterialId(2L);
        event.setName("GalvanizedSheetName");
        event.setCategory(MaterialType.GALVANIZED_SHEET.name());

        materialRegisterService.processingRegisterGalvanized(event);

        verify(galvaniseRepository, times(1)).save(any(GalvanisedSheetEntity.class));
        verify(galvaniseRepository).save(argThat(entity ->
                entity.getId().equals("2") &&
                        entity.getName().equals("GalvanizedSheetName")
        ));
    }

    @Test
    public void testProcessingRegisterInsulation() {
        RegisterInsulationEvent event = new RegisterInsulationEvent();
        event.setMaterialId(3L);
        event.setName("InsulationName");
        event.setCategory(MaterialType.INSULATION.name());

        materialRegisterService.processingRegisterInsulation(event);

        verify(insulationRepository, times(1)).save(any(InsulationEntity.class));
        verify(insulationRepository).save(argThat(entity ->
                entity.getId().equals("3") &&
                        entity.getName().equals("InsulationName")
        ));
    }

    @Test
    public void testProcessingRegisterMetal() {
        RegisterMetalEvent event = new RegisterMetalEvent();
        event.setMaterialId(4L);
        event.setName("MetalName");
        event.setCategory(MaterialType.METAL.name());

        materialRegisterService.processingRegisterMetal(event);

        verify(metalRepository, times(1)).save(any(MetalEntity.class));
        verify(metalRepository).save(argThat(entity ->
                entity.getId().equals("4") &&
                        entity.getName().equals("MetalName")
        ));
    }

    @Test
    public void testProcessingRegisterPanel() {
        RegisterPanelEvent event = new RegisterPanelEvent();
        event.setMaterialId(5L);
        event.setName("PanelName");
        event.setCategory(MaterialType.PANELS.name());

        materialRegisterService.processingRegisterPanel(event);

        verify(panelRepository, times(1)).save(any(PanelEntity.class));
        verify(panelRepository).save(argThat(entity ->
                entity.getId().equals("5") &&
                        entity.getName().equals("PanelName")
        ));
    }

    @Test
    public void testProcessingRegisterRebar() {
        RegisterRebarEvent event = new RegisterRebarEvent();
        event.setMaterialId(6L);
        event.setName("RebarName");
        event.setCategory(MaterialType.REBAR.name());

        materialRegisterService.processingRegisterRebar(event);

        verify(rebarRepository, times(1)).save(any(RebarEntity.class));
        verify(rebarRepository).save(argThat(entity ->
                entity.getId().equals("6") &&
                        entity.getName().equals("RebarName")
        ));
    }

    @Test
    public void testProcessingRegisterSet() {
        RegisterSetEvent event = new RegisterSetEvent();
        event.setMaterialId(7L);
        event.setName("SetName");
        event.setCategory(MaterialType.SET.name());

        materialRegisterService.processingRegisterSet(event);

        verify(setRepository, times(1)).save(any(SetEntity.class));
        verify(setRepository).save(argThat(entity ->
                entity.getId().equals("7") &&
                        entity.getName().equals("SetName")
        ));
    }

    @Test
    public void testProcessingRegisterUnspecified() {
        RegisterUnspecifiedEvent event = new RegisterUnspecifiedEvent();
        event.setMaterialId(8L);
        event.setName("UnspecifiedName");
        event.setCategory(MaterialType.UNSPECIFIED.name());

        materialRegisterService.processingRegisterUnspecified(event);

        verify(unspecifiedRepository, times(1)).save(any(UnspecifiedEntity.class));
        verify(unspecifiedRepository).save(argThat(entity ->
                entity.getId().equals("8") &&
                        entity.getName().equals("UnspecifiedName")
        ));
    }
}
