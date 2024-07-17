package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.CreateOrderDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(OrderEntity order);

    OrderEntity toOrderEntity(OrderDTO orderDTO);

    CreateOrderEvent toEvent(OrderEntity OrderEntity);
}