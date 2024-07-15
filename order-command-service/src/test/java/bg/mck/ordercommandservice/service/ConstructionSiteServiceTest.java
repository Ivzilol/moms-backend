package bg.mck.ordercommandservice.service;


import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.exception.ConstructionSiteAlreadyExists;
import bg.mck.ordercommandservice.exception.ConstructionSiteNotFoundException;
import bg.mck.ordercommandservice.mapper.ConstructionSiteMapper;
import bg.mck.ordercommandservice.repository.ConstructionSiteRepository;
import bg.mck.ordercommandservice.testUtils.ConstructionSiteUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConstructionSiteServiceTest {
    @Mock
    private ConstructionSiteRepository constructionSiteRepository;
    @InjectMocks
    private ConstructionSiteService constructionSiteService;
    @Mock
    private ConstructionSiteMapper constructionSiteMapper;

    private ConstructionSiteDTO constructionSiteDTO;
    private ConstructionSiteEntity constructionSiteEntity;

    @BeforeEach
    public void setUp() {
        constructionSiteDTO = ConstructionSiteUtil.createConstructionSiteDTO();
        constructionSiteEntity = ConstructionSiteUtil.createConstructionSiteEntity();
    }

    @Test
    void test_GetConstructionSite_ByNumberAndName_Found() {
        when(constructionSiteRepository.findByConstructionNumberAndName(anyString(), anyString()))
                .thenReturn(Optional.of(constructionSiteEntity));

        ConstructionSiteEntity result = constructionSiteService.getConstructionSiteByNumberAndName(constructionSiteDTO);

        assertNotNull(result);
        assertEquals("1234", result.getConstructionNumber());
        assertEquals("Site Name", result.getName());

        verify(constructionSiteRepository, times(1))
                .findByConstructionNumberAndName("1234", "Site Name");
    }

    @Test
    void test_GetConstructionSite_ByNumberAndName_NotFound() {
        when(constructionSiteRepository.findByConstructionNumberAndName(anyString(), anyString()))
                .thenReturn(Optional.empty());

        ConstructionSiteNotFoundException exception = assertThrows(
                ConstructionSiteNotFoundException.class,
                () -> constructionSiteService.getConstructionSiteByNumberAndName(constructionSiteDTO)
        );

        assertEquals("Construction site with number 1234 and name Site Name not found", exception.getMessage());

        verify(constructionSiteRepository, times(1))
                .findByConstructionNumberAndName("1234", "Site Name");
    }

    @Test
    void test_CreateConstructionSite_Successfully() {
        when(constructionSiteMapper.toEntity(constructionSiteDTO)).thenReturn(constructionSiteEntity);
        when(constructionSiteRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(constructionSiteRepository.findByConstructionNumber(anyString())).thenReturn(Optional.empty());
        when(constructionSiteRepository.save(any(ConstructionSiteEntity.class))).thenReturn(constructionSiteEntity);
        when(constructionSiteMapper.toDTO(constructionSiteEntity)).thenReturn(constructionSiteDTO);

        ConstructionSiteDTO result = constructionSiteService.createConstructionSite(constructionSiteDTO);

        assertNotNull(result);
        assertEquals(constructionSiteDTO.getConstructionNumber(), result.getConstructionNumber());
        assertEquals(constructionSiteDTO.getName(), result.getName());

        verify(constructionSiteRepository, times(1)).findByName("Site Name");
        verify(constructionSiteRepository, times(1)).findByConstructionNumber("1234");
        verify(constructionSiteRepository, times(1)).save(constructionSiteEntity);
    }

    @Test
    void test_CreateConstructionSite_Name_AlreadyExists() {
        when(constructionSiteMapper.toEntity(constructionSiteDTO)).thenReturn(constructionSiteEntity);
        when(constructionSiteRepository.findByName(anyString())).thenReturn(Optional.of(constructionSiteEntity));

        ConstructionSiteAlreadyExists exception = assertThrows(
                ConstructionSiteAlreadyExists.class,
                () -> constructionSiteService.createConstructionSite(constructionSiteDTO)
        );

        assertEquals("Construction site with name Site Name already exists", exception.getMessage());

        verify(constructionSiteRepository, times(1)).findByName("Site Name");
        verify(constructionSiteRepository, never()).findByConstructionNumber(anyString());
        verify(constructionSiteRepository, never()).save(any(ConstructionSiteEntity.class));
    }

    @Test
    void test_CreateConstructionSite_Number_AlreadyExists() {
        when(constructionSiteMapper.toEntity(constructionSiteDTO)).thenReturn(constructionSiteEntity);
        when(constructionSiteRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(constructionSiteRepository.findByConstructionNumber(anyString())).thenReturn(Optional.of(constructionSiteEntity));

        ConstructionSiteAlreadyExists exception = assertThrows(
                ConstructionSiteAlreadyExists.class,
                () -> constructionSiteService.createConstructionSite(constructionSiteDTO)
        );

        assertEquals("Construction site with number 1234 already exists", exception.getMessage());

        verify(constructionSiteRepository, times(1)).findByName("Site Name");
        verify(constructionSiteRepository, times(1)).findByConstructionNumber("1234");
        verify(constructionSiteRepository, never()).save(any(ConstructionSiteEntity.class));
    }

}

