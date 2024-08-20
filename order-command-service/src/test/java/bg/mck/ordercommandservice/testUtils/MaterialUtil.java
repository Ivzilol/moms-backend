package bg.mck.ordercommandservice.testUtils;

import bg.mck.ordercommandservice.dto.*;
import bg.mck.ordercommandservice.entity.*;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;

public class MaterialUtil {

    private static Long idGenerator = 0L;

    public static FastenerDTO createFastenerDTO() {
        FastenerDTO fastenerDTO = new FastenerDTO();
        fastenerDTO.setDiameter("M12")
                .setId(++idGenerator)
                .setDescription("Fastener description")
                .setSpecificationFileUrl("https://fastener.com");
        fastenerDTO.setClazz("10.9")
                .setDiameter("M12")
                .setLength("55.0")
                .setLengthUnit(LengthUnits.MM)
                .setQuantity("10.0")
                .setStandard("DIN 976")
                .setType("Болт М12х55");
        return fastenerDTO;
    }

    public static FastenerEntity createFastenerEntity() {
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setDiameter("M12")
                .setDescription("Fastener description")
                .setSpecificationFileUrl("https://fastener.com")
                .setId(++idGenerator);
        fastenerEntity.setType("Болт М12х55")
                .setDiameter("M12")
                .setLength("55.0 MM")
                .setStandard("DIN 976")
                .setQuantity("10.0")
                .setClazz("10.9");
        return fastenerEntity;
    }

