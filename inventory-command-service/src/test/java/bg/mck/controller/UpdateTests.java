package bg.mck.controller;

import bg.mck.client.InventoryQueryServiceClient;
import bg.mck.dto.CreateMaterialDTO;
import bg.mck.dto.UpdateMaterialDTO;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.LengthUnits;
import bg.mck.enums.MaterialType;
import bg.mck.enums.WeightUnits;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UpdateTests {

    private String url = "/v1/admin/inventory/command/update/%s/%s";
    private String createMaterialUrl = "/v1/admin/inventory/command/materials/create";
    private String deleteUrl = "/v1/admin/inventory/command/materials/%s";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InventoryQueryServiceClient inventoryQueryServiceClient;


    //Query service and Discovery service should be running for tests
    @Test
    public void testCreateUpdateAndDeleteFastener() throws Exception {

        CreateMaterialDTO fastener = new CreateMaterialDTO();
        fastener.setMaterialType(MaterialType.FASTENERS);
        fastener.setType("test");
        fastener.setDiameter("5");
        fastener.setLength("5.5");
        fastener.setLengthUnit(LengthUnits.CM);


        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fastener)));

        String name = fastener.getType() + " " + fastener.getDiameter() + " " + fastener.getLength() + " " + fastener.getLengthUnit();

        FastenerEntity fastenerByName = inventoryQueryServiceClient.getFastenerByName(name);

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();
        updateMaterialDTO.setDiameter("1.1");
        updateMaterialDTO.setLength("1.0");
        updateMaterialDTO.setLengthUnit(LengthUnits.MM);

        mockMvc.perform(patch(String.format(url, MaterialType.FASTENERS.name(), fastenerByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));

        name = fastener.getType() + " " + updateMaterialDTO.getDiameter() + " " + updateMaterialDTO.getLength() + " " + updateMaterialDTO.getLengthUnit();
        fastenerByName = inventoryQueryServiceClient.getFastenerByName(name);
        assertEquals("Test change diameter", "1.1", fastenerByName.getDiameter());
        assertEquals("Test change length", "1.0", fastenerByName.getLength());
        assertEquals("Test change length unit", LengthUnits.MM, fastenerByName.getLengthUnit());


        mockMvc.perform(delete(String.format(deleteUrl, fastenerByName.getId()))
                .param("category", MaterialType.FASTENERS.name()));
        fastenerByName = inventoryQueryServiceClient.getFastenerByName(name);
        assertNull("Should be deleted", fastenerByName);
    }

    @Test
    public void testCreateUpdateAndDeleteGalvanizedSheet() throws Exception {
        CreateMaterialDTO galvanizedSheet = new CreateMaterialDTO();

        galvanizedSheet.setMaterialType(MaterialType.GALVANIZED_SHEET);
        galvanizedSheet.setType("test");
        galvanizedSheet.setMaxLength("5");
        galvanizedSheet.setMaxLengthUnit(LengthUnits.CM);
        galvanizedSheet.setNumberOfSheets("5");
        galvanizedSheet.setDescription("test test");


        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(galvanizedSheet)));

        String name = galvanizedSheet.getType();

        GalvanisedSheetEntity galvanizedSheetByName = inventoryQueryServiceClient.getGalvanizedSheetByName(name);

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();

        updateMaterialDTO.setNumberOfSheets("1");
        updateMaterialDTO.setMaxLengthUnit(LengthUnits.M);
        updateMaterialDTO.setMaxLength("1");
        updateMaterialDTO.setType("new test type");

        mockMvc.perform(patch(String.format(url, MaterialType.GALVANIZED_SHEET.name(), galvanizedSheetByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));


        name = updateMaterialDTO.getType();

        galvanizedSheetByName = inventoryQueryServiceClient.getGalvanizedSheetByName(name);
        assertEquals("Test change number of sheets.", "1", galvanizedSheetByName.getNumberOfSheets());
        assertEquals("Test change max length unit", LengthUnits.M, galvanizedSheetByName.getMaxLengthUnit());

        mockMvc.perform(delete(String.format(deleteUrl, galvanizedSheetByName.getId()))
                .param("category", MaterialType.GALVANIZED_SHEET.name()));

        galvanizedSheetByName = inventoryQueryServiceClient.getGalvanizedSheetByName(name);
        assertNull("Should be deleted", galvanizedSheetByName);
    }

    @Test
    public void testCreateUpdateAndDeleteInsulation() throws Exception {
        CreateMaterialDTO insulation = new CreateMaterialDTO();

        insulation.setMaterialType(MaterialType.INSULATION);
        insulation.setType("testType");
        insulation.setThickness("5");
        insulation.setThicknessUnit(LengthUnits.CM);
        insulation.setDescription("test test");

        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insulation)));

        String name = insulation.getType() + " " + insulation.getThickness() + " " + insulation.getThicknessUnit();

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();

        updateMaterialDTO.setType("newType");
        updateMaterialDTO.setThickness("1");
        updateMaterialDTO.setThicknessUnit(LengthUnits.M);

        InsulationEntity insulationEntityByName = inventoryQueryServiceClient.getInsulationEntityByName(name);

        mockMvc.perform(patch(String.format(url, MaterialType.INSULATION.name(), insulationEntityByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));

        name = updateMaterialDTO.getType() + " " + updateMaterialDTO.getThickness() + " " + updateMaterialDTO.getThicknessUnit();

        insulationEntityByName = inventoryQueryServiceClient.getInsulationEntityByName(name);

        assertEquals("Test change thickness","1",insulationEntityByName.getThickness());
        assertEquals("Test change thickness unit",LengthUnits.M,insulationEntityByName.getThicknessUnit());

        mockMvc.perform(delete(String.format(deleteUrl, insulationEntityByName.getId()))
                .param("category", MaterialType.INSULATION.name()));

        insulationEntityByName = inventoryQueryServiceClient.getInsulationEntityByName(name);
        assertNull("Should be deleted", insulationEntityByName);
    }

    @Test
    public void testCreateUpdateAndDeleteMetalEntity() throws Exception {
        CreateMaterialDTO metal = new CreateMaterialDTO();
        metal.setMaterialType(MaterialType.METAL);
        metal.setTotalWeight("5");
        metal.setTotalWeightUnit(WeightUnits.KG);
        metal.setKind("testKind");
        metal.setDescription("testDescription");

        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(metal)));

        String name = metal.getDescription();

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();

        updateMaterialDTO.setTotalWeight("1");
        updateMaterialDTO.setTotalWeightUnit(WeightUnits.G);
        updateMaterialDTO.setKind("newKind");
        updateMaterialDTO.setDescription("newDescription");

        MetalEntity metalEntityByName = inventoryQueryServiceClient.getMetalEntityByName(name);

        mockMvc.perform(patch(String.format(url, MaterialType.METAL.name(), metalEntityByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));

        name = updateMaterialDTO.getDescription();

        metalEntityByName = inventoryQueryServiceClient.getMetalEntityByName(name);

        assertEquals("Test change total weight","1",metalEntityByName.getTotalWeight());
        assertEquals("Test change total weight unit",WeightUnits.G,metalEntityByName.getTotalWeightUnit());
        assertEquals("Test change kind","newKind",metalEntityByName.getKind());

        mockMvc.perform(delete(String.format(deleteUrl, metalEntityByName.getId()))
                .param("category", MaterialType.METAL.name()));

        metalEntityByName = inventoryQueryServiceClient.getMetalEntityByName(name);

        assertNull("Should be deleted",metalEntityByName);
    }

    @Test
    public void testCreateUpdateAndDeletePanelEntity() throws Exception {
        CreateMaterialDTO panel = new CreateMaterialDTO();

        panel.setMaterialType(MaterialType.PANELS);
        panel.setType("testType");
        panel.setColor("testColor");
        panel.setLength("5");
        panel.setLengthUnit(LengthUnits.CM);
        panel.setWidth("5");
        panel.setWidthUnit(LengthUnits.CM);
        panel.setTotalThickness("5");
        panel.setTotalThicknessUnit(LengthUnits.CM);
        panel.setFrontSheetThickness("5");
        panel.setFrontSheetThicknessUnit(LengthUnits.CM);
        panel.setBackSheetThickness("5");
        panel.setBackSheetThicknessUnit(LengthUnits.CM);

        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(panel)));

        String name = panel.getType() + " " + panel.getLength() + " " + panel.getLengthUnit()
                + " " + panel.getTotalThickness() + " " + panel.getTotalThicknessUnit();

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();

        updateMaterialDTO.setType("newType");
        updateMaterialDTO.setColor("newColor");
        updateMaterialDTO.setLength("1");
        updateMaterialDTO.setLengthUnit(LengthUnits.M);
        updateMaterialDTO.setWidth("1");
        updateMaterialDTO.setWidthUnit(LengthUnits.M);
        updateMaterialDTO.setTotalThickness("1");
        updateMaterialDTO.setTotalThicknessUnit(LengthUnits.M);
        updateMaterialDTO.setFrontSheetThickness("1");
        updateMaterialDTO.setFrontSheetThicknessUnit(LengthUnits.M);
        updateMaterialDTO.setBackSheetThickness("1");
        updateMaterialDTO.setBackSheetThicknessUnit(LengthUnits.M);

        PanelEntity panelEntityByName = inventoryQueryServiceClient.getPanelEntityByName(name);

        mockMvc.perform(patch(String.format(url, MaterialType.PANELS.name(), panelEntityByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));

        name = updateMaterialDTO.getType() + " " + updateMaterialDTO.getLength() + " " + updateMaterialDTO.getLengthUnit()
                + " " + updateMaterialDTO.getTotalThickness() + " " + updateMaterialDTO.getTotalThicknessUnit();

        panelEntityByName = inventoryQueryServiceClient.getPanelEntityByName(name);

        assertEquals("Test change type","newType",panelEntityByName.getType());
        assertEquals("Test change color","newColor",panelEntityByName.getColor());
        assertEquals("Test changeLength","1",panelEntityByName.getLength());
        assertEquals("Test change length unit",LengthUnits.M,panelEntityByName.getLengthUnit());
        assertEquals("Test change width","1",panelEntityByName.getWidth());
        assertEquals("Test change width unit",LengthUnits.M,panelEntityByName.getWidthUnit());
        assertEquals("Test change total thickness","1",panelEntityByName.getTotalThickness());
        assertEquals("Test change total thickness unit",LengthUnits.M,panelEntityByName.getTotalThicknessUnit());
        assertEquals("Test change front sheet thickness","1",panelEntityByName.getFrontSheetThickness());
        assertEquals("Test change front sheet thickness unit",LengthUnits.M,panelEntityByName.getFrontSheetThicknessUnit());

        mockMvc.perform(delete(String.format(deleteUrl, panelEntityByName.getId()))
                .param("category", MaterialType.PANELS.name()));

        panelEntityByName = inventoryQueryServiceClient.getPanelEntityByName(name);
        assertNull("Should be deleted",panelEntityByName);
    }

    @Test
    public void testCreateUpdateAndDeleteRebarEntity() throws Exception {
        CreateMaterialDTO rebar = new CreateMaterialDTO();
        rebar.setMaterialType(MaterialType.REBAR);
        rebar.setMaxLength("5");
        rebar.setMaxLengthUnit(LengthUnits.CM);
        rebar.setDescription("testDescription");

        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rebar)));

        String name = rebar.getDescription();

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();
        updateMaterialDTO.setMaxLength("1");
        updateMaterialDTO.setMaxLengthUnit(LengthUnits.M);
        updateMaterialDTO.setDescription("newDescription");

        RebarEntity rebarEntityByName = inventoryQueryServiceClient.getRebarEntityByName(name);

        mockMvc.perform(patch(String.format(url, MaterialType.REBAR.name(), rebarEntityByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));

        name = updateMaterialDTO.getDescription();

        rebarEntityByName = inventoryQueryServiceClient.getRebarEntityByName(name);

        assertEquals("Test change max length","1",rebarEntityByName.getMaxLength());
        assertEquals("Test change max length unit",LengthUnits.M,rebarEntityByName.getMaxLengthUnit());
        assertEquals("Test change description","newDescription",rebarEntityByName.getDescription());

        mockMvc.perform(delete(String.format(deleteUrl, rebarEntityByName.getId()))
                .param("category", MaterialType.REBAR.name()));

        rebarEntityByName = inventoryQueryServiceClient.getRebarEntityByName(name);
        assertNull("Should be deleted",rebarEntityByName);
    }

    @Test
    public void testCreateUpdateAndDeleteSetEntity() throws Exception {
        CreateMaterialDTO set = new CreateMaterialDTO();
        set.setMaterialType(MaterialType.SET);
        set.setColor("testColor");
        set.setMaxLength("5");
        set.setMaxLengthUnit(LengthUnits.CM);

        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(set)));

        String name = set.getColor() + " " + set.getMaxLength() + " " + set.getMaxLengthUnit();

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();

        updateMaterialDTO.setColor("newColor");
        updateMaterialDTO.setMaxLength("1");
        updateMaterialDTO.setMaxLengthUnit(LengthUnits.M);

        SetEntity setEntityByName = inventoryQueryServiceClient.getSetEntityByName(name);

        mockMvc.perform(patch(String.format(url, MaterialType.SET.name(), setEntityByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));

        name = updateMaterialDTO.getColor() + " " + updateMaterialDTO.getMaxLength() + " " + updateMaterialDTO.getMaxLengthUnit();

        setEntityByName = inventoryQueryServiceClient.getSetEntityByName(name);

        assertEquals("Test change color","newColor",setEntityByName.getColor());
        assertEquals("Test change max length","1",setEntityByName.getMaxLength());
        assertEquals("Test change max length unit",LengthUnits.M,setEntityByName.getMaxLengthUnit());

        mockMvc.perform(delete(String.format(deleteUrl, setEntityByName.getId()))
                .param("category", MaterialType.SET.name()));

        setEntityByName = inventoryQueryServiceClient.getSetEntityByName(name);
        assertNull("Should be deleted",setEntityByName);
    }

    @Test
    public void testCreateUpdateAndDeleteUnspecifiedEntity() throws Exception {
        CreateMaterialDTO unspecified = new CreateMaterialDTO();
        unspecified.setMaterialType(MaterialType.UNSPECIFIED);
        unspecified.setDescription("testDescription");

        mockMvc.perform(post(createMaterialUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unspecified)));

        String name = unspecified.getDescription();

        UpdateMaterialDTO updateMaterialDTO = new UpdateMaterialDTO();

        updateMaterialDTO.setDescription("newDescription");

        UnspecifiedEntity unspecifiedEntityByName = inventoryQueryServiceClient.getUnspecifiedEntityByName(name);

        mockMvc.perform(patch(String.format(url, MaterialType.UNSPECIFIED.name(), unspecifiedEntityByName.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMaterialDTO)));

        name = updateMaterialDTO.getDescription();

        unspecifiedEntityByName = inventoryQueryServiceClient.getUnspecifiedEntityByName(name);

        assertEquals("Test change description","newDescription",unspecifiedEntityByName.getDescription());

        mockMvc.perform(delete(String.format(deleteUrl, unspecifiedEntityByName.getId()))
                .param("category", MaterialType.UNSPECIFIED.name()));

        unspecifiedEntityByName = inventoryQueryServiceClient.getUnspecifiedEntityByName(name);
        assertNull("Should be deleted",unspecifiedEntityByName);
    }
}
