package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.*;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.event.EventType;
import bg.mck.ordercommandservice.mapper.*;
import bg.mck.ordercommandservice.repository.*;
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

    private final PanelRepository panelRepository;

    private final PanelMapper panelMapper;

    private final RebarRepository rebarRepository;

    private final RebarMapper rebarMapper;

    private final UnspecifiedRepository unspecifiedRepository;

    private final UnspecifiedMapper unspecifiedMapper;

    private final ServiceRepository serviceRepository;

    private final ServiceMapper serviceMapper;

    private final TransportRepository transportRepository;

    private final TransportMapper transportMapper;

    private final SetRepository setRepository;

    private final SetMapper setMapper;


    public UpdateOrderService(FastenerRepository fastenerRepository, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper, GalvanisedSheetRepository galvanisedSheetRepository, GalvanisedSheetMapper galvanisedSheetMapper, InsulationRepository insulationRepository, InsulationMapper insulationMapper, MetalRepository metalRepository, MetalMapper metalMapper, PanelRepository panelRepository, PanelMapper panelMapper, RebarRepository rebarRepository, RebarMapper rebarMapper, UnspecifiedRepository unspecifiedRepository, UnspecifiedMapper unspecifiedMapper, ServiceRepository serviceRepository, ServiceMapper serviceMapper, TransportRepository transportRepository, TransportMapper transportMapper, SetRepository setRepository, SetMapper setMapper) {
        this.fastenerRepository = fastenerRepository;
        this.orderQueryServiceClient = orderQueryServiceClient;
        this.fastenerMapper = fastenerMapper;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
        this.insulationRepository = insulationRepository;
        this.insulationMapper = insulationMapper;
        this.metalRepository = metalRepository;
        this.metalMapper = metalMapper;
        this.panelRepository = panelRepository;
        this.panelMapper = panelMapper;
        this.rebarRepository = rebarRepository;
        this.rebarMapper = rebarMapper;
        this.unspecifiedRepository = unspecifiedRepository;
        this.unspecifiedMapper = unspecifiedMapper;
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
        this.transportRepository = transportRepository;
        this.transportMapper = transportMapper;
        this.setRepository = setRepository;
        this.setMapper = setMapper;
    }

    @Transactional
    public void updateOrder(UpdateOrderDTO updateOrderDTO, String email) {
        switch (MaterialType.valueOf(updateOrderDTO.getMaterialType())) {
            case FASTENERS -> updateFastenerEntity(updateOrderDTO);
            case GALVANIZED_SHEET -> updateGalvanisedSheetEntity(updateOrderDTO);
            case INSULATION -> updateInsulationEntity(updateOrderDTO);
            case METAL -> updateMetalEntity(updateOrderDTO);
            case PANELS -> updatePanelsEntity(updateOrderDTO);
            case REBAR -> updateRebarEntity(updateOrderDTO);
            case UNSPECIFIED -> updateUnspecifiedEntity(updateOrderDTO);
            case SERVICE -> updateServiceEntity(updateOrderDTO);
            case TRANSPORT -> updateTransportEntity(updateOrderDTO);
            case SET -> updateSetEntity(updateOrderDTO);
        }
        sendEvent(updateOrderDTO);
    }

    private void updateSetEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<SetEntity> setEntity = this.setRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        setMapper.toUpdateSetEntity(updateOrderDTO, setEntity.get());
        this.setRepository.save(setEntity.get());
    }

    private void updateTransportEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<TransportEntity> transportEntity = this.transportRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        transportMapper.toUpdateTransportEntity(updateOrderDTO, transportEntity.get());
        this.transportRepository.save(transportEntity.get());
    }

    private void updateServiceEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<ServiceEntity> serviceEntity = this.serviceRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        serviceMapper.toUpdateServiceEntity(updateOrderDTO, serviceEntity.get());
        this.serviceRepository.save(serviceEntity.get());
    }

    private void updateUnspecifiedEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<UnspecifiedEntity> unspecifiedEntity = this.unspecifiedRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        unspecifiedMapper.toUpdateUnspecifiedEntity(updateOrderDTO, unspecifiedEntity.get());
        this.unspecifiedRepository.save(unspecifiedEntity.get());
    }

    private void updateRebarEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<RebarEntity> rebarEntity = this.rebarRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        rebarMapper.toUpdateRebarEntity(updateOrderDTO, rebarEntity.get());
        this.rebarRepository.save(rebarEntity.get());
    }

    private void updatePanelsEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<PanelEntity> panelEntity = this.panelRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        panelMapper.toUpdatePanelEntity(updateOrderDTO, panelEntity.get());
        this.panelRepository.save(panelEntity.get());
    }

    private void updateMetalEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<MetalEntity> metalEntity = this.metalRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        metalMapper.toUpdateMetalEntity(updateOrderDTO, metalEntity.get());
        this.metalRepository.save(metalEntity.get());
    }

    private void updateInsulationEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<InsulationEntity> insulationEntity = this.insulationRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        insulationMapper.toUpdateInsulationEntity(updateOrderDTO, insulationEntity.get());
        this.insulationRepository.save(insulationEntity.get());
    }

    private void updateFastenerEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<FastenerEntity> fastenerEntity = this.fastenerRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        fastenerMapper.toUpdateFasterEntity(updateOrderDTO, fastenerEntity.get());
        this.fastenerRepository.save(fastenerEntity.get());
    }

    private void updateGalvanisedSheetEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<GalvanisedSheetEntity> galvanisedSheetEntity = this.galvanisedSheetRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        galvanisedSheetMapper.toUpdateGalvanisedSheetEntity(updateOrderDTO, galvanisedSheetEntity.get());
        this.galvanisedSheetRepository.save(galvanisedSheetEntity.get());
    }

    private void sendEvent(UpdateOrderDTO updateOrderDTO) {
        try {
            orderQueryServiceClient.sendUpdateEvent(updateOrderDTO, String.valueOf(EventType.ORDER_UPDATED));
        } catch (Exception e) {
            throw new RuntimeException("Failed to update order in external service", e);
        }
    }
}
