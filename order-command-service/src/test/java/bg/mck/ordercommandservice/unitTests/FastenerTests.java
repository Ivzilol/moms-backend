package bg.mck.ordercommandservice.unitTests;

import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import bg.mck.ordercommandservice.service.FastenerService;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FastenerTests {
    @InjectMocks
    private FastenerService fastenerService;
    @Mock
    private FastenerRepository fastenerRepository;
    @Mock
    private FastenerMapper fastenerMapper;

    private FastenerDTO fastenerDTO;
    private FastenerEntity fastenerEntity;

    @BeforeEach
    public void setUp() {
        fastenerDTO = MaterialUtil.createFastenerDTO();
        fastenerEntity = MaterialUtil.createFastenerEntity();
        when(fastenerMapper.toEntity(fastenerDTO)).thenReturn(fastenerEntity);
        when(fastenerMapper.toDTO(fastenerEntity)).thenReturn(fastenerDTO);
    }

    @AfterEach
    public void tearDown() {
        reset(fastenerRepository);
        reset(fastenerMapper);
    }

    @Test
    void test_Fastener_mapper_toEntity() {
        FastenerEntity result = fastenerMapper.toEntity(fastenerDTO);

        assertNotNull(result);
        assertEquals(fastenerDTO.getId(), 1);
        assertEquals(fastenerDTO.getDescription(), result.getDescription());
        assertEquals(fastenerDTO.getQuantity(), result.getQuantity());
        assertEquals(fastenerDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(fastenerDTO.getDiameter(), result.getDiameter());
        assertEquals(fastenerDTO.getLength(), result.getLength());
        assertEquals(fastenerDTO.getModel(), result.getModel());
        assertEquals(fastenerDTO.getType(), result.getType());
        assertEquals(fastenerDTO.getClazz(), result.getClazz());
    }

    @Test
    void test_Fastener_mapper_toDTO() {
        FastenerDTO result = fastenerMapper.toDTO(fastenerEntity);

        assertNotNull(result);
        assertEquals(fastenerEntity.getDescription(), result.getDescription());
        assertEquals(fastenerEntity.getQuantity(), result.getQuantity());
        assertEquals(fastenerEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(fastenerEntity.getDiameter(), result.getDiameter());
        assertEquals(fastenerEntity.getLength(), result.getLength());
        assertEquals(fastenerEntity.getModel(), result.getModel());
        assertEquals(fastenerEntity.getType(), result.getType());
        assertEquals(fastenerEntity.getClazz(), result.getClazz());
    }

    @Test
    void test_CreateFastener() {
        when(fastenerRepository.save(fastenerEntity)).thenReturn(fastenerEntity);

        FastenerDTO result = fastenerService.createFastener(fastenerDTO);

        verify(fastenerRepository, times(1)).save(fastenerEntity);
        assertEquals(fastenerDTO.getDescription(), result.getDescription());
        assertEquals(fastenerDTO.getQuantity(), result.getQuantity());
        assertEquals(fastenerDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(fastenerDTO.getDiameter(), result.getDiameter());
        assertEquals(fastenerDTO.getLength(), result.getLength());
        assertEquals(fastenerDTO.getModel(), result.getModel());
        assertEquals(fastenerDTO.getType(), result.getType());
        assertEquals(fastenerDTO.getClazz(), result.getClazz());

    }

}
