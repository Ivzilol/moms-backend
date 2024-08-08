package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.event.FasterEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastenerMapperTest {

    private FastenerMapper fastenerMapper;
    private FastenerDTO fastenerDTO;
    private FastenerEntity fastenerEntity;


    @BeforeEach
    public void setUp() {
        fastenerDTO = MaterialUtil.createFastenerDTO();
        fastenerEntity = MaterialUtil.createFastenerEntity();
        fastenerMapper = new FastenerMapperImpl();
    }

    @AfterEach
    public void tearDown() {
        fastenerDTO = null;
        fastenerEntity = null;
    }

    @Test
    public void shouldMap_FastenerDTO_To_FastenerEntity() {
        FastenerEntity result = fastenerMapper.toEntity(fastenerDTO);

        assertNotNull(result);
        assertEquals(fastenerDTO.getId(), result.getId());
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
    void shouldMap_FastenerEntity_To_FastenerDTO() {
        FastenerDTO result = fastenerMapper.toDTO(fastenerEntity);

        assertNotNull(result);
        assertEquals(fastenerEntity.getId(), result.getId());
        assertEquals(fastenerEntity.getDescription(), result.getDescription());
        assertEquals(fastenerEntity.getQuantity(), result.getQuantity());
        assertEquals(fastenerEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(fastenerEntity.getDiameter(), result.getDiameter());
        assertEquals(fastenerEntity.getLength().split(" ")[0], result.getLength());
        assertEquals(fastenerEntity.getModel(), result.getModel());
        assertEquals(fastenerEntity.getType(), result.getType());
        assertEquals(fastenerEntity.getClazz(), result.getClazz());
    }

    @Test
    void shouldMap_FastenerEntity_To_FasterEvent() {
        FasterEvent result = fastenerMapper.toEvent(fastenerEntity);

        assertNotNull(result);
        assertEquals(fastenerEntity.getId(), result.getId());
        assertEquals(fastenerEntity.getDescription(), result.getDescription());
        assertEquals(fastenerEntity.getQuantity(), result.getQuantity());
        assertEquals(fastenerEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(fastenerEntity.getDiameter(), result.getDiameter());
        assertEquals(fastenerEntity.getLength(), result.getLength());
        assertEquals(fastenerEntity.getModel(), result.getModel());
        assertEquals(fastenerEntity.getType(), result.getType());
    }


}
