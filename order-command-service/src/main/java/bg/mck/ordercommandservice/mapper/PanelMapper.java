package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.Material.PanelDTO;
import bg.mck.ordercommandservice.entity.material.PanelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PanelMapper {

    PanelDTO toDTO(PanelEntity panelEntity);

    PanelEntity toEntity(PanelDTO panelDTO);
}
