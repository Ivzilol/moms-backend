package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.RebarDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.RebarEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.RebarEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RebarMapper extends Splitter, Concatenator {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(rebarEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(rebarEntity.getMaxLength())[1]))"),
            @Mapping(target = "quantity", expression = "java(split(rebarEntity.getQuantity())[0])"),
            @Mapping(target = "quantityUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.WeightUnits.valueOf(split(rebarEntity.getQuantity())[1]))")
    })
    RebarDTO toDTO(RebarEntity rebarEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenate(rebarDTO.getMaxLength(), rebarDTO.getMaxLengthUnit()))"),
            @Mapping(target = "quantity", expression = "java(concatenate(rebarDTO.getQuantity(), rebarDTO.getQuantityUnit()))")
    })
    RebarEntity toEntity(RebarDTO rebarDTO);

    RebarEvent toEvent(RebarEntity rebarEntity);
}
