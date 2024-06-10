package bg.mck.momsbackend.application.mapper;

import bg.mck.momsbackend.application.dto.OrderDTO;
import bg.mck.momsbackend.application.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDTO orderDTO);
    OrderDTO toDTO(Order order);
}
