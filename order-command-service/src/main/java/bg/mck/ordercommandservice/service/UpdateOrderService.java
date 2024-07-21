package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.entity.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.event.OrderEventType;
import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.mapper.GalvanisedSheetMapper;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import bg.mck.ordercommandservice.repository.GalvanisedSheetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateOrderService {

    private final FastenerRepository fastenerRepository;

    private final OrderQueryServiceClient orderQueryServiceClient;

    private final FastenerMapper fastenerMapper;

    private final GalvanisedSheetRepository galvanisedSheetRepository;

    private final GalvanisedSheetMapper galvanisedSheetMapper;

    public UpdateOrderService(FastenerRepository fastenerRepository, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper, GalvanisedSheetRepository galvanisedSheetRepository, GalvanisedSheetMapper galvanisedSheetMapper) {
        this.fastenerRepository = fastenerRepository;
        this.orderQueryServiceClient = orderQueryServiceClient;
        this.fastenerMapper = fastenerMapper;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
    }

    @Transactional
    public void updateOrder(UpdateOrderDTO updateOrderDTO, String email) {
        switch (MaterialType.valueOf(updateOrderDTO.getMaterialType())) {
            case FASTENERS:
                updateFastenerEntity(updateOrderDTO);
            case GALVANIZED_SHEET:
                updateGalvanisedSheetEntity(updateOrderDTO);
            case INSULATION:
            case METAL:
            case PANELS:
            case REBAR:
            case UNSPECIFIED:
            case SERVICE:
            case TRANSPORT:
        }
    }

    private void updateFastenerEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<FastenerEntity> fastenerEntity = this.fastenerRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        fastenerMapper.toUpdateFasterEntity(updateOrderDTO, fastenerEntity.get());
        this.fastenerRepository.save(fastenerEntity.get());
        sendEvent(updateOrderDTO);
    }

    private void updateGalvanisedSheetEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<GalvanisedSheetEntity> galvanisedSheetEntity = this.galvanisedSheetRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        galvanisedSheetMapper.toUpdateGalvanisedSheetEntity(updateOrderDTO, galvanisedSheetEntity.get());
        this.galvanisedSheetRepository.save(galvanisedSheetEntity.get());
        sendEvent(updateOrderDTO);
    }

    private void sendEvent(UpdateOrderDTO updateOrderDTO) {
        try {
            orderQueryServiceClient.sendUpdateEvent(updateOrderDTO, String.valueOf(OrderEventType.ORDER_UPDATED));
        } catch (Exception e) {
            throw new RuntimeException("Failed to update order in external service", e);
        }
    }
}
