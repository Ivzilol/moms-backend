package mapper;

import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(OrderEntity order);

    OrderEntity toOrderEntity(OrderDTO orderDTO);

}
