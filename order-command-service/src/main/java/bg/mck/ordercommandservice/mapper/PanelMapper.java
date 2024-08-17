package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.PanelDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.PanelEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.PanelEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PanelMapper {

    @Mappings({
            @Mapping(target = "length", expression = "java(split(panelEntity.getLength())[0])"),
            @Mapping(target = "lengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(panelEntity.getLength())[1]))"),
            @Mapping(target = "width", expression = "java(split(panelEntity.getWidth())[0])"),
            @Mapping(target = "widthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(panelEntity.getWidth())[1]))"),
            @Mapping(target = "totalThickness", expression = "java(split(panelEntity.getTotalThickness())[0])"),
            @Mapping(target = "totalThicknessUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(panelEntity.getTotalThickness())[1]))"),
            @Mapping(target = "frontSheetThickness", expression = "java(split(panelEntity.getFrontSheetThickness())[0])"),
            @Mapping(target = "frontSheetThicknessUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(panelEntity.getFrontSheetThickness())[1]))"),
            @Mapping(target = "backSheetThickness", expression = "java(split(panelEntity.getBackSheetThickness())[0])"),
            @Mapping(target = "backSheetThicknessUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(panelEntity.getBackSheetThickness())[1]))"),
            @Mapping(target = "quantity", expression = "java(split(panelEntity.getQuantity())[0])"),
            @Mapping(target = "quantityUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.AreaUnits.valueOf(split(panelEntity.getQuantity())[1]))")

    })
    PanelDTO toDTO(PanelEntity panelEntity);

    @Mappings({
            @Mapping(target = "length", expression = "java(concatenate(panelDTO.getLength(), panelDTO.getLengthUnit()))"),
            @Mapping(target = "width", expression = "java(concatenate(panelDTO.getWidth(), panelDTO.getWidthUnit()))"),
            @Mapping(target = "totalThickness", expression = "java(concatenate(panelDTO.getTotalThickness(), panelDTO.getTotalThicknessUnit()))"),
            @Mapping(target = "frontSheetThickness", expression = "java(concatenate(panelDTO.getFrontSheetThickness(), panelDTO.getFrontSheetThicknessUnit()))"),
            @Mapping(target = "backSheetThickness", expression = "java(concatenate(panelDTO.getBackSheetThickness(), panelDTO.getBackSheetThicknessUnit()))"),
            @Mapping(target = "quantity", expression = "java(concatenate(panelDTO.getQuantity(), panelDTO.getQuantityUnit()))"),
    })
    PanelEntity toEntity(PanelDTO panelDTO);

    PanelEvent toEvent(PanelEntity panelEntity);

    void toUpdatePanelEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget PanelEntity panelEntity);

    default String concatenate(String unit, LengthUnits unitType) {
        if (unit == null && unitType == null) {
            return null;
        }
        if (unit == null) {
            return unitType.toString();
        }
        if (unitType == null) {
            return unit;
        }
        return unit + " " + unitType;
    }

    default String concatenate(String unit, AreaUnits unitType) {
        if (unit == null && unitType == null) {
            return null;
        }
        if (unit == null) {
            return unitType.toString();
        }
        if (unitType == null) {
            return unit;
        }
        return unit + " " + unitType;
    }

    default String[] split(String unit) {
        if (unit == null) {
            return null;
        }
        return unit.split(" ");
    }
}
