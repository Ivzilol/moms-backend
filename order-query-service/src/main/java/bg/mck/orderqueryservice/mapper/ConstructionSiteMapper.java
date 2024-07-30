package bg.mck.orderqueryservice.mapper;

import bg.mck.orderqueryservice.dto.ConstructionSiteDTO;
import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import bg.mck.orderqueryservice.events.ConstructionSiteEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConstructionSiteMapper {

    ConstructionSiteEntity toEntityFromEvent(ConstructionSiteEvent constructionSiteEvent);

    ConstructionSiteEntity toEntity(ConstructionSiteDTO constructionSiteDTO);

    ConstructionSiteDTO toDto(ConstructionSiteEntity constructionSiteEntity);
}
