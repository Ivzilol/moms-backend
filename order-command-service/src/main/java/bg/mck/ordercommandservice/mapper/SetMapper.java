package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.SetDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.SetEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.SetEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SetMapper {

    @Mappings({
            @Mapping(target = "galvanisedSheetThickness", expression = "java(split(setEntity.getGalvanisedSheetThickness())[0])"),
            @Mapping(target = "galvanisedSheetThicknessUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(setEntity.getGalvanisedSheetThickness())[1]))"),
            @Mapping(target = "maxLength", expression = "java(split(setEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(setEntity.getMaxLength())[1]))")
    })
    SetDTO toDTO(SetEntity setEntity);

    @Mappings({
            @Mapping(target = "galvanisedSheetThickness", expression = "java(concatenate(setDTO.getGalvanisedSheetThickness(), setDTO.getGalvanisedSheetThicknessUnit()))"),
            @Mapping(target = "maxLength", expression = "java(concatenate(setDTO.getMaxLength(), setDTO.getMaxLengthUnit()))")
    })
    SetEntity toEntity(SetDTO setDTO);

    SetEvent toEvent(SetEntity setEntity);

    void toUpdateSetEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget SetEntity setEntity);

    default String concatenate(String length, LengthUnits lengthUnit) {
        if (length == null && lengthUnit == null) {
            return null;
        }
        if (length == null) {
            return lengthUnit.toString();
        }
        if (lengthUnit == null) {
            return length;
        }
        return length + " " + lengthUnit;
    }

    default String[] split(String length) {
        if (length == null) {
            return null;
        }
        return length.split(" ");
    }
}
