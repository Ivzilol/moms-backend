package bg.mck.mapper;

import bg.mck.dto.ConstructionSiteDTO;
import bg.mck.dto.TransportDTO;
import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.entity.transportEntity.TransportEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConstructionSiteMapper {

    ConstructionSiteEntity toConstructionEntity(ConstructionSiteDTO constructionSiteDTO);
    ConstructionSiteDTO toConstructionDTO(ConstructionSiteEntity constructionSiteEntity);
    List<ConstructionSiteDTO> toConstructionDTOs(List<ConstructionSiteEntity> constructionSiteEntities);

}
