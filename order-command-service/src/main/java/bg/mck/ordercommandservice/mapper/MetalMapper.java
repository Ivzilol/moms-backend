package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.MetalDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.MetalEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MetalMapper extends Splitter, Concatenator {

    @Mappings({
            @Mapping(target = "totalWeight", expression = "java(split(metalEntity.getTotalWeight())[0])"),
            @Mapping(target = "totalWeightUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.WeightUnits.valueOf(split(metalEntity.getTotalWeight())[1]))")
    })
    MetalDTO toDTO(MetalEntity metalEntity);

    @Mapping(target = "totalWeight", expression = "java(concatenate(metalDTO.getTotalWeight(), metalDTO.getTotalWeightUnit()))")
    MetalEntity toEntity(MetalDTO metalDTO);

    MetalEvent toEvent(MetalEntity metalEntity);


}
