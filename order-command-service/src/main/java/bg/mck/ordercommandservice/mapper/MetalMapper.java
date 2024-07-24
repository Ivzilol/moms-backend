package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.MetalDTO;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.MetalEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MetalMapper {

    @Mappings({
            @Mapping(target = "totalWeight", expression = "java(split(metalEntity.getTotalWeight())[0])"),
            @Mapping(target = "totalWeightUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.WeightUnits.valueOf(split(metalEntity.getTotalWeight())[1]))")
    })
    MetalDTO toDTO(MetalEntity metalEntity);

    @Mapping(target = "totalWeight", expression = "java(concatenateWeight(metalDTO.getTotalWeight(), metalDTO.getTotalWeightUnit()))")
    MetalEntity toEntity(MetalDTO metalDTO);

    MetalEvent toEvent(MetalEntity metalEntity);

    default String concatenateWeight(String unit, WeightUnits unitType) {
        return unit + " " + unitType;
    }

    default String[] split(String unit) {
        return unit.split(" ");
    }
}
