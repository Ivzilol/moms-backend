package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.GalvanisedSheetDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.GalvanisedSheetEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GalvanisedSheetMapper extends Splitter, Concatenator {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(galvanisedSheetEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(galvanisedSheetEntity.getMaxLength())[1]))"),
            @Mapping(target = "quantity", expression = "java(split(galvanisedSheetEntity.getQuantity())[0])"),
            @Mapping(target = "quantityUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.AreaUnits.valueOf(split(galvanisedSheetEntity.getQuantity())[1]))")
    })
    GalvanisedSheetDTO toDTO(GalvanisedSheetEntity galvanisedSheetEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenate(galvanisedSheetDTO.getMaxLength(), galvanisedSheetDTO.getMaxLengthUnit()))"),
            @Mapping(target = "quantity", expression = "java(concatenate(galvanisedSheetDTO.getQuantity(), galvanisedSheetDTO.getQuantityUnit()))")
    })
    GalvanisedSheetEntity toEntity(GalvanisedSheetDTO galvanisedSheetDTO);

    GalvanisedSheetEvent toEvent(GalvanisedSheetEntity galvanisedSheetEntity);
}
