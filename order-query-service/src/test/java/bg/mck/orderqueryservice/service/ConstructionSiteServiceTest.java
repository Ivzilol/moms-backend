package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.ConstructionSiteDTO;
import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import bg.mck.orderqueryservice.exception.ConstructionSiteNotFoundException;
import bg.mck.orderqueryservice.mapper.ConstructionSiteMapper;
import bg.mck.orderqueryservice.repository.ConstructionSiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConstructionSiteServiceTest {

    @Mock
    private ConstructionSiteRepository constructionSiteRepository;

    @Mock
    private ConstructionSiteMapper constructionSiteMapper;

    @InjectMocks
    private ConstructionSiteService constructionSiteService;

    private ConstructionSiteEntity mockEntity;
    private ConstructionSiteDTO mockDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockEntity = new ConstructionSiteEntity();
        mockEntity.setId("1");
        mockEntity.setName("Test Site");

        mockDto = new ConstructionSiteDTO();
        mockDto.setId("1");
        mockDto.setName("Test Site");
    }

    @Test
    void saveConstructionSite_Success() {
        constructionSiteService.saveConstructionSite(mockEntity);

        verify(constructionSiteRepository, times(1)).save(mockEntity);
    }

    @Test
    void getConstructionSiteByName_Success() {
        when(constructionSiteRepository.findByName("Test Site")).thenReturn(Optional.of(mockEntity));
        when(constructionSiteMapper.toDto(mockEntity)).thenReturn(mockDto);

        ConstructionSiteDTO result = constructionSiteService.getConstructionSiteByName("Test Site");

        assertNotNull(result);
        assertEquals("Test Site", result.getName());
        verify(constructionSiteRepository, times(1)).findByName("Test Site");
    }

    @Test
    void getConstructionSiteByName_NotFound() {
        when(constructionSiteRepository.findByName("Test Site")).thenReturn(Optional.empty());

        assertThrows(ConstructionSiteNotFoundException.class, () -> constructionSiteService.getConstructionSiteByName("Test Site"));
    }

    @Test
    void getConstructionSiteById_Success() {
        when(constructionSiteRepository.findById("1")).thenReturn(Optional.of(mockEntity));
        when(constructionSiteMapper.toDto(mockEntity)).thenReturn(mockDto);

        ConstructionSiteDTO result = constructionSiteService.getConstructionSiteById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(constructionSiteRepository, times(1)).findById("1");
    }

    @Test
    void getConstructionSiteById_NotFound() {
        when(constructionSiteRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ConstructionSiteNotFoundException.class, () -> constructionSiteService.getConstructionSiteById("1"));
    }

    @Test
    void getAllConstructionSites_Success() {
        List<ConstructionSiteEntity> entities = List.of(mockEntity);

        when(constructionSiteRepository.findAll()).thenReturn(entities);
        when(constructionSiteMapper.toDto(mockEntity)).thenReturn(mockDto);

        List<ConstructionSiteDTO> result = constructionSiteService.getAllConstructionSites();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Site", result.getFirst().getName());
        verify(constructionSiteRepository, times(1)).findAll();
    }

    @Test
    void updateConstructionSite_Success() {
        when(constructionSiteRepository.save(mockEntity)).thenReturn(mockEntity);

        constructionSiteService.updateConstructionSite(mockEntity);

        verify(constructionSiteRepository, times(1)).save(mockEntity);
    }

}
