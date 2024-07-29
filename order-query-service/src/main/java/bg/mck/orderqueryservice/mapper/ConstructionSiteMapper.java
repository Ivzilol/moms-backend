package bg.mck.orderqueryservice.mapper;

import bg.mck.orderqueryservice.dto.ConstructionSiteDTO;
import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConstructionSiteMapper {

    ConstructionSiteEntity toEntity(ConstructionSiteDTO constructionSiteDTO);

    ConstructionSiteDTO toDto(ConstructionSiteEntity constructionSiteEntity);
}
