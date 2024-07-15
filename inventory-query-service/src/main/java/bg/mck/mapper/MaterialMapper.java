package bg.mck.mapper;

import bg.mck.dto.FastenersDTO;
import bg.mck.dto.GalvanisedDTO;
import bg.mck.dto.InsulationDTO;
import bg.mck.dto.MetalDTO;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.entity.materialEntity.GalvanisedSheetEntity;
import bg.mck.entity.materialEntity.InsulationEntity;
import bg.mck.entity.materialEntity.MetalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    FastenerEntity toFastenerEntity(FastenersDTO fastenersDTO);
    FastenersDTO toFastenerDTO(FastenerEntity fastenerEntity);

    GalvanisedSheetEntity toGalvanizedEntity(GalvanisedDTO galvanisedDTO);
    GalvanisedDTO toGalvanizedDTO(GalvanisedSheetEntity galvanisedEntity);

    InsulationEntity toInsulationEntity(InsulationDTO insulationDTO);
    InsulationDTO toInsulationDTO(InsulationEntity insulationEntity);

    MetalEntity toMetalEntity(MetalDTO metalDTO);
    MetalDTO toMetalDTO(MetalEntity metalEntity);





}
