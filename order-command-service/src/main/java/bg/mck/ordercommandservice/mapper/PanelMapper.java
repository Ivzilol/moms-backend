package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.PanelDTO;
import bg.mck.ordercommandservice.entity.PanelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PanelMapper {

    PanelDTO toDTO(PanelEntity panelEntity);

    PanelEntity toEntity(PanelDTO panelDTO);
}
