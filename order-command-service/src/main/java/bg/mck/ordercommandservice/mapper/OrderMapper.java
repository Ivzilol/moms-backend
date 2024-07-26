package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.CreateOrderDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        FastenerMapper.class,
        GalvanisedSheetMapper.class,
        InsulationMapper.class,
        MetalMapper.class,
        PanelMapper.class,
        RebarMapper.class,
        ServiceMapper.class,
        SetMapper.class,
        TransportMapper.class,
        UnspecifiedMapper.class})
public interface OrderMapper {

    OrderDTO toOrderDTO(OrderEntity order);

    OrderEntity toOrderEntity(OrderDTO orderDTO);

    CreateOrderEvent toEvent(OrderEntity OrderEntity);
}
