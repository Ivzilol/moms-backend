package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConstructionSiteMapper {

    ConstructionSiteDTO toDTO(ConstructionSiteEntity constructionSiteEntity);

    ConstructionSiteEntity toEntity(ConstructionSiteDTO constructionSiteDTO);
}
