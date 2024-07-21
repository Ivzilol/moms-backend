package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.entity.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.entity.InsulationEntity;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.event.OrderEventType;
import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.mapper.GalvanisedSheetMapper;
import bg.mck.ordercommandservice.mapper.InsulationMapper;
import bg.mck.ordercommandservice.mapper.MetalMapper;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import bg.mck.ordercommandservice.repository.GalvanisedSheetRepository;
import bg.mck.ordercommandservice.repository.InsulationRepository;
import bg.mck.ordercommandservice.repository.MetalRepository;
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

    private final InsulationRepository insulationRepository;

    private final InsulationMapper insulationMapper;

    private final MetalRepository metalRepository;

    private final MetalMapper metalMapper;


    public UpdateOrderService(FastenerRepository fastenerRepository, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper, GalvanisedSheetRepository galvanisedSheetRepository, GalvanisedSheetMapper galvanisedSheetMapper, InsulationRepository insulationRepository, InsulationMapper insulationMapper, MetalRepository metalRepository, MetalMapper metalMapper) {
        this.fastenerRepository = fastenerRepository;
        this.orderQueryServiceClient = orderQueryServiceClient;
        this.fastenerMapper = fastenerMapper;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
        this.insulationRepository = insulationRepository;
        this.insulationMapper = insulationMapper;
        this.metalRepository = metalRepository;
        this.metalMapper = metalMapper;
    }

    @Transactional
    public void updateOrder(UpdateOrderDTO updateOrderDTO, String email) {
        switch (MaterialType.valueOf(updateOrderDTO.getMaterialType())) {
            case FASTENERS:
                updateFastenerEntity(updateOrderDTO);
            case GALVANIZED_SHEET:
                updateGalvanisedSheetEntity(updateOrderDTO);
            case INSULATION:
                updateInsulationEntity(updateOrderDTO);
            case METAL:
                updateMetalEntity(updateOrderDTO);
            case PANELS:
//                updatePanelsEntity(updateOrderDTO);
            case REBAR:
            case UNSPECIFIED:
            case SERVICE:
            case TRANSPORT:
        }
    }

    private void updateMetalEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<MetalEntity> metalEntity = this.metalRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        metalMapper.toUpdateMetalEntity(updateOrderDTO, metalEntity.get());
        this.metalRepository.save(metalEntity.get());
        sendEvent(updateOrderDTO);
    }

    private void updateInsulationEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<InsulationEntity> insulationEntity = this.insulationRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        insulationMapper.toUpdateInsulationEntity(updateOrderDTO, insulationEntity.get());
        this.insulationRepository.save(insulationEntity.get());
        sendEvent(updateOrderDTO);
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
