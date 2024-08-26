package bg.mck.service;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.MaterialType;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.material.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialDeleteServiceTest {

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

    @InjectMocks
    private MaterialDeleteService materialDeleteService;

    private final String VALID_ID = "valid-id";
    private final String INVALID_ID = "invalid-id";

    @BeforeEach
    void setUp() {
        // Using lenient to avoid unnecessary stubbing exception
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
    void testDeleteMaterialByIdAndCategory_ValidCategoryAndId_ShouldDeleteMaterial() {
        for (MaterialType type : MaterialType.values()) {
            String typeName = type.name();

            assertDoesNotThrow(() -> materialDeleteService.deleteMaterialByIdAndCategory(VALID_ID, typeName));
            verifyCorrectRepositoryCalled(type, VALID_ID);
        }
    }

    @Test
    void testDeleteMaterialByIdAndCategory_InvalidCategory_ShouldThrowInvalidCategoryException() {
        assertThrows(InvalidCategoryException.class, () -> materialDeleteService.deleteMaterialByIdAndCategory(VALID_ID, "NON_EXISTENT_CATEGORY"));
    }

    @Test
    void testDeleteMaterialByIdAndCategory_ValidCategoryButInvalidId_ShouldThrowInventoryItemNotFoundException() {
        for (MaterialType type : MaterialType.values()) {
            String typeName = type.name();

            assertThrows(InventoryItemNotFoundException.class, () -> materialDeleteService.deleteMaterialByIdAndCategory(INVALID_ID, typeName));
            verifyNoDeletionPerformed(type, INVALID_ID);
        }
    }

    private void verifyCorrectRepositoryCalled(MaterialType type, String id) {
        switch (type) {
            case FASTENERS -> verify(fastenerRepository).deleteById(id);
            case GALVANIZED_SHEET -> verify(galvanisedSheetRepository).deleteById(id);
            case INSULATION -> verify(insulationRepository).deleteById(id);
            case PANELS -> verify(panelRepository).deleteById(id);
            case REBAR -> verify(rebarRepository).deleteById(id);
            case SET -> verify(setRepository).deleteById(id);
            case UNSPECIFIED -> verify(unspecifiedRepository).deleteById(id);
            case METAL -> verify(metalRepository).deleteById(id);
            default -> throw new InvalidCategoryException("Unhandled category type: " + type);
        }
    }

    private void verifyNoDeletionPerformed(MaterialType type, String id) {
        switch (type) {
            case FASTENERS -> verify(fastenerRepository, never()).deleteById(id);
            case GALVANIZED_SHEET -> verify(galvanisedSheetRepository, never()).deleteById(id);
            case INSULATION -> verify(insulationRepository, never()).deleteById(id);
            case PANELS -> verify(panelRepository, never()).deleteById(id);
            case REBAR -> verify(rebarRepository, never()).deleteById(id);
            case SET -> verify(setRepository, never()).deleteById(id);
            case UNSPECIFIED -> verify(unspecifiedRepository, never()).deleteById(id);
            case METAL -> verify(metalRepository, never()).deleteById(id);
            default -> throw new InvalidCategoryException("Unhandled category type: " + type);
        }
    }
}
