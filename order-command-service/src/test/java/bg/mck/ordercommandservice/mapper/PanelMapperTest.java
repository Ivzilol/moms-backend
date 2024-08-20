package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.PanelDTO;
import bg.mck.ordercommandservice.entity.PanelEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.PanelEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PanelMapperTest {

    private PanelMapper panelMapper;
    private PanelDTO panelDTO;
    private PanelEntity panelEntity;


    @BeforeEach
    public void setUp(){
        panelDTO = MaterialUtil.createPanelDTO();
        panelEntity = MaterialUtil.createPanelEntity();
        panelMapper = new PanelMapperImpl();
    }

    @AfterEach
    public void tearDown(){
        panelDTO = null;
        panelEntity = null;
    }

    @Test
    public void shouldMap_PanelDTO_To_PanelEntity(){
        PanelEntity result = panelMapper.toEntity(panelDTO);

        assertNotNull(result);
        assertEquals(panelDTO.getId(), result.getId());
        assertEquals(panelDTO.getDescription(), result.getDescription());
        assertEquals(panelDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(panelDTO.getMaterialStatus(), result.getMaterialStatus().name());

        assertEquals(panelDTO.getQuantity(), result.getQuantity().split(" ")[0]);
        assertEquals(panelDTO.getQuantityUnit(), AreaUnits.valueOf(result.getQuantity().split(" ")[1]));
        assertEquals(panelDTO.getWidth(), result.getWidth().split(" ")[0]);
        assertEquals(panelDTO.getWidthUnit(), LengthUnits.valueOf(result.getWidth().split(" ")[1]));
        assertEquals(panelDTO.getLength(), result.getLength().split(" ")[0]);
        assertEquals(panelDTO.getLengthUnit(), LengthUnits.valueOf(result.getLength().split(" ")[1]));
        assertEquals(panelDTO.getTotalThickness(), result.getTotalThickness().split(" ")[0]);
        assertEquals(panelDTO.getTotalThicknessUnit(), LengthUnits.valueOf(result.getTotalThickness().split(" ")[1]));
        assertEquals(panelDTO.getFrontSheetThickness(), result.getFrontSheetThickness().split(" ")[0]);
        assertEquals(panelDTO.getFrontSheetThicknessUnit(), LengthUnits.valueOf(result.getFrontSheetThickness().split(" ")[1]));
        assertEquals(panelDTO.getBackSheetThickness(), result.getBackSheetThickness().split(" ")[0]);
        assertEquals(panelDTO.getBackSheetThicknessUnit(), LengthUnits.valueOf(result.getBackSheetThickness().split(" ")[1]));
    }

    @Test
    public void shouldMap_PanelEntity_To_PanelDTO(){
        PanelDTO result = panelMapper.toDTO(panelEntity);

        assertNotNull(result);
        assertEquals(panelEntity.getId(), result.getId());
        assertEquals(panelEntity.getDescription(), result.getDescription());
        assertEquals(panelEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(panelEntity.getMaterialStatus().name(), result.getMaterialStatus());

        assertEquals(panelEntity.getQuantity().split(" ")[0], result.getQuantity());
        assertEquals(panelEntity.getQuantity().split(" ")[1], result.getQuantityUnit().name());
        assertEquals(panelEntity.getWidth().split(" ")[0], result.getWidth());
        assertEquals(panelEntity.getWidth().split(" ")[1], result.getWidthUnit().name());
        assertEquals(panelEntity.getLength().split(" ")[0], result.getLength());
        assertEquals(panelEntity.getLength().split(" ")[1], result.getLengthUnit().name());
        assertEquals(panelEntity.getTotalThickness().split(" ")[0], result.getTotalThickness());
        assertEquals(panelEntity.getTotalThickness().split(" ")[1], result.getTotalThicknessUnit().name());
        assertEquals(panelEntity.getFrontSheetThickness().split(" ")[0], result.getFrontSheetThickness());
        assertEquals(panelEntity.getFrontSheetThickness().split(" ")[1], result.getFrontSheetThicknessUnit().name());
        assertEquals(panelEntity.getBackSheetThickness().split(" ")[0], result.getBackSheetThickness());
        assertEquals(panelEntity.getBackSheetThickness().split(" ")[1], result.getBackSheetThicknessUnit().name());
    }

    @Test
    public void shouldMap_PanelEntity_To_PanelEvent(){
        PanelEvent result = panelMapper.toEvent(panelEntity);

        assertNotNull(result);
        assertEquals(panelEntity.getId(), result.getId());
        assertEquals(panelEntity.getDescription(), result.getDescription());
        assertEquals(panelEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(panelEntity.getMaterialStatus(), result.getMaterialStatus());
        assertEquals(panelEntity.getQuantity(), result.getQuantity());
        assertEquals(panelEntity.getWidth(), result.getWidth());
        assertEquals(panelEntity.getLength(), result.getLength());
        assertEquals(panelEntity.getTotalThickness(), result.getTotalThickness());
        assertEquals(panelEntity.getFrontSheetThickness(), result.getFrontSheetThickness());
        assertEquals(panelEntity.getBackSheetThickness(), result.getBackSheetThickness());
    }


}
