package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.mapper.OrderMapper;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ConstructionSiteService constructionSiteService;
    private final MaterialService materialService;
    private final ServiceService serviceService;
    private final TransportService transportService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ConstructionSiteService constructionSiteService, MaterialService materialService, ServiceService serviceService, TransportService transportService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.constructionSiteService = constructionSiteService;
        this.materialService = materialService;
        this.serviceService = serviceService;
        this.transportService = transportService;
        this.orderMapper = orderMapper;
    }


    public OrderDTO getOrder(Long id) {
        Optional<OrderEntity> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {
            Long constructionSiteId = orderById.get().getConstructionSite().getId();
            Long materialsId = orderById.get().getMaterials().getId();
            Long servicesId = orderById.get().getServices().getId();
            Long transportsId = orderById.get().getTransports().getId();


            OrderDTO orderDTO = orderMapper.toOrderDTO(orderById.get());
            orderDTO.setConstructionSite(constructionSiteService.getConstructionSite(constructionSiteId));
            orderDTO.setMaterial(materialService.getMaterialById(materialsId));
            orderDTO.setService(serviceService.getServiceById(servicesId));
            orderDTO.setTransport(transportService.getTransportById(transportsId));
            return orderDTO;
        }
        throw new OrderNotFoundException("Order with id " + id + " not found");
    }

    public Object createOrder(OrderDTO order, String email) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);

        Optional<Integer> lastOrderNumber = orderRepository.findLastOrderNumber();

        orderEntity.setUsername(email);
        orderEntity.setOrderNumber(lastOrderNumber.orElse(0) + 1);

        constructionSiteService.getConstructionSiteByNumberAndName(order.getConstructionSite());
        materialService.saveMaterial(order.getMaterial());
        serviceService.saveService(order.getService());
        transportService.saveTransport(order.getTransport());

        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} created successfully", orderEntity.getId());
        return null;

    }
}
