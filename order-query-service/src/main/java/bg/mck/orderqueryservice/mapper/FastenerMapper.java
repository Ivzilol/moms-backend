package bg.mck.orderqueryservice.mapper;

import bg.mck.orderqueryservice.entity.FastenerEntity;
import bg.mck.orderqueryservice.events.FasterEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastenerMapper {

    FastenerEntity fromFastenerEventToFastenerEntity(FasterEvent fasterEvent);
}
