package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.event.OrderEventType;
import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateOrderService {

    private final FastenerRepository fastenerRepository;

    private final OrderQueryServiceClient orderQueryServiceClient;

    private final FastenerMapper fastenerMapper;

    public UpdateOrderService(FastenerRepository fastenerRepository, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper) {
        this.fastenerRepository = fastenerRepository;
        this.orderQueryServiceClient = orderQueryServiceClient;
        this.fastenerMapper = fastenerMapper;
    }

    public void updateOrder(UpdateOrderDTO updateOrderDTO, String email) {
        if (updateOrderDTO.getMaterialType().equals(String.valueOf(MaterialType.FASTENERS))) {
            updateFastenerEntity(updateOrderDTO);
        }
    }

    private void updateFastenerEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<FastenerEntity> fastenerEntity = this.fastenerRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        fastenerMapper.toUpdateFasterEntity(updateOrderDTO, fastenerEntity.get());
        this.fastenerRepository.save(fastenerEntity.get());
        orderQueryServiceClient.sendUpdateEvent(updateOrderDTO, String.valueOf(OrderEventType.ORDER_UPDATED));

    }
}