    public static GalvanisedSheetDTO createGalvanisedSheetDTO() {
        GalvanisedSheetDTO galvanisedSheetDTO = new GalvanisedSheetDTO();
        galvanisedSheetDTO.setId(11L)
                .setDescription("Galvanised sheet description")
                .setSpecificationFileUrl("https://galvanised-sheet.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        galvanisedSheetDTO
                .setMaxLength("1000")
                .setMaxLengthUnit(LengthUnits.MM)
                .setNumberOfSheets("10")
                .setQuantity("100")
                .setQuantityUnit(AreaUnits.CM2)
                .setType("Galvanised sheet");
        return galvanisedSheetDTO;
    }

    public static GalvanisedSheetEntity createGalvanisedSheetEntity() {
        GalvanisedSheetEntity galvanisedSheetEntity = new GalvanisedSheetEntity();
        galvanisedSheetEntity
                .setDescription("Galvanised sheet description")
                .setSpecificationFileUrl("https://galvanised-sheet.com")
                .setAdminNote("Admin note")
                .setDescription("Galvanised sheet description")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(11L);
        galvanisedSheetEntity
                .setMaxLength("1000 MM")
                .setNumberOfSheets("10")
                .setQuantity("100 CM2")
                .setType("Galvanised sheet");
        return galvanisedSheetEntity;
    }

    public static InsulationDTO createInsulationDTO() {
        InsulationDTO insulationDTO = new InsulationDTO();
        insulationDTO.setId(12L)
                .setDescription("Insulation description")
                .setSpecificationFileUrl("https://insulation.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        insulationDTO
                .setThickness("100")
                .setThicknessUnit(LengthUnits.MM)
                .setQuantity("100")
                .setQuantityUnit(AreaUnits.CM2)
                .setType("Външна изолация");
        return insulationDTO;
    }

    public static InsulationEntity createInsulationEntity() {
        InsulationEntity insulationEntity = new InsulationEntity();
        insulationEntity
                .setDescription("Insulation description")
                .setSpecificationFileUrl("https://insulation.com")
                .setAdminNote("Admin note")
                .setDescription("Insulation description")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(12L);
        insulationEntity
                .setThickness("100 MM")
                .setQuantity("100 CM2")
                .setType("Външна изолация");
        return insulationEntity;
    }

    public static MetalDTO createMetalDTO() {
        MetalDTO metalDTO = new MetalDTO();
        metalDTO.setId(13L)
                .setDescription("Metal description")
                .setSpecificationFileUrl("https://metal.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        metalDTO
                .setTotalWeight("100")
                .setTotalWeightUnit(WeightUnits.KG)
                .setKind("Metal kind");
        return metalDTO;
    }

    public static MetalEntity createMetalEntity() {
        MetalEntity metalEntity = new MetalEntity();
        metalEntity
                .setDescription("Metal description")
                .setSpecificationFileUrl("https://metal.com")
                .setAdminNote("Admin note")
                .setDescription("Metal description")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(13L);
        metalEntity
                .setTotalWeight("100 KG")
                .setKind("Metal kind");
        return metalEntity;
    }

    public static PanelDTO createPanelDTO() {
        PanelDTO panelDTO = new PanelDTO();
        panelDTO.setId(14L)
                .setDescription("Panel description")
                .setSpecificationFileUrl("https://panel.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        panelDTO
                .setWidth("100")
                .setWidthUnit(LengthUnits.MM)
                .setTotalThickness("100")
                .setTotalThicknessUnit(LengthUnits.MM)
                .setFrontSheetThickness("100")
                .setFrontSheetThicknessUnit(LengthUnits.MM)
                .setBackSheetThickness("100")
                .setBackSheetThicknessUnit(LengthUnits.MM)
                .setLength("100")
                .setLengthUnit(LengthUnits.MM)
                .setQuantity("100")
                .setQuantityUnit(AreaUnits.CM2)
                .setType("Panel type");
        return panelDTO;
    }

    public static PanelEntity createPanelEntity() {
        PanelEntity panelEntity = new PanelEntity();
        panelEntity
                .setDescription("Panel description")
                .setSpecificationFileUrl("https://panel.com")
                .setAdminNote("Admin note")
                .setDescription("Panel description")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(14L);
        panelEntity
                .setWidth("100 MM")
                .setTotalThickness("100 MM")
                .setFrontSheetThickness("100 MM")
                .setBackSheetThickness("100 MM")
                .setLength("100 MM")
                .setQuantity("100 CM2")
                .setType("Panel type");
        return panelEntity;
    }

    public static RebarDTO createRebarDTO() {
        RebarDTO rebarDTO = new RebarDTO();
        rebarDTO.setId(15L)
                .setDescription("Rebar description")
                .setSpecificationFileUrl("https://rebar.com")
                .setAdminNote("Admin note")
                .setDescription("Rebar description")
                .setMaterialStatus("APPROVED");
        rebarDTO
                .setMaxLength("100")
                .setMaxLengthUnit(LengthUnits.MM)
                .setQuantity("100")
                .setQuantityUnit(WeightUnits.T);
        return rebarDTO;
    }

    public static RebarEntity createRebarEntity() {
        RebarEntity rebarEntity = new RebarEntity();
        rebarEntity
                .setDescription("Rebar description")
                .setSpecificationFileUrl("https://rebar.com")
                .setAdminNote("Admin note")
                .setDescription("Rebar description")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(15L);
        rebarEntity
                .setMaxLength("100 MM")
                .setQuantity("100 T");
        return rebarEntity;
    }

    public static ServiceDTO createServiceDTO() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(16L)
                .setDescription("Service description")
                .setSpecificationFileUrl("https://service.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        serviceDTO
                .setQuantity("100");
        return serviceDTO;
    }

    public static ServiceEntity createServiceEntity() {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity
                .setDescription("Service description")
                .setSpecificationFileUrl("https://service.com")
                .setAdminNote("Admin note")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(16L);
        serviceEntity
                .setQuantity("100");
        return serviceEntity;
    }

    public static SetDTO createSetDTO() {
        SetDTO setDTO = new SetDTO();
        setDTO.setId(17L)
                .setDescription("Set description")
                .setSpecificationFileUrl("https://set.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        setDTO
                .setQuantity("100")
                .setQuantityUnit(WeightUnits.KG)
                .setMaxLength("100")
                .setMaxLengthUnit(LengthUnits.MM);
        return setDTO;
    }

    public static SetEntity createSetEntity() {
        SetEntity setEntity = new SetEntity();
        setEntity
                .setDescription("Set description")
                .setSpecificationFileUrl("https://set.com")
                .setAdminNote("Admin note")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(17L);
        setEntity
                .setQuantity("100 KG")
                .setMaxLength("100 MM");
        return setEntity;


    }

    public static TransportDTO createTransportDTO() {
        TransportDTO transportDTO = new TransportDTO();
        transportDTO.setId(18L)
                .setDescription("Transport description")
                .setSpecificationFileUrl("https://transport.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        transportDTO
                .setQuantity("100")
                .setMaxLength("100")
                .setMaxLengthUnit(LengthUnits.MM)
                .setWeight("100")
                .setWeightUnit(WeightUnits.KG)
                .setTruck("Truck");
        return transportDTO;
    }

    public static TransportEntity createTransportEntity() {
        TransportEntity transportEntity = new TransportEntity();
        transportEntity
                .setDescription("Transport description")
                .setSpecificationFileUrl("https://transport.com")
                .setAdminNote("Admin note")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(18L);
        transportEntity
                .setQuantity("100")
                .setMaxLength("100 MM")
                .setWeight("100 KG")
                .setTruck("Truck");
        return transportEntity;
    }

    public static UnspecifiedDTO createUnspecifiedDTO() {
        UnspecifiedDTO unspecifiedDTO = new UnspecifiedDTO();
        unspecifiedDTO.setId(19L)
                .setDescription("Unspecified description")
                .setSpecificationFileUrl("https://unspecified.com")
                .setAdminNote("Admin note")
                .setMaterialStatus("APPROVED");
        unspecifiedDTO
                .setQuantity("100");
        return unspecifiedDTO;
    }

    public static UnspecifiedEntity createUnspecifiedEntity() {
        UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
        unspecifiedEntity
                .setDescription("Unspecified description")
                .setSpecificationFileUrl("https://unspecified.com")
                .setAdminNote("Admin note")
                .setMaterialStatus(MaterialStatus.APPROVED)
                .setId(19L);
        unspecifiedEntity
                .setQuantity("100");
        return unspecifiedEntity;
    }
}
