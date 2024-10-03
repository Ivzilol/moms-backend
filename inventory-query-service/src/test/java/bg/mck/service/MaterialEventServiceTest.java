package bg.mck.service;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.EventType;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.MaterialType;
import bg.mck.enums.WeightUnits;
import bg.mck.events.material.*;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InvalidEventTypeException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.mapper.InventoryQueryUpdateMapper;
import bg.mck.repository.material.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;

import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialEventServiceTest {

    private final InventoryQueryUpdateMapper mapper = Mappers.getMapper(InventoryQueryUpdateMapper.class);

    @Mock
    private EventMaterialRepository eventMaterialRepository;
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
    private ObjectMapper objectMapper;
    @Mock
    private MaterialDeleteService materialDeleteService;
    @Mock
    private MaterialRegisterService materialRegisterService;
    @Mock
    private CacheManager cacheManager;


    private MaterialEventService materialEventService;

    private final String VALID_ID = "10";
    private final String INVALID_ID = "100";

    @BeforeEach
    void setUp(TestInfo testInfo) throws JsonProcessingException {


        materialEventService = spy(new MaterialEventService(
                eventMaterialRepository,
                fastenerRepository,
                galvanisedSheetRepository,
                insulationRepository,
                panelRepository,
                rebarRepository,
                setRepository,
                unspecifiedRepository,
                metalRepository,
                objectMapper,
                materialDeleteService,
                materialRegisterService,
                mapper,
                cacheManager
        ));



        lenient().when(fastenerRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(FastenerEntity.class)));
        lenient().when(fastenerRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        lenient().when(galvanisedSheetRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(GalvanisedSheetEntity.class)));
        lenient().when(galvanisedSheetRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        lenient().when(insulationRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(InsulationEntity.class)));
        lenient().when(insulationRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        lenient().when(panelRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(PanelEntity.class)));
        lenient().when(panelRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        lenient().when(rebarRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(RebarEntity.class)));
        lenient().when(rebarRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        lenient().when(setRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(SetEntity.class)));
        lenient().when(setRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        lenient().when(unspecifiedRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(UnspecifiedEntity.class)));
        lenient().when(unspecifiedRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        lenient().when(metalRepository.findById(VALID_ID)).thenReturn(Optional.of(mock(MetalEntity.class)));
        lenient().when(metalRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_FastenerRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterFastenerEvent fastenerEvent = new RegisterFastenerEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Fastener Name",
                "A description",
                "10mm",
                "50",
                LengthUnits.MM,
                "ISO123",
                "Class A",
                "Type B",
                "http://example.com/spec"
        );

        MaterialEvent<RegisterFastenerEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.FASTENERS, fastenerEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.FASTENERS.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterFastenerEvent> eventCaptor = ArgumentCaptor.forClass(RegisterFastenerEvent.class);
        verify(materialRegisterService).processingRegisterMaterial(eventCaptor.capture());

        RegisterFastenerEvent capturedEvent = eventCaptor.getValue();
        assertEquals(fastenerEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(fastenerEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(fastenerEvent.getName(), capturedEvent.getName());
        assertEquals(fastenerEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(fastenerEvent.getDiameter(), capturedEvent.getDiameter());
        assertEquals(fastenerEvent.getLength(), capturedEvent.getLength());
        assertEquals(fastenerEvent.getLengthUnit(), capturedEvent.getLengthUnit());
        assertEquals(fastenerEvent.getStandard(), capturedEvent.getStandard());
        assertEquals(fastenerEvent.getClazz(), capturedEvent.getClazz());
        assertEquals(fastenerEvent.getType(), capturedEvent.getType());
        assertEquals(fastenerEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_GalvanizedSheetRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterGalvanizedEvent galvanizedEvent = new RegisterGalvanizedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Galvanized Sheet Name",
                "Type A",
                "1000",
                LengthUnits.MM,
                "50",
                "A description",
                "http://example.com/galvanized-spec"
        );

        MaterialEvent<RegisterGalvanizedEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.GALVANIZED_SHEET, galvanizedEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.GALVANIZED_SHEET.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterGalvanizedEvent> eventCaptor = ArgumentCaptor.forClass(RegisterGalvanizedEvent.class);
        verify(materialRegisterService).processingRegisterGalvanized(eventCaptor.capture());

        RegisterGalvanizedEvent capturedEvent = eventCaptor.getValue();
        assertEquals(galvanizedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(galvanizedEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(galvanizedEvent.getName(), capturedEvent.getName());
        assertEquals(galvanizedEvent.getType(), capturedEvent.getType());
        assertEquals(galvanizedEvent.getMaxLength(), capturedEvent.getMaxLength());
        assertEquals(galvanizedEvent.getMaxLengthUnit(), capturedEvent.getMaxLengthUnit());
        assertEquals(galvanizedEvent.getNumberOfSheets(), capturedEvent.getNumberOfSheets());
        assertEquals(galvanizedEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(galvanizedEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_InsulationRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterInsulationEvent insulationEvent = new RegisterInsulationEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Insulation Name",
                "Type B",
                "50mm",
                LengthUnits.MM,
                "A description",
                "http://example.com/insulation-spec"
        );

        MaterialEvent<RegisterInsulationEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.INSULATION, insulationEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.INSULATION.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterInsulationEvent> eventCaptor = ArgumentCaptor.forClass(RegisterInsulationEvent.class);
        verify(materialRegisterService).processingRegisterInsulation(eventCaptor.capture());

        RegisterInsulationEvent capturedEvent = eventCaptor.getValue();
        assertEquals(insulationEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(insulationEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(insulationEvent.getName(), capturedEvent.getName());
        assertEquals(insulationEvent.getType(), capturedEvent.getType());
        assertEquals(insulationEvent.getThickness(), capturedEvent.getThickness());
        assertEquals(insulationEvent.getThicknessUnit(), capturedEvent.getThicknessUnit());
        assertEquals(insulationEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(insulationEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_PanelRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterPanelEvent panelEvent = new RegisterPanelEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Panel Name",
                "Type C",
                "Blue",
                "2000mm",
                LengthUnits.MM,
                "1000mm",
                LengthUnits.MM,
                "30mm",
                LengthUnits.MM,
                "10mm",
                LengthUnits.MM,
                "5mm",
                LengthUnits.MM,
                "A description",
                "http://example.com/panel-spec"
        );

        MaterialEvent<RegisterPanelEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.PANELS, panelEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.PANELS.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterPanelEvent> eventCaptor = ArgumentCaptor.forClass(RegisterPanelEvent.class);
        verify(materialRegisterService).processingRegisterPanel(eventCaptor.capture());

        RegisterPanelEvent capturedEvent = eventCaptor.getValue();
        assertEquals(panelEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(panelEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(panelEvent.getName(), capturedEvent.getName());
        assertEquals(panelEvent.getType(), capturedEvent.getType());
        assertEquals(panelEvent.getColor(), capturedEvent.getColor());
        assertEquals(panelEvent.getLength(), capturedEvent.getLength());
        assertEquals(panelEvent.getLengthUnit(), capturedEvent.getLengthUnit());
        assertEquals(panelEvent.getWidth(), capturedEvent.getWidth());
        assertEquals(panelEvent.getWidthUnit(), capturedEvent.getWidthUnit());
        assertEquals(panelEvent.getTotalThickness(), capturedEvent.getTotalThickness());
        assertEquals(panelEvent.getTotalThicknessUnit(), capturedEvent.getTotalThicknessUnit());
        assertEquals(panelEvent.getFrontSheetThickness(), capturedEvent.getFrontSheetThickness());
        assertEquals(panelEvent.getFrontSheetThicknessUnit(), capturedEvent.getFrontSheetThicknessUnit());
        assertEquals(panelEvent.getBackSheetThickness(), capturedEvent.getBackSheetThickness());
        assertEquals(panelEvent.getBackSheetThicknessUnit(), capturedEvent.getBackSheetThicknessUnit());
        assertEquals(panelEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(panelEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_RebarRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterRebarEvent rebarEvent = new RegisterRebarEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Rebar Name",
                "1000mm",
                LengthUnits.MM,
                "A description",
                "http://example.com/rebar-spec"
        );

        MaterialEvent<RegisterRebarEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.REBAR, rebarEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.REBAR.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterRebarEvent> eventCaptor = ArgumentCaptor.forClass(RegisterRebarEvent.class);
        verify(materialRegisterService).processingRegisterRebar(eventCaptor.capture());

        RegisterRebarEvent capturedEvent = eventCaptor.getValue();
        assertEquals(rebarEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(rebarEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(rebarEvent.getName(), capturedEvent.getName());
        assertEquals(rebarEvent.getMaxLength(), capturedEvent.getMaxLength());
        assertEquals(rebarEvent.getMaxLengthUnit(), capturedEvent.getMaxLengthUnit());
        assertEquals(rebarEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(rebarEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_SetRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterSetEvent setEvent = new RegisterSetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Set Name",
                "Red",
                "2000mm",
                LengthUnits.MM,
                "A description",
                "http://example.com/set-spec"
        );

        MaterialEvent<RegisterSetEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.SET, setEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.SET.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterSetEvent> eventCaptor = ArgumentCaptor.forClass(RegisterSetEvent.class);
        verify(materialRegisterService).processingRegisterSet(eventCaptor.capture());

        RegisterSetEvent capturedEvent = eventCaptor.getValue();
        assertEquals(setEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(setEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(setEvent.getName(), capturedEvent.getName());
        assertEquals(setEvent.getColor(), capturedEvent.getColor());
        assertEquals(setEvent.getMaxLength(), capturedEvent.getMaxLength());
        assertEquals(setEvent.getMaxLengthUnit(), capturedEvent.getMaxLengthUnit());
        assertEquals(setEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(setEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_MetalRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterMetalEvent metalEvent = new RegisterMetalEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Metal Name",
                "1000",
                WeightUnits.KG,
                "kind1",
                "A description",
                "http://example.com/metal-spec"
        );

        MaterialEvent<RegisterMetalEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.METAL, metalEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.METAL.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterMetalEvent> eventCaptor = ArgumentCaptor.forClass(RegisterMetalEvent.class);
        verify(materialRegisterService).processingRegisterMetal(eventCaptor.capture());

        RegisterMetalEvent capturedEvent = eventCaptor.getValue();
        assertEquals(metalEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(metalEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(metalEvent.getName(), capturedEvent.getName());
        assertEquals(metalEvent.getTotalWeight(), capturedEvent.getTotalWeight());
        assertEquals(metalEvent.getTotalWeightUnit(), capturedEvent.getTotalWeightUnit());
        assertEquals(metalEvent.getKind(), capturedEvent.getKind());
        assertEquals(metalEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(metalEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }
    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_UnspecifiedRegisteredEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        RegisterUnspecifiedEvent unspecifiedEvent = new RegisterUnspecifiedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "category1",
                "Unspecified Name",
                "A description",
                "http://example.com/unspecified-spec"
        );

        MaterialEvent<RegisterUnspecifiedEvent> materialEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.UNSPECIFIED, unspecifiedEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemRegistered.name();
        String materialType = MaterialType.UNSPECIFIED.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<RegisterUnspecifiedEvent> eventCaptor = ArgumentCaptor.forClass(RegisterUnspecifiedEvent.class);
        verify(materialRegisterService).processingRegisterUnspecified(eventCaptor.capture());

        RegisterUnspecifiedEvent capturedEvent = eventCaptor.getValue();
        assertEquals(unspecifiedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(unspecifiedEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(unspecifiedEvent.getName(), capturedEvent.getName());
        assertEquals(unspecifiedEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(unspecifiedEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_FastenerUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        UpdateFastenerEvent fastenerEvent = new UpdateFastenerEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Updated Fastener Name",
                "Updated description",
                "14mm",
                "75",
                LengthUnits.MM,
                "ISO456",
                "Class C",
                "Type D",
                "http://example.com/spec-updated",
                "category1"
        );

        MaterialEvent<UpdateFastenerEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.FASTENERS, fastenerEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.FASTENERS.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<MaterialEvent<UpdateFastenerEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdateFastenerEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdateFastenerEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(fastenerEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(fastenerEvent.getName(), capturedEvent.getName());
        assertEquals(fastenerEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(fastenerEvent.getDiameter(), capturedEvent.getDiameter());
        assertEquals(fastenerEvent.getLength(), capturedEvent.getLength());
        assertEquals(fastenerEvent.getLengthUnit(), capturedEvent.getLengthUnit());
        assertEquals(fastenerEvent.getStandard(), capturedEvent.getStandard());
        assertEquals(fastenerEvent.getClazz(), capturedEvent.getClazz());
        assertEquals(fastenerEvent.getType(), capturedEvent.getType());
        assertEquals(fastenerEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
        assertEquals(fastenerEvent.getCategory(), capturedEvent.getCategory());

        // Verify that the cache is evicted
        verify(materialEventService).evictCache(materialType, fastenerEvent.getName());

        // Verify that the entity is reconstructed
        verify(materialEventService).reconstructMaterialEntity(fastenerEvent.getMaterialId().toString(), materialType, FastenerEntity.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_GalvanizedSheetUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        UpdateGalvanizedSheetEvent galvanizedEvent = new UpdateGalvanizedSheetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "category1",
                "Updated Galvanized Sheet Name",
                "Type A",
                "1200",
                LengthUnits.MM,
                "60",
                "Updated description",
                "http://example.com/galvanized-spec-updated"

        );

        MaterialEvent<UpdateGalvanizedSheetEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.GALVANIZED_SHEET, galvanizedEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.GALVANIZED_SHEET.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<MaterialEvent<UpdateGalvanizedSheetEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdateGalvanizedSheetEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdateGalvanizedSheetEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(galvanizedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(galvanizedEvent.getName(), capturedEvent.getName());
        assertEquals(galvanizedEvent.getType(), capturedEvent.getType());
        assertEquals(galvanizedEvent.getMaxLength(), capturedEvent.getMaxLength());
        assertEquals(galvanizedEvent.getMaxLengthUnit(), capturedEvent.getMaxLengthUnit());
        assertEquals(galvanizedEvent.getNumberOfSheets(), capturedEvent.getNumberOfSheets());
        assertEquals(galvanizedEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(galvanizedEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());
        assertEquals(galvanizedEvent.getCategory(), capturedEvent.getCategory());

        // Verify that the cache is evicted
        verify(materialEventService).evictCache(materialType, galvanizedEvent.getName());

        // Verify that the entity is reconstructed
        verify(materialEventService).reconstructMaterialEntity(galvanizedEvent.getMaterialId().toString(), materialType, GalvanisedSheetEntity.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_InsulationUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        // Arrange
        UpdateInsulationEvent insulationEvent = new UpdateInsulationEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "category1",
                "Updated Insulation Name",
                "Insulation",
                "Type B",
                "60mm",
                LengthUnits.MM,
                "Updated description",
                "http://example.com/insulation-spec-updated"
        );

        MaterialEvent<UpdateInsulationEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.INSULATION, insulationEvent);

        // Register the JavaTimeModule to handle LocalDateTime serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Serialize the MaterialEvent object to JSON
        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.INSULATION.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        // Act
        materialEventService.processMaterialEvent(data, eventType, materialType);

        // Assert
        ArgumentCaptor<MaterialEvent<UpdateInsulationEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdateInsulationEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdateInsulationEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(insulationEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(insulationEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(insulationEvent.getName(), capturedEvent.getName());
        assertEquals(insulationEvent.getMaterialType(), capturedEvent.getMaterialType());
        assertEquals(insulationEvent.getType(), capturedEvent.getType());
        assertEquals(insulationEvent.getThickness(), capturedEvent.getThickness());
        assertEquals(insulationEvent.getThicknessUnit(), capturedEvent.getThicknessUnit());
        assertEquals(insulationEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(insulationEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());

        // Verify that the cache is evicted
        verify(materialEventService).evictCache(materialType, insulationEvent.getName());

        // Verify that the entity is reconstructed
        verify(materialEventService).reconstructMaterialEntity(insulationEvent.getMaterialId().toString(), materialType, InsulationEntity.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_PanelUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        UpdatePanelEvent panelEvent = new UpdatePanelEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "category1",
                "Updated Panel Name",
                "MaterialType1",
                "Type C",
                "Red",
                "2200mm",
                LengthUnits.MM,
                "1100mm",
                LengthUnits.MM,
                "35mm",
                LengthUnits.MM,
                "15mm",
                LengthUnits.MM,
                "10mm",
                LengthUnits.MM,
                "Updated description",
                "http://example.com/panel-spec-updated"
        );

        MaterialEvent<UpdatePanelEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.PANELS, panelEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.PANELS.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<UpdatePanelEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdatePanelEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdatePanelEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(panelEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(panelEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(panelEvent.getName(), capturedEvent.getName());
        assertEquals(panelEvent.getMaterialType(), capturedEvent.getMaterialType());
        assertEquals(panelEvent.getType(), capturedEvent.getType());
        assertEquals(panelEvent.getColor(), capturedEvent.getColor());
        assertEquals(panelEvent.getLength(), capturedEvent.getLength());
        assertEquals(panelEvent.getLengthUnit(), capturedEvent.getLengthUnit());
        assertEquals(panelEvent.getWidth(), capturedEvent.getWidth());
        assertEquals(panelEvent.getWidthUnit(), capturedEvent.getWidthUnit());
        assertEquals(panelEvent.getTotalThickness(), capturedEvent.getTotalThickness());
        assertEquals(panelEvent.getTotalThicknessUnit(), capturedEvent.getTotalThicknessUnit());
        assertEquals(panelEvent.getFrontSheetThickness(), capturedEvent.getFrontSheetThickness());
        assertEquals(panelEvent.getFrontSheetThicknessUnit(), capturedEvent.getFrontSheetThicknessUnit());
        assertEquals(panelEvent.getBackSheetThickness(), capturedEvent.getBackSheetThickness());
        assertEquals(panelEvent.getBackSheetThicknessUnit(), capturedEvent.getBackSheetThicknessUnit());
        assertEquals(panelEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(panelEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());

        verify(materialEventService).evictCache(materialType, panelEvent.getName());
        verify(materialEventService).reconstructMaterialEntity(panelEvent.getMaterialId().toString(), materialType, PanelEntity.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_RebarUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        UpdateRebarEvent rebarEvent = new UpdateRebarEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated
        );
        rebarEvent.setCategory("category1")
                .setName("Updated Rebar Name")
                .setMaterialType("MaterialType1")
                .setMaxLength("500mm")
                .setMaxLengthUnit(LengthUnits.MM)
                .setDescription("Updated description")
                .setSpecificationFileUrl("http://example.com/rebar-spec-updated");

        MaterialEvent<UpdateRebarEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.REBAR, rebarEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.REBAR.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<UpdateRebarEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdateRebarEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdateRebarEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(rebarEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(rebarEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(rebarEvent.getName(), capturedEvent.getName());
        assertEquals(rebarEvent.getMaterialType(), capturedEvent.getMaterialType());
        assertEquals(rebarEvent.getMaxLength(), capturedEvent.getMaxLength());
        assertEquals(rebarEvent.getMaxLengthUnit(), capturedEvent.getMaxLengthUnit());
        assertEquals(rebarEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(rebarEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());

        verify(materialEventService).evictCache(materialType, rebarEvent.getName());
        verify(materialEventService).reconstructMaterialEntity(rebarEvent.getMaterialId().toString(), materialType, RebarEntity.class);
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_MetalUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        UpdateMetalEvent metalEvent = new UpdateMetalEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated
        );
        metalEvent.setCategory("category1")
                .setName("Updated Metal Name")
                .setMaterialType("MaterialType1")
                .setTotalWeight("500kg")
                .setTotalWeightUnit(WeightUnits.KG)
                .setKind("Steel")
                .setDescription("Updated description")
                .setSpecificationFileUrl("http://example.com/metal-spec-updated");

        MaterialEvent<UpdateMetalEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.METAL, metalEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.METAL.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<UpdateMetalEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdateMetalEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdateMetalEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(metalEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(metalEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(metalEvent.getName(), capturedEvent.getName());
        assertEquals(metalEvent.getMaterialType(), capturedEvent.getMaterialType());
        assertEquals(metalEvent.getTotalWeight(), capturedEvent.getTotalWeight());
        assertEquals(metalEvent.getTotalWeightUnit(), capturedEvent.getTotalWeightUnit());
        assertEquals(metalEvent.getKind(), capturedEvent.getKind());
        assertEquals(metalEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(metalEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());

        verify(materialEventService).evictCache(materialType, metalEvent.getName());
        verify(materialEventService).reconstructMaterialEntity(metalEvent.getMaterialId().toString(), materialType, MetalEntity.class);
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_SetUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        UpdateSetEvent setEvent = new UpdateSetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated
        );
        setEvent.setCategory("category1")
                .setName("Updated Set Name")
                .setMaterialType("MaterialType1")
                .setColor("Blue")
                .setMaxLength("1000mm")
                .setMaxLengthUnit(LengthUnits.MM)
                .setDescription("Updated description")
                .setSpecificationFileUrl("http://example.com/set-spec-updated");

        MaterialEvent<UpdateSetEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.SET, setEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.SET.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<UpdateSetEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdateSetEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdateSetEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(setEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(setEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(setEvent.getName(), capturedEvent.getName());
        assertEquals(setEvent.getMaterialType(), capturedEvent.getMaterialType());
        assertEquals(setEvent.getColor(), capturedEvent.getColor());
        assertEquals(setEvent.getMaxLength(), capturedEvent.getMaxLength());
        assertEquals(setEvent.getMaxLengthUnit(), capturedEvent.getMaxLengthUnit());
        assertEquals(setEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(setEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());

        verify(materialEventService).evictCache(materialType, setEvent.getName());
        verify(materialEventService).reconstructMaterialEntity(setEvent.getMaterialId().toString(), materialType, SetEntity.class);
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_UnspecifiedUpdatedEvent_ShouldInvokeProcessingAndCaptureEvent() throws JsonProcessingException {
        UpdateUnspecifiedEvent unspecifiedEvent = new UpdateUnspecifiedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated
        );
        unspecifiedEvent.setCategory("category1")
                .setName("Updated Unspecified Name")
                .setMaterialType("MaterialType1")
                .setDescription("Updated description")
                .setSpecificationFileUrl("http://example.com/unspecified-spec-updated");

        MaterialEvent<UpdateUnspecifiedEvent> materialEvent = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.UNSPECIFIED, unspecifiedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemUpdated.name();
        String materialType = MaterialType.UNSPECIFIED.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<UpdateUnspecifiedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<UpdateUnspecifiedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        UpdateUnspecifiedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(unspecifiedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(unspecifiedEvent.getCategory(), capturedEvent.getCategory());
        assertEquals(unspecifiedEvent.getName(), capturedEvent.getName());
        assertEquals(unspecifiedEvent.getMaterialType(), capturedEvent.getMaterialType());
        assertEquals(unspecifiedEvent.getDescription(), capturedEvent.getDescription());
        assertEquals(unspecifiedEvent.getSpecificationFileUrl(), capturedEvent.getSpecificationFileUrl());

        verify(materialEventService).evictCache(materialType, unspecifiedEvent.getName());
        verify(materialEventService).reconstructMaterialEntity(unspecifiedEvent.getMaterialId().toString(), materialType, UnspecifiedEntity.class);
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_MetalDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Metal Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.METAL, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.METAL.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_SetDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Set Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.SET, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.SET.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_UnspecifiedDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Unspecified Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.UNSPECIFIED, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.UNSPECIFIED.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_FastenersDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Fasteners Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.FASTENERS, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.FASTENERS.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_GalvanizedSheetDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Galvanized Sheet Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.GALVANIZED_SHEET, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.GALVANIZED_SHEET.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_InsulationDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Insulation Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.INSULATION, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.INSULATION.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_PanelDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Panel Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.PANELS, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.PANELS.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_RebarDeletedEvent_ShouldInvokeDeletionAndCaptureEvent() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemDeleted,
                "Deleted Rebar Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.REBAR, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.REBAR.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);
        when(eventMaterialRepository.save(any(MaterialEvent.class))).thenReturn(materialEvent);

        materialEventService.processMaterialEvent(data, eventType, materialType);

        ArgumentCaptor<MaterialEvent<MaterialDeletedEvent>> materialEventCaptor = ArgumentCaptor.forClass(MaterialEvent.class);
        verify(eventMaterialRepository).save(materialEventCaptor.capture());

        MaterialEvent<MaterialDeletedEvent> capturedMaterialEvent = materialEventCaptor.getValue();
        verify(eventMaterialRepository).save(capturedMaterialEvent);

        assertEquals(materialEvent.getEvent(), capturedMaterialEvent.getEvent());
        assertEquals(materialEvent.getEventType(), capturedMaterialEvent.getEventType());
        assertEquals(materialEvent.getMaterialType(), capturedMaterialEvent.getMaterialType());

        MaterialDeletedEvent capturedEvent = capturedMaterialEvent.getEvent();
        assertEquals(deletedEvent.getMaterialId(), capturedEvent.getMaterialId());
        assertEquals(deletedEvent.getName(), capturedEvent.getName());

        verify(materialDeleteService).deleteMaterialByIdAndCategory(deletedEvent.getMaterialId().toString(), materialType);
        verify(materialEventService).evictCache(materialType, deletedEvent.getName());
    }


    @Test
    @SuppressWarnings("unchecked")
    void testProcessMaterialEvent_RebarDeletedEvent_InvalidRebarID_ShouldThrowInvalidCategoryException() throws JsonProcessingException {
        MaterialDeletedEvent deletedEvent = new MaterialDeletedEvent(
                Long.valueOf(INVALID_ID),
                EventType.ItemDeleted,
                "Deleted Rebar Name",
                "category"
        );

        MaterialEvent<MaterialDeletedEvent> materialEvent = new MaterialEvent<>(EventType.ItemDeleted, MaterialType.REBAR, deletedEvent);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String data = mapper.writeValueAsString(materialEvent);
        String eventType = EventType.ItemDeleted.name();
        String materialType = MaterialType.REBAR.name();

        when(objectMapper.readValue(eq(data), any(TypeReference.class))).thenReturn(materialEvent);

        assertThrows(InventoryItemNotFoundException.class,
                () -> materialEventService.processMaterialEvent(data, eventType, materialType));

    }

    @Test
    void testProcessMaterialEvent_InvalidEventType_ShouldThrowInvalidEventTypeException() {
        String data = "some-data";
        String eventType = "InvalidEvent";
        String materialType = MaterialType.FASTENERS.name();

        assertThrows(InvalidEventTypeException.class, () ->
                materialEventService.processMaterialEvent(data, eventType, materialType)
        );
    }

    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_Fasteners_ShouldApplyEventsCorrectly() {
        RegisterFastenerEvent registerEvent = new RegisterFastenerEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Fastener Category",
                "Fastener Name",
                "Initial description",
                "10mm",
                "50",
                LengthUnits.MM,
                "ISO123",
                "Class A",
                "Type B",
                "http://example.com/spec"
        );

        UpdateFastenerEvent updateEvent1 = new UpdateFastenerEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Updated Fastener Name 1",
                "Updated description 1",
                "12mm",
                "60",
                LengthUnits.MM,
                "ISO124",
                "Class B",
                "Type C",
                "http://example.com/spec-updated-1",
                "Fastener Category"
        );

        UpdateFastenerEvent updateEvent2 = new UpdateFastenerEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Updated Fastener Name 2",
                "Updated description 2",
                "14mm",
                "75",
                LengthUnits.MM,
                "ISO125",
                "Class C",
                "Type D",
                "http://example.com/spec-updated-2",
                "Fastener Category"
        );

        MaterialEvent<RegisterFastenerEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.FASTENERS, registerEvent);
        MaterialEvent<UpdateFastenerEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.FASTENERS, updateEvent1);
        MaterialEvent<UpdateFastenerEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.FASTENERS, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.FASTENERS.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.FASTENERS.name(), FastenerEntity.class);

        ArgumentCaptor<FastenerEntity> fastenerCaptor = ArgumentCaptor.forClass(FastenerEntity.class);
        verify(fastenerRepository).save(fastenerCaptor.capture());

        FastenerEntity capturedFastener = fastenerCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedFastener.getName());
        assertEquals(updateEvent2.getDescription(), capturedFastener.getDescription());
        assertEquals(updateEvent2.getDiameter(), capturedFastener.getDiameter());
        assertEquals(updateEvent2.getLength(), capturedFastener.getLength());
        assertEquals(updateEvent2.getLengthUnit(), capturedFastener.getLengthUnit());
        assertEquals(updateEvent2.getStandard(), capturedFastener.getStandard());
        assertEquals(updateEvent2.getClazz(), capturedFastener.getClazz());
        assertEquals(updateEvent2.getType(), capturedFastener.getType());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedFastener.getSpecificationFileUrl());

    }

    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_GalvanizedSheet_ShouldApplyEventsCorrectly() {
        RegisterGalvanizedEvent registerEvent = new RegisterGalvanizedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Galvanized Category",
                "Galvanized Sheet Name",
                "Type A",
                "1000mm",
                LengthUnits.MM,
                "50",
                "Initial description",
                "http://example.com/spec"
        );

        UpdateGalvanizedSheetEvent updateEvent1 = new UpdateGalvanizedSheetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Galvanized Category",
                "Updated Galvanized Sheet Name 1",
                "Type B",
                "1200mm",
                LengthUnits.MM,
                "60",
                "Updated description 1",
                "http://example.com/spec-updated-1"
        );

        UpdateGalvanizedSheetEvent updateEvent2 = new UpdateGalvanizedSheetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Galvanized Category",
                "Updated Galvanized Sheet Name 2",
                "Type C",
                "1400mm",
                LengthUnits.MM,
                "70",
                "Updated description 2",
                "http://example.com/spec-updated-2"
        );

        MaterialEvent<RegisterGalvanizedEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.GALVANIZED_SHEET, registerEvent);
        MaterialEvent<UpdateGalvanizedSheetEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.GALVANIZED_SHEET, updateEvent1);
        MaterialEvent<UpdateGalvanizedSheetEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.GALVANIZED_SHEET, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.GALVANIZED_SHEET.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.GALVANIZED_SHEET.name(), GalvanisedSheetEntity.class);

        ArgumentCaptor<GalvanisedSheetEntity> galvanizedSheetCaptor = ArgumentCaptor.forClass(GalvanisedSheetEntity.class);
        verify(galvanisedSheetRepository).save(galvanizedSheetCaptor.capture());

        GalvanisedSheetEntity capturedGalvanizedSheet = galvanizedSheetCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedGalvanizedSheet.getName());
        assertEquals(updateEvent2.getType(), capturedGalvanizedSheet.getType());
        assertEquals(updateEvent2.getMaxLength(), capturedGalvanizedSheet.getMaxLength());
        assertEquals(updateEvent2.getMaxLengthUnit(), capturedGalvanizedSheet.getMaxLengthUnit());
        assertEquals(updateEvent2.getNumberOfSheets(), capturedGalvanizedSheet.getNumberOfSheets());
        assertEquals(updateEvent2.getDescription(), capturedGalvanizedSheet.getDescription());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedGalvanizedSheet.getSpecificationFileUrl());

        // Verify that the object is cached in Redis
    }

    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_Insulation_ShouldApplyEventsCorrectly() {
        RegisterInsulationEvent registerEvent = new RegisterInsulationEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Insulation Category",
                "Insulation Name",
                "Type A",
                "50mm",
                LengthUnits.MM,
                "Initial description",
                "http://example.com/spec"
        );

        UpdateInsulationEvent updateEvent1 = new UpdateInsulationEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Insulation Category",
                "Updated Insulation Name 1",
                "Foam",
                "Type B",
                "75mm",
                LengthUnits.MM,
                "Updated description 1",
                "http://example.com/spec-updated-1"
        );

        UpdateInsulationEvent updateEvent2 = new UpdateInsulationEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Insulation Category",
                "Updated Insulation Name 2",
                "Foam",
                "Type C",
                "100mm",
                LengthUnits.MM,
                "Updated description 2",
                "http://example.com/spec-updated-2"
        );

        MaterialEvent<RegisterInsulationEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.INSULATION, registerEvent);
        MaterialEvent<UpdateInsulationEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.INSULATION, updateEvent1);
        MaterialEvent<UpdateInsulationEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.INSULATION, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.INSULATION.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.INSULATION.name(), InsulationEntity.class);

        ArgumentCaptor<InsulationEntity> insulationCaptor = ArgumentCaptor.forClass(InsulationEntity.class);
        verify(insulationRepository).save(insulationCaptor.capture());

        InsulationEntity capturedInsulation = insulationCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedInsulation.getName());
        assertEquals(updateEvent2.getDescription(), capturedInsulation.getDescription());
        assertEquals(updateEvent2.getThickness(), capturedInsulation.getThickness());
        assertEquals(updateEvent2.getThicknessUnit(), capturedInsulation.getThicknessUnit());
        assertEquals(updateEvent2.getType(), capturedInsulation.getType());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedInsulation.getSpecificationFileUrl());

        // Verify that the object is cached in Redis
    }

    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_Rebar_ShouldApplyEventsCorrectly() {
        RegisterRebarEvent registerEvent = new RegisterRebarEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Rebar Category",
                "Rebar Name",
                "50m",
                LengthUnits.M,
                "Initial description",
                "http://example.com/spec"
        );

        UpdateRebarEvent updateEvent1 = new UpdateRebarEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Rebar Category",
                "Updated Rebar Name 1",
                "Steel",
                "60m",
                LengthUnits.M,
                "Updated description 1",
                "http://example.com/spec-updated-1"
        );

        UpdateRebarEvent updateEvent2 = new UpdateRebarEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Rebar Category",
                "Updated Rebar Name 2",
                "Steel",
                "75m",
                LengthUnits.M,
                "Updated description 2",
                "http://example.com/spec-updated-2"
        );

        MaterialEvent<RegisterRebarEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.REBAR, registerEvent);
        MaterialEvent<UpdateRebarEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.REBAR, updateEvent1);
        MaterialEvent<UpdateRebarEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.REBAR, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.REBAR.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.REBAR.name(), RebarEntity.class);

        ArgumentCaptor<RebarEntity> rebarCaptor = ArgumentCaptor.forClass(RebarEntity.class);
        verify(rebarRepository).save(rebarCaptor.capture());

        RebarEntity capturedRebar = rebarCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedRebar.getName());
        assertEquals(updateEvent2.getDescription(), capturedRebar.getDescription());
        assertEquals(updateEvent2.getMaxLength(), capturedRebar.getMaxLength());
        assertEquals(updateEvent2.getMaxLengthUnit(), capturedRebar.getMaxLengthUnit());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedRebar.getSpecificationFileUrl());

        // Verify that the object is cached in Redis
    }

    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_Metal_ShouldApplyEventsCorrectly() {
        RegisterMetalEvent registerEvent = new RegisterMetalEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Metal Category",
                "Metal Name",
                "500kg",
                WeightUnits.KG,
                "kind1",
                "Initial description",
                "http://example.com/spec"
        );

        UpdateMetalEvent updateEvent1 = new UpdateMetalEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Metal Category",
                "Updated Metal Name 1",
                "Steel1",
                "600kg",
                WeightUnits.KG,
                "kind2",
                "Updated description 1",
                "http://example.com/spec-updated-1"
        );

        UpdateMetalEvent updateEvent2 = new UpdateMetalEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Metal Category",
                "Updated Metal Name 2",
                "Steel2",
                "750kg",
                WeightUnits.KG,
                "kind3",
                "Updated description 2",
                "http://example.com/spec-updated-2"
        );

        MaterialEvent<RegisterMetalEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.METAL, registerEvent);
        MaterialEvent<UpdateMetalEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.METAL, updateEvent1);
        MaterialEvent<UpdateMetalEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.METAL, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.METAL.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.METAL.name(), MetalEntity.class);

        ArgumentCaptor<MetalEntity> metalCaptor = ArgumentCaptor.forClass(MetalEntity.class);
        verify(metalRepository).save(metalCaptor.capture());

        MetalEntity capturedMetal = metalCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedMetal.getName());
        assertEquals(updateEvent2.getDescription(), capturedMetal.getDescription());
        assertEquals(updateEvent2.getTotalWeight(), capturedMetal.getTotalWeight());
        assertEquals(updateEvent2.getTotalWeightUnit(), capturedMetal.getTotalWeightUnit());
        assertEquals(updateEvent2.getKind(), capturedMetal.getKind());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedMetal.getSpecificationFileUrl());

        // Verify that the object is cached in Redis
    }


    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_Panel_ShouldApplyEventsCorrectly() {
        RegisterPanelEvent registerEvent = new RegisterPanelEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Panel Category",
                "Panel Name",
                "Type A",
                "Blue",
                "100m",
                LengthUnits.M,
                "50m",
                LengthUnits.M,
                "200mm",
                LengthUnits.MM,
                "1mm",
                LengthUnits.MM,
                "1mm",
                LengthUnits.MM,
                "Initial description",
                "http://example.com/spec"
        );

        UpdatePanelEvent updateEvent1 = new UpdatePanelEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Panel Category",
                "Updated Panel Name 1",
                "MaterialType A",
                "Type B",
                "Red",
                "120m",
                LengthUnits.M,
                "60m",
                LengthUnits.M,
                "250mm",
                LengthUnits.MM,
                "1.5mm",
                LengthUnits.MM,
                "1.5mm",
                LengthUnits.MM,
                "Updated description 1",
                "http://example.com/spec-updated-1"
        );

        UpdatePanelEvent updateEvent2 = new UpdatePanelEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Panel Category",
                "Updated Panel Name 2",
                "MaterialType B",
                "Type C",
                "Green",
                "150m",
                LengthUnits.M,
                "70m",
                LengthUnits.M,
                "300mm",
                LengthUnits.MM,
                "2mm",
                LengthUnits.MM,
                "2mm",
                LengthUnits.MM,
                "Updated description 2",
                "http://example.com/spec-updated-2"
        );

        MaterialEvent<RegisterPanelEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.PANELS, registerEvent);
        MaterialEvent<UpdatePanelEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.PANELS, updateEvent1);
        MaterialEvent<UpdatePanelEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.PANELS, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.PANELS.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.PANELS.name(), PanelEntity.class);

        ArgumentCaptor<PanelEntity> panelCaptor = ArgumentCaptor.forClass(PanelEntity.class);
        verify(panelRepository).save(panelCaptor.capture());

        PanelEntity capturedPanel = panelCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedPanel.getName());
        assertEquals(updateEvent2.getDescription(), capturedPanel.getDescription());
        assertEquals(updateEvent2.getType(), capturedPanel.getType());
        assertEquals(updateEvent2.getColor(), capturedPanel.getColor());
        assertEquals(updateEvent2.getLength(), capturedPanel.getLength());
        assertEquals(updateEvent2.getLengthUnit(), capturedPanel.getLengthUnit());
        assertEquals(updateEvent2.getWidth(), capturedPanel.getWidth());
        assertEquals(updateEvent2.getWidthUnit(), capturedPanel.getWidthUnit());
        assertEquals(updateEvent2.getTotalThickness(), capturedPanel.getTotalThickness());
        assertEquals(updateEvent2.getTotalThicknessUnit(), capturedPanel.getTotalThicknessUnit());
        assertEquals(updateEvent2.getFrontSheetThickness(), capturedPanel.getFrontSheetThickness());
        assertEquals(updateEvent2.getFrontSheetThicknessUnit(), capturedPanel.getFrontSheetThicknessUnit());
        assertEquals(updateEvent2.getBackSheetThickness(), capturedPanel.getBackSheetThickness());
        assertEquals(updateEvent2.getBackSheetThicknessUnit(), capturedPanel.getBackSheetThicknessUnit());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedPanel.getSpecificationFileUrl());

        // Verify that the object is cached in Redis
    }

    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_Set_ShouldApplyEventsCorrectly() {
        RegisterSetEvent registerEvent = new RegisterSetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Set Category",
                "Set Name",
                "Red",
                "50m",
                LengthUnits.M,
                "Initial description",
                "http://example.com/spec"
        );

        UpdateSetEvent updateEvent1 = new UpdateSetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Set Category",
                "Updated Set Name 1",
                "Type B",
                "Blue",
                "60m",
                LengthUnits.M,
                "Updated description 1",
                "http://example.com/spec-updated-1"
        );

        UpdateSetEvent updateEvent2 = new UpdateSetEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Set Category",
                "Updated Set Name 2",
                "Type C",
                "Green",
                "75m",
                LengthUnits.M,
                "Updated description 2",
                "http://example.com/spec-updated-2"
        );

        MaterialEvent<RegisterSetEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.SET, registerEvent);
        MaterialEvent<UpdateSetEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.SET, updateEvent1);
        MaterialEvent<UpdateSetEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.SET, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.SET.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.SET.name(), SetEntity.class);

        ArgumentCaptor<SetEntity> setCaptor = ArgumentCaptor.forClass(SetEntity.class);
        verify(setRepository).save(setCaptor.capture());

        SetEntity capturedSet = setCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedSet.getName());
        assertEquals(updateEvent2.getDescription(), capturedSet.getDescription());
        assertEquals(updateEvent2.getColor(), capturedSet.getColor());
        assertEquals(updateEvent2.getMaxLength(), capturedSet.getMaxLength());
        assertEquals(updateEvent2.getMaxLengthUnit(), capturedSet.getMaxLengthUnit());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedSet.getSpecificationFileUrl());

        // Verify that the object is cached in Redis
    }

    @Test
    @SuppressWarnings("unchecked")
    void testReconstructMaterialEntity_Unspecified_ShouldApplyEventsCorrectly() {
        RegisterUnspecifiedEvent registerEvent = new RegisterUnspecifiedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemRegistered,
                "Unspecified Category",
                "Unspecified Name",
                "Initial description",
                "http://example.com/spec"
        );

        UpdateUnspecifiedEvent updateEvent1 = new UpdateUnspecifiedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Unspecified Category",
                "Updated Unspecified Name 1",
                "Material Y",
                "Updated description 1",
                "http://example.com/spec-updated-1"
        );

        UpdateUnspecifiedEvent updateEvent2 = new UpdateUnspecifiedEvent(
                Long.valueOf(VALID_ID),
                EventType.ItemUpdated,
                "Unspecified Category",
                "Updated Unspecified Name 2",
                "Material Z",
                "Updated description 2",
                "http://example.com/spec-updated-2"
        );

        MaterialEvent<RegisterUnspecifiedEvent> materialRegisterEvent = new MaterialEvent<>(EventType.ItemRegistered, MaterialType.UNSPECIFIED, registerEvent);
        MaterialEvent<UpdateUnspecifiedEvent> materialUpdateEvent1 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.UNSPECIFIED, updateEvent1);
        MaterialEvent<UpdateUnspecifiedEvent> materialUpdateEvent2 = new MaterialEvent<>(EventType.ItemUpdated, MaterialType.UNSPECIFIED, updateEvent2);

        when(eventMaterialRepository.findEventsByMaterialIdAndCategoryOrderByEventLocalDateTimeAsc(Long.valueOf(VALID_ID), MaterialType.UNSPECIFIED.name()))
                .thenReturn(List.of(materialRegisterEvent, materialUpdateEvent1, materialUpdateEvent2));

        materialEventService.reconstructMaterialEntity(VALID_ID, MaterialType.UNSPECIFIED.name(), UnspecifiedEntity.class);

        ArgumentCaptor<UnspecifiedEntity> unspecifiedCaptor = ArgumentCaptor.forClass(UnspecifiedEntity.class);
        verify(unspecifiedRepository).save(unspecifiedCaptor.capture());

        UnspecifiedEntity capturedUnspecified = unspecifiedCaptor.getValue();
        assertEquals(updateEvent2.getName(), capturedUnspecified.getName());
        assertEquals(updateEvent2.getDescription(), capturedUnspecified.getDescription());
        assertEquals(updateEvent2.getSpecificationFileUrl(), capturedUnspecified.getSpecificationFileUrl());

        // Verify that the object is cached in Redis
    }

    @Test
    void testReconstructMaterialEntity_InvalidUserID_ShouldThrowInventoryItemNotFoundException() {

        // Act & Assert
        assertThrows(InventoryItemNotFoundException.class, () ->
                materialEventService.reconstructMaterialEntity(INVALID_ID, MaterialType.FASTENERS.name(), FastenerEntity.class)
        );


        verify(fastenerRepository, never()).save(any(FastenerEntity.class));
    }

    @Test
    void testReconstructMaterialEntity_InvalidCategory_ShouldThrowInvalidCategoryException() {

        // Act & Assert
        assertThrows(InvalidCategoryException.class, () ->
                materialEventService.reconstructMaterialEntity(VALID_ID, "INVALID_CATEGORY", FastenerEntity.class)
        );

        // Ensure no save operation happens for invalid category
        verify(fastenerRepository, never()).save(any(FastenerEntity.class));
    }
}
