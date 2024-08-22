package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.InsulationDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.InsulationEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.InsulationEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsulationMapper extends Splitter, Concatenator {

    @Mappings({
            @Mapping(target = "thickness", expression = "java(split(insulationEntity.getThickness())[0])"),
            @Mapping(target = "thicknessUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(insulationEntity.getThickness())[1]))"),
            @Mapping(target = "quantity", expression = "java(split(insulationEntity.getQuantity())[0])"),
            @Mapping(target = "quantityUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.AreaUnits.valueOf(split(insulationEntity.getQuantity())[1]))")
    })
    InsulationDTO toDTO(InsulationEntity insulationEntity);

    @Mappings({
    @Mapping(target = "thickness", expression = "java(concatenate(insulationDTO.getThickness(), insulationDTO.getThicknessUnit()))"),
    @Mapping(target = "quantity", expression = "java(concatenate(insulationDTO.getQuantity(), insulationDTO.getQuantityUnit()))")
    })
    InsulationEntity toEntity(InsulationDTO insulationDTO);

    InsulationEvent toEvent(InsulationEntity insulationEntity);

}
