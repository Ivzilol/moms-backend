package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.SetDTO;
import bg.mck.ordercommandservice.entity.SetEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.SetEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetMapperTest {
    private SetDTO setDTO;
    private SetEntity setEntity;
    private SetMapper setMapper;

    @BeforeEach
    public void setUp() {
        setDTO = MaterialUtil.createSetDTO();
        setEntity = MaterialUtil.createSetEntity();
        setMapper = new SetMapperImpl();
    }

    @AfterEach
    public void tearDown() {
        setDTO = null;
        setEntity = null;
    }

    @Test
    public void shouldMap_SetDTO_To_SetEntity() {
        SetEntity result = setMapper.toEntity(setDTO);

        assertNotNull(result);
        assertEquals(setDTO.getId(), result.getId());
        assertEquals(setDTO.getDescription(), result.getDescription());
        assertEquals(setDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(setDTO.getMaterialStatus(), result.getMaterialStatus().name());
        assertEquals(setDTO.getAdminNote(), result.getAdminNote());
        assertEquals(setDTO.getQuantity(), result.getQuantity().split(" ")[0]);
        assertEquals(setDTO.getQuantityUnit(), WeightUnits.valueOf(result.getQuantity().split(" ")[1]));
        assertEquals(setDTO.getMaxLength(), result.getMaxLength().split(" ")[0]);
        assertEquals(setDTO.getMaxLengthUnit(), LengthUnits.valueOf(result.getMaxLength().split(" ")[1]));
    }

    @Test
    public void shouldMap_SetEntity_To_SetDTO() {
        SetDTO result = setMapper.toDTO(setEntity);

        assertNotNull(result);
        assertEquals(setEntity.getId(), result.getId());
        assertEquals(setEntity.getDescription(), result.getDescription());
        assertEquals(setEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(setEntity.getMaterialStatus().name(), result.getMaterialStatus());
        assertEquals(setEntity.getAdminNote(), result.getAdminNote());
        assertEquals(setEntity.getQuantity().split(" ")[0], result.getQuantity());
        assertEquals(setEntity.getQuantity().split(" ")[1], result.getQuantityUnit().name());
        assertEquals(setEntity.getMaxLength().split(" ")[0], result.getMaxLength());
        assertEquals(setEntity.getMaxLength().split(" ")[1], result.getMaxLengthUnit().name());
    }

    @Test
    public void shouldMap_SetEntity_To_SetEvent() {
        SetEvent result = setMapper.toEvent(setEntity);

        assertNotNull(result);
        assertEquals(setEntity.getId(), result.getId());
        assertEquals(setEntity.getDescription(), result.getDescription());
        assertEquals(setEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(setEntity.getMaterialStatus(), result.getMaterialStatus());
        assertEquals(setEntity.getAdminNote(), result.getAdminNote());
        assertEquals(setEntity.getQuantity(), result.getQuantity());
        assertEquals(setEntity.getMaxLength(), result.getMaxLength());
    }
}
