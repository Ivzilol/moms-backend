package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.GalvanisedSheetDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.event.GalvanisedSheetEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GalvanisedSheetMapper {

    GalvanisedSheetDTO toDTO(GalvanisedSheetEntity galvanisedSheetEntity);

    GalvanisedSheetEntity toEntity(GalvanisedSheetDTO galvanisedSheetDTO);

    GalvanisedSheetEvent toEvent(GalvanisedSheetEntity galvanisedSheetEntity);

    void toUpdateGalvanisedSheetEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget GalvanisedSheetEntity galvanisedSheetEntity);
}
