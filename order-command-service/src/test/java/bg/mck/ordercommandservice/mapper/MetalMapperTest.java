package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.MetalDTO;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.MetalEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MetalMapperTest {
    private MetalMapper metalMapper;
    private MetalDTO metalDTO;
    private MetalEntity metalEntity;

    @BeforeEach
    public void setUp() {
        metalDTO = MaterialUtil.createMetalDTO();
        metalEntity = MaterialUtil.createMetalEntity();
        metalMapper = new MetalMapperImpl();
    }

    @AfterEach
    public void tearDown() {
        metalDTO = null;
        metalEntity = null;
    }

    @Test
    public void shouldMap_MetalDTO_To_MetalEntity() {
        MetalEntity result = metalMapper.toEntity(metalDTO);

        assertNotNull(result);
        assertEquals(metalDTO.getId(), result.getId());
        assertEquals(metalDTO.getDescription(), result.getDescription());
        assertEquals(metalDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(metalDTO.getTotalWeight(), result.getTotalWeight().split(" ")[0]);
        assertEquals(metalDTO.getTotalWeightUnit(), WeightUnits.valueOf(result.getTotalWeight().split(" ")[1]));
        assertEquals(metalDTO.getAdminNote(), result.getAdminNote());
        assertEquals(MaterialStatus.valueOf(metalDTO.getMaterialStatus()), result.getMaterialStatus());
        assertEquals(metalDTO.getKind(), result.getKind());
    }

    @Test
    public void shouldMap_MetalEntity_To_MetalDTO() {
        MetalDTO result = metalMapper.toDTO(metalEntity);

        assertNotNull(result);
        assertEquals(metalEntity.getId(), result.getId());
        assertEquals(metalEntity.getDescription(), result.getDescription());
        assertEquals(metalEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(metalEntity.getTotalWeight().split(" ")[0], result.getTotalWeight());
        assertEquals(WeightUnits.valueOf(metalEntity.getTotalWeight().split(" ")[1]), result.getTotalWeightUnit());
        assertEquals(metalEntity.getAdminNote(), result.getAdminNote());
        assertEquals(metalEntity.getMaterialStatus().name(), result.getMaterialStatus());
        assertEquals(metalEntity.getKind(), result.getKind());
    }

    @Test
    public void shouldMap_MetalEvent_To_MetalEntity() {
        MetalEvent result = metalMapper.toEvent(metalEntity);

        assertNotNull(result);
        assertEquals(metalEntity.getId(), result.getId());
        assertEquals(metalEntity.getDescription(), result.getDescription());
        assertEquals(metalEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(metalEntity.getTotalWeight(), result.getTotalWeight());
        assertEquals(metalEntity.getAdminNote(), result.getAdminNote());
        assertEquals(metalEntity.getMaterialStatus(), result.getMaterialStatus());
        assertEquals(metalEntity.getKind(), result.getKind());
    }


}
