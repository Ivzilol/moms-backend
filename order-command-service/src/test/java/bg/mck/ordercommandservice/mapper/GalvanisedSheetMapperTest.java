package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.GalvanisedSheetDTO;
import bg.mck.ordercommandservice.entity.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.event.GalvanisedSheetEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GalvanisedSheetMapperTest {

    private GalvanisedSheetMapper galvanisedSheetMapper;
    private GalvanisedSheetDTO galvanisedSheetDTO;
    private GalvanisedSheetEntity galvanisedSheetEntity;

    @BeforeEach
    public void setUp() {
        galvanisedSheetDTO = MaterialUtil.createGalvanisedSheetDTO();
        galvanisedSheetEntity = MaterialUtil.createGalvanisedSheetEntity();
        galvanisedSheetMapper = new GalvanisedSheetMapperImpl();
    }

    @AfterEach
    public void tearDown() {
        galvanisedSheetDTO = null;
        galvanisedSheetEntity = null;
    }

    @Test
    public void shouldMap_GalvanisedSheetEntity_To_GalvanisedSheetDTO() {
        GalvanisedSheetEntity result = galvanisedSheetMapper.toEntity(galvanisedSheetDTO);

        assertNotNull(result);
        assertEquals(galvanisedSheetDTO.getId(), result.getId());
        assertEquals(galvanisedSheetDTO.getDescription(), result.getDescription());
        assertEquals(galvanisedSheetDTO.getQuantity(), result.getQuantity().split(" ")[0]);
        assertEquals(galvanisedSheetDTO.getQuantityUnit(), AreaUnits.valueOf(result.getQuantity().split(" ")[1]));
        assertEquals(galvanisedSheetDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(galvanisedSheetDTO.getType(), result.getType());
        assertEquals(galvanisedSheetDTO.getMaxLength(), result.getMaxLength().split(" ")[0]);
        assertEquals(galvanisedSheetDTO.getMaxLengthUnit(), LengthUnits.valueOf(result.getMaxLength().split(" ")[1]));
        assertEquals(galvanisedSheetDTO.getNumberOfSheets(), result.getNumberOfSheets());
        assertEquals(galvanisedSheetDTO.getAdminNote(), result.getAdminNote());
        assertEquals(MaterialStatus.valueOf(galvanisedSheetDTO.getMaterialStatus()), result.getMaterialStatus());
    }

    @Test
    public void shouldMap_GalvanisedSheetDTO_To_GalvanisedSheetEntity(){
        GalvanisedSheetDTO result = galvanisedSheetMapper.toDTO(galvanisedSheetEntity);

        assertNotNull(result);
        assertEquals(galvanisedSheetEntity.getId(), result.getId());
        assertEquals(galvanisedSheetEntity.getDescription(), result.getDescription());
        assertEquals(galvanisedSheetEntity.getQuantity().split(" ")[0], result.getQuantity());
        assertEquals(AreaUnits.valueOf(galvanisedSheetEntity.getQuantity().split(" ")[1]), result.getQuantityUnit());
        assertEquals(galvanisedSheetEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(galvanisedSheetEntity.getType(), result.getType());
        assertEquals(galvanisedSheetEntity.getMaxLength().split(" ")[0], result.getMaxLength());
        assertEquals(LengthUnits.valueOf(galvanisedSheetEntity.getMaxLength().split(" ")[1]), result.getMaxLengthUnit());
        assertEquals(galvanisedSheetEntity.getNumberOfSheets(), result.getNumberOfSheets());
        assertEquals(galvanisedSheetEntity.getAdminNote(), result.getAdminNote());
        assertEquals(galvanisedSheetEntity.getMaterialStatus(), MaterialStatus.valueOf(result.getMaterialStatus()));
    }

    @Test
    void shouldMap_GalvanisedSheetEntity_To_GalvanisedSheetEvent() {
        GalvanisedSheetEvent result = galvanisedSheetMapper.toEvent(galvanisedSheetEntity);

        assertNotNull(result);
        assertEquals(galvanisedSheetEntity.getId(), result.getId());
        assertEquals(galvanisedSheetEntity.getDescription(), result.getDescription());
        assertEquals(galvanisedSheetEntity.getQuantity(), result.getQuantity());
        assertEquals(galvanisedSheetEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(galvanisedSheetEntity.getType(), result.getType());
        assertEquals(galvanisedSheetEntity.getMaxLength(), result.getMaxLength());
        assertEquals(galvanisedSheetEntity.getNumberOfSheets(), result.getNumberOfSheets());
        assertEquals(galvanisedSheetEntity.getAdminNote(), result.getAdminNote());
        assertEquals(galvanisedSheetEntity.getMaterialStatus(), result.getMaterialStatus());
    }
}

