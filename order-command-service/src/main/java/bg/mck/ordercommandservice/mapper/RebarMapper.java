package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.RebarDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.RebarEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.RebarEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RebarMapper {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(rebarEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(rebarEntity.getMaxLength())[1]))"),
            @Mapping(target = "weight", expression = "java(split(rebarEntity.getWeight())[0])"),
            @Mapping(target = "weightUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.WeightUnits.valueOf(split(rebarEntity.getWeight())[1]))")
    })
    RebarDTO toDTO(RebarEntity rebarEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenateLength(rebarDTO.getMaxLength(), rebarDTO.getMaxLengthUnit()))"),
            @Mapping(target = "weight", expression = "java(concatenateWeight(rebarDTO.getWeight(), rebarDTO.getWeightUnit()))")
    })
    RebarEntity toEntity(RebarDTO rebarDTO);

    RebarEvent toEvent(RebarEntity rebarEntity);

    void toUpdateRebarEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget RebarEntity rebarEntity);

    default String concatenateLength(String length, LengthUnits lengthUnit) {
        return length + " " + lengthUnit;
    }

    default String concatenateWeight(String length, WeightUnits weightUnits) {
        return length + " " + weightUnits;
    }

    default String[] split(String length) {
        return length.split(" ");
    }
}
