package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.SetDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.SetEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.SetEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SetMapper extends Splitter, Concatenator {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(setEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(setEntity.getMaxLength())[1]))"),
            @Mapping(target = "quantity", expression = "java(split(setEntity.getQuantity())[0])"),
            @Mapping(target = "quantityUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.WeightUnits.valueOf(split(setEntity.getQuantity())[1]))")
    })
    SetDTO toDTO(SetEntity setEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenate(setDTO.getMaxLength(), setDTO.getMaxLengthUnit()))"),
            @Mapping(target = "quantity", expression = "java(concatenate(setDTO.getQuantity(), setDTO.getQuantityUnit()))")
    })
    SetEntity toEntity(SetDTO setDTO);

    SetEvent toEvent(SetEntity setEntity);

}
