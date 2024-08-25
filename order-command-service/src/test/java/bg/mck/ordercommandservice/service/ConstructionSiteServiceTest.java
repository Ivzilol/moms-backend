package bg.mck.ordercommandservice.service;


import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.event.ConstructionSiteEvent;
import bg.mck.ordercommandservice.event.EventData;
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
    @Mock
    private OrderQueryServiceClient orderQueryServiceClient;

    private ConstructionSiteDTO constructionSiteDTO;
    private ConstructionSiteEntity constructionSiteEntity;
    private ConstructionSiteEvent constructionSiteEvent;

    @BeforeEach
    public void setUp() {
        constructionSiteDTO = ConstructionSiteUtil.createConstructionSiteDTO();
        constructionSiteEntity = ConstructionSiteUtil.createConstructionSiteEntity();
        constructionSiteEvent = ConstructionSiteUtil.createConstructionSiteEvent();
    }

    @Test
    void test_GetConstructionSite_ByNumberAndName_Found() {
        when(constructionSiteRepository.findByName(anyString())).thenReturn(Optional.of(constructionSiteEntity));

        ConstructionSiteEntity result = constructionSiteService.getConstructionSiteByName(constructionSiteDTO.getName());

        assertNotNull(result);
        assertEquals("1234", result.getConstructionNumber());
        assertEquals("Site Name", result.getName());

        verify(constructionSiteRepository, times(1))
                .findByName("Site Name");
    }

    @Test
    void test_GetConstructionSite_ByNumberAndName_NotFound() {

        ConstructionSiteNotFoundException exception = assertThrows(
                ConstructionSiteNotFoundException.class,
                () -> constructionSiteService.getConstructionSiteByNumberAndName(constructionSiteDTO)
        );

        assertEquals("Construction site with number 1234 and name Site Name not found", exception.getMessage());

        verify(constructionSiteRepository, times(1)).findByName("Site Name");
    }

    @Test
    public void test_GetConstructionSite_Successfully() {
        when(constructionSiteRepository.findById(anyLong())).thenReturn(Optional.of(constructionSiteEntity));
        when(constructionSiteMapper.toDTO(constructionSiteEntity)).thenReturn(constructionSiteDTO);

        ConstructionSiteDTO result = constructionSiteService.getConstructionSite(1L);

        assertNotNull(result);
        assertEquals(constructionSiteDTO.getConstructionNumber(), result.getConstructionNumber());
        assertEquals(constructionSiteDTO.getName(), result.getName());

        verify(constructionSiteRepository, times(1)).findById(1L);
    }

    @Test
    public void test_GetConstructionSite_NotFound() {
        when(constructionSiteRepository.findById(anyLong())).thenReturn(Optional.empty());

        ConstructionSiteNotFoundException exception = assertThrows(
                ConstructionSiteNotFoundException.class,
                () -> constructionSiteService.getConstructionSite(1L)
        );

        assertEquals("Construction site with id 1 not found", exception.getMessage());

        verify(constructionSiteRepository, times(1)).findById(1L);
    }

    @Test
    public void test_GetConstructionSiteById_Successfully() {
        when(constructionSiteRepository.findById(anyLong())).thenReturn(Optional.of(constructionSiteEntity));

        ConstructionSiteEntity result = constructionSiteService.getConstructionSiteById(1L);

        assertNotNull(result);
        assertEquals(constructionSiteEntity.getConstructionNumber(), result.getConstructionNumber());
        assertEquals(constructionSiteEntity.getName(), result.getName());

        verify(constructionSiteRepository, times(1)).findById(1L);
    }

    @Test
    public void test_GetConstructionSiteById_NotFound() {
        when(constructionSiteRepository.findById(anyLong())).thenReturn(Optional.empty());

        ConstructionSiteNotFoundException exception = assertThrows(
                ConstructionSiteNotFoundException.class,
                () -> constructionSiteService.getConstructionSiteById(1L)
        );
        assertEquals("Construction site with id 1 not found", exception.getMessage());
        verify(constructionSiteRepository, times(1)).findById(1L);
    }
    @Test
    void test_CreateConstructionSite_Successfully() {
        when(constructionSiteMapper.toEntity(constructionSiteDTO)).thenReturn(constructionSiteEntity);
        when(constructionSiteRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(constructionSiteRepository.findByConstructionNumber(anyString())).thenReturn(Optional.empty());
        when(constructionSiteRepository.save(any(ConstructionSiteEntity.class))).thenReturn(constructionSiteEntity);
        when(constructionSiteMapper.toDTO(constructionSiteEntity)).thenReturn(constructionSiteDTO);
        when(constructionSiteMapper.toEvent(constructionSiteEntity)).thenReturn(constructionSiteEvent);

//        when(constructionSiteMapper.toEvent(constructionSiteEntity)).thenReturn(constructionSiteEvent);
        doNothing().when(orderQueryServiceClient).sendConstructionSiteEvent(any(EventData.class), anyString());

        ConstructionSiteDTO result = constructionSiteService.createConstructionSite(constructionSiteDTO);

        assertNotNull(result);
        assertEquals(constructionSiteDTO.getConstructionNumber(), result.getConstructionNumber());
        assertEquals(constructionSiteDTO.getName(), result.getName());

        verify(constructionSiteRepository, times(1)).findByName("Site Name");
        verify(constructionSiteRepository, times(1)).findByConstructionNumber("1234");
        verify(constructionSiteRepository, times(1)).save(constructionSiteEntity);

        verify(orderQueryServiceClient, times(1)).sendConstructionSiteEvent(any(EventData.class), anyString());

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

