package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.InsulationDTO;
import bg.mck.ordercommandservice.entity.InsulationEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.event.InsulationEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InsulationMapperTest {

    private InsulationMapper insulationMapper;
    private InsulationDTO insulationDTO;
    private InsulationEntity insulationEntity;


    @BeforeEach
    public void setUp(){
        insulationDTO = MaterialUtil.createInsulationDTO();
        insulationEntity = MaterialUtil.createInsulationEntity();
        insulationMapper = new InsulationMapperImpl();
    }

    @AfterEach
    public void tearDown(){
        insulationDTO = null;
        insulationEntity = null;
    }

    @Test
    public void shouldMap_InsulationDTO_To_InsulationEntity(){
        InsulationEntity result = insulationMapper.toEntity(insulationDTO);

        assertNotNull(result);
        assertEquals(insulationDTO.getId(), result.getId());
        assertEquals(insulationDTO.getDescription(), result.getDescription());
        assertEquals(insulationDTO.getQuantity(), result.getQuantity().split(" ")[0]);
        assertEquals(insulationDTO.getQuantityUnit(), AreaUnits.valueOf(result.getQuantity().split(" ")[1]));
        assertEquals(insulationDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(insulationDTO.getThickness(), result.getThickness().split(" ")[0]);
        assertEquals(insulationDTO.getThicknessUnit(), LengthUnits.valueOf(result.getThickness().split(" ")[1]));
        assertEquals(insulationDTO.getType(), result.getType());
        assertEquals(insulationDTO.getMaterialStatus(), result.getMaterialStatus().name());
    }

    @Test
    public void shouldMap_InsulationEntity_To_InsulationDTO(){
        InsulationDTO result = insulationMapper.toDTO(insulationEntity);

        assertNotNull(result);
        assertEquals(insulationEntity.getId(), result.getId());
        assertEquals(insulationEntity.getDescription(), result.getDescription());
        assertEquals(insulationEntity.getQuantity().split(" ")[0], result.getQuantity());
        assertEquals(AreaUnits.valueOf(insulationEntity.getQuantity().split(" ")[1]), result.getQuantityUnit());
        assertEquals(insulationEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(insulationEntity.getThickness().split(" ")[0], result.getThickness());
        assertEquals(LengthUnits.valueOf(insulationEntity.getThickness().split(" ")[1]), result.getThicknessUnit());
        assertEquals(insulationEntity.getType(), result.getType());
        assertEquals(insulationEntity.getMaterialStatus().name(), result.getMaterialStatus());
    }

    @Test
    public void shouldMap_InsulationEntity_To_InsulationEvent(){
        InsulationEvent result = insulationMapper.toEvent(insulationEntity);

        assertNotNull(result);
        assertEquals(insulationEntity.getId(), result.getId());
        assertEquals(insulationEntity.getDescription(), result.getDescription());
        assertEquals(insulationEntity.getQuantity(), result.getQuantity());
        assertEquals(insulationEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(insulationEntity.getThickness(), result.getThickness());
        assertEquals(insulationEntity.getType(), result.getType());
        assertEquals(insulationEntity.getMaterialStatus(), result.getMaterialStatus());
    }

}
