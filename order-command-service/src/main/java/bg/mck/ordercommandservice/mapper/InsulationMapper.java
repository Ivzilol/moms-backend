package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.InsulationDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.InsulationEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.InsulationEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsulationMapper {

    @Mappings({
            @Mapping(target = "thickness", expression = "java(splitLength(insulationEntity.getThickness())[0])"),
            @Mapping(target = "thicknessUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(splitLength(insulationEntity.getThickness())[1]))")
    })
    InsulationDTO toDTO(InsulationEntity insulationEntity);

    @Mapping(target = "thickness", expression = "java(concatenateLength(insulationDTO.getThickness(), insulationDTO.getThicknessUnit()))")
    InsulationEntity toEntity(InsulationDTO insulationDTO);

    InsulationEvent toEvent(InsulationEntity insulationEntity);

    void toUpdateInsulationEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget InsulationEntity insulationEntity);

    default String concatenateLength(String length, LengthUnits lengthUnit) {
        return length + " " + lengthUnit;
    }

    default String[] splitLength(String length) {
        return length.split(" ");
    }

}
