package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.UnspecifiedDTO;
import bg.mck.ordercommandservice.entity.UnspecifiedEntity;
import bg.mck.ordercommandservice.event.UnspecifiedEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnspecifiedMapperTest {
    private UnspecifiedMapper unspecifiedMapper;
    private UnspecifiedDTO unspecifiedDTO;
    private UnspecifiedEntity unspecifiedEntity;

    @BeforeEach
    public void setUp() {
        unspecifiedDTO = MaterialUtil.createUnspecifiedDTO();
        unspecifiedEntity = MaterialUtil.createUnspecifiedEntity();
        unspecifiedMapper = new UnspecifiedMapperImpl();
    }

    @AfterEach
    public void tearDown() {
        unspecifiedDTO = null;
        unspecifiedEntity = null;
    }

    @Test
    public void shouldMap_UnspecifiedDTO_To_UnspecifiedEntity() {
        UnspecifiedEntity result = unspecifiedMapper.toEntity(unspecifiedDTO);

        assertNotNull(result);
        assertEquals(unspecifiedDTO.getId(), result.getId());
        assertEquals(unspecifiedDTO.getDescription(), result.getDescription());
        assertEquals(unspecifiedDTO.getQuantity(), result.getQuantity());
        assertEquals(unspecifiedDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(unspecifiedDTO.getMaterialStatus(), result.getMaterialStatus().name());
        assertEquals(unspecifiedDTO.getAdminNote(), result.getAdminNote());
    }

    @Test
    public void shouldMap_UnspecifiedEntity_To_UnspecifiedDTO() {
        UnspecifiedDTO result = unspecifiedMapper.toDTO(unspecifiedEntity);

        assertNotNull(result);
        assertEquals(unspecifiedEntity.getId(), result.getId());
        assertEquals(unspecifiedEntity.getDescription(), result.getDescription());
        assertEquals(unspecifiedEntity.getQuantity(), result.getQuantity());
        assertEquals(unspecifiedEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(unspecifiedEntity.getMaterialStatus().name(), result.getMaterialStatus());
        assertEquals(unspecifiedEntity.getAdminNote(), result.getAdminNote());
    }

    @Test
    public void shouldMap_UnspecifiedEntity_To_UnspecifiedEvent() {
        UnspecifiedEvent result = unspecifiedMapper.toEvent(unspecifiedEntity);

        assertNotNull(result);
        assertEquals(unspecifiedEntity.getId(), result.getId());
        assertEquals(unspecifiedEntity.getDescription(), result.getDescription());
        assertEquals(unspecifiedEntity.getQuantity(), result.getQuantity());
        assertEquals(unspecifiedEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(unspecifiedEntity.getMaterialStatus(), result.getMaterialStatus());
        assertEquals(unspecifiedEntity.getAdminNote(), result.getAdminNote());
    }
}
