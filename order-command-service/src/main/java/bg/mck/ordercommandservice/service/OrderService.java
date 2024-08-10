package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.*;
import bg.mck.ordercommandservice.entity.*;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.*;
import bg.mck.ordercommandservice.mapper.*;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;


import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final OrderRepository orderRepository;
    private final ConstructionSiteService constructionSiteService;
    private final OrderMapper orderMapper;
    private final OrderQueryServiceClient orderQueryServiceClient;
    private final FastenerMapper fastenerMapper;
    private final GalvanisedSheetMapper galvanisedSheetMapper;
    private final InsulationMapper insulationMapper;
    private final MetalMapper metalMapper;
    private final PanelMapper panelMapper;
    private final RebarMapper rebarMapper;
    private final ServiceMapper serviceMapper;
    private final SetMapper setMapper;
    private final TransportMapper transportMapper;
    private final UnspecifiedMapper unspecifiedMapper;
    private final MaterialService materialService;
    private RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, ConstructionSiteService constructionSiteService, OrderMapper orderMapper, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper, GalvanisedSheetMapper galvanisedSheetMapper, InsulationMapper insulationMapper, MetalMapper metalMapper, PanelMapper panelMapper, RebarMapper rebarMapper, ServiceMapper serviceMapper, SetMapper setMapper, TransportMapper transportMapper, UnspecifiedMapper unspecifiedMapper, MaterialService materialService, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.constructionSiteService = constructionSiteService;
        this.orderMapper = orderMapper;
        this.orderQueryServiceClient = orderQueryServiceClient;
        this.fastenerMapper = fastenerMapper;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
        this.insulationMapper = insulationMapper;
        this.metalMapper = metalMapper;
        this.panelMapper = panelMapper;
        this.rebarMapper = rebarMapper;
        this.serviceMapper = serviceMapper;
        this.setMapper = setMapper;
        this.transportMapper = transportMapper;
        this.unspecifiedMapper = unspecifiedMapper;
        this.materialService = materialService;
        this.restTemplate = restTemplate;
    }


    public OrderDTO getOrder(Long id) { //FIXME: remove after order query service is implemented
        Optional<OrderEntity> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {
            Long constructionSiteId = orderById.get().getConstructionSite().getId();

            OrderDTO orderDTO = orderMapper.toOrderDTO(orderById.get());
            orderDTO.setConstructionSite(constructionSiteService.getConstructionSite(constructionSiteId));

            return orderDTO;
        }
        throw new OrderNotFoundException("Order with id " + id + " not found");
    }

    @Transactional
    public OrderConfirmationDTO createOrder(OrderDTO order, String email, List<FileDTO> fileUrls) {

        matchFilesToMaterials(order, fileUrls);

        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        ConstructionSiteEntity constructionSiteByNumberAndName = constructionSiteService.getConstructionSiteByNumberAndName(order.getConstructionSite());
        Optional<Integer> lastOrderNumber = orderRepository.findLastOrderNumber();

        orderEntity.setEmail(email)
                .setOrderNumber(lastOrderNumber.orElse(0) + 1)
                .setOrderStatus(OrderStatus.CREATED)
                .setOrderDate(ZonedDateTime.now(ZoneId.of("Europe/Sofia")).plusHours(3)) //FIXME: find a better way to set the time and timezone
                .setConstructionSite(constructionSiteByNumberAndName);

        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} created successfully", orderEntity.getId());

        return createOrderEvent(orderEntity);
    }

    @Transactional
    public OrderConfirmationDTO updateOrder(OrderDTO order, String email, List<MultipartFile> files) {

//        if (!files.isEmpty()) {
//            //TODO: implement file upload
//            List<String> filesUrl = uploadFiles(files);
//            //TODO: implement matching files to materials
//            matchFilesToMaterials(order, filesUrl);
//        }

        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        ConstructionSiteEntity constructionSiteByName =
                constructionSiteService.getConstructionSiteByName(order.getConstructionSite().getName());

        orderEntity
                .setEmail(email)
                .setConstructionSite(constructionSiteByName);

//        Set<FastenerEntity> fastenerEntities = new HashSet<>();
//        order.getFasteners().forEach(fastener -> {
//            FastenerEntity fastenerEntity = fastenerMapper.toEntity(fastener);
//            fastenerEntities.add(fastenerEntity);
//        });
//        orderEntity.setFasteners(fastenerEntities);

        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} updated successfully", orderEntity.getId());

        return createOrderEvent(orderEntity);
    }


    public OrderConfirmationDTO deleteOrder(Long order, String email) {
        OrderEntity orderEntity = orderRepository.findById(order)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + order + " not found"));
        orderEntity.setOrderStatus(OrderStatus.CANCELLED)
                .setEmail(email);
        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} cancelled successfully", orderEntity.getId());

        return createOrderEvent(orderEntity);
    }

    public OrderConfirmationDTO restoreOrder(Long orderId, String email) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        orderEntity.setOrderStatus(OrderStatus.PENDING)
                .setEmail(email);
        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} restored successfully", orderEntity.getId());

        return createOrderEvent(orderEntity);
    }

    public OrderConfirmationDTO deleteMaterial(Long orderId, Long materialId, String email) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));

        materialService.deleteMaterial(materialId, orderEntity.getMaterialType().toString());

        LOGGER.info("Material with id {} deleted from order with id {}", materialId, orderId);

        return createOrderEvent(orderEntity);
    }

    public OrderConfirmationDTO updateOrderStatus(OrderDTO order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + order.getId() + " not found"));
        orderEntity.setOrderStatus(order.getOrderStatus());
        updateMaterialStatus(orderEntity, order);
        orderRepository.save(orderEntity);
        return createOrderEvent(orderEntity);
    }

    private void updateMaterialStatus(OrderEntity orderEntity, OrderDTO order) {
        switch (order.getMaterialType()) {
            case FASTENERS -> updateMaterialStatus(orderEntity.getFasteners(), order.getFasteners());
            case GALVANIZED_SHEET ->
                    updateMaterialStatus(orderEntity.getGalvanisedSheets(), order.getGalvanisedSheets());
            case INSULATION -> updateMaterialStatus(orderEntity.getInsulation(), order.getInsulation());
            case METAL -> updateMaterialStatus(orderEntity.getMetals(), order.getMetals());
            case PANELS -> updateMaterialStatus(orderEntity.getPanels(), order.getPanels());
            case REBAR -> updateMaterialStatus(orderEntity.getRebars(), order.getRebars());
            case SERVICE -> updateMaterialStatus(orderEntity.getServices(), order.getServices());
            case SET -> updateMaterialStatus(orderEntity.getSets(), order.getSets());
            case TRANSPORT -> updateMaterialStatus(orderEntity.getTransports(), order.getTransports());
            case UNSPECIFIED -> updateMaterialStatus(orderEntity.getUnspecified(), order.getUnspecified());
        }
    }

    private void updateMaterialStatus(Set<? extends BaseMaterialEntity> materials, List<? extends BaseDTO> materialsDTO) {
        materials.forEach(material -> {
            materialsDTO.forEach(materialDTO -> {
                if (material.getId().equals(materialDTO.getId())) {
                    material.setAdminNote(materialDTO.getAdminNote());
                    if (materialDTO.getMaterialStatus() != null) {
                        material.setMaterialStatus(Enum.valueOf(MaterialStatus.class, materialDTO.getMaterialStatus()));
                    }
                }
            });
        });
    }

    private static void matchFilesToMaterials(OrderDTO order, List<FileDTO> filesUrl) {
        if (filesUrl == null || filesUrl.isEmpty()) {
            return;
        }


        for (FileDTO fileDTO : filesUrl) {
            String fileMatchingPattern = getFileMatchingPattern(fileDTO.getFileName());
            if (fileMatchingPattern.equals("000")) {
                order.setSpecificationFileUrl(fileDTO.getFileUrl());
                continue;
            }

            switch (order.getMaterialType()) {
                case FASTENERS -> addFileUrlToMaterial(order.getFasteners(), fileDTO);
                case GALVANIZED_SHEET -> addFileUrlToMaterial(order.getGalvanisedSheets(), fileDTO);
                case INSULATION -> addFileUrlToMaterial(order.getInsulation(), fileDTO);
                case METAL -> addFileUrlToMaterial(order.getMetals(), fileDTO);
                case PANELS -> addFileUrlToMaterial(order.getPanels(), fileDTO);
                case REBAR -> addFileUrlToMaterial(order.getRebars(), fileDTO);
                case SERVICE -> addFileUrlToMaterial(order.getServices(), fileDTO);
                case SET -> addFileUrlToMaterial(order.getSets(), fileDTO);
                case TRANSPORT -> addFileUrlToMaterial(order.getTransports(), fileDTO);
                case UNSPECIFIED -> addFileUrlToMaterial(order.getUnspecified(), fileDTO);
            }
        }
    }

    private static void addFileUrlToMaterial(List<?> currentMaterials, FileDTO fileDTO) {

        String fileMatchingPattern = getFileMatchingPattern(fileDTO.getFileName());

        BaseDTO baseDTO = (BaseDTO) currentMaterials.get(Integer.parseInt(fileMatchingPattern) - 1);
        baseDTO.setSpecificationFileUrl(fileDTO.getFileUrl());

    }

    private static String getFileMatchingPattern(String fileName) {
        String pattern = "(\\d{3})(?=\\.[^\\.]+$)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(fileName);

        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }

    private static List<?> getMaterialSet(OrderDTO order) {
        return switch (order.getMaterialType()) {
            case FASTENERS -> order.getFasteners();
            case GALVANIZED_SHEET -> order.getGalvanisedSheets();
            case INSULATION -> order.getInsulation();
            case METAL -> order.getMetals();
            case PANELS -> order.getPanels();
            case REBAR -> order.getRebars();
            case SERVICE -> order.getServices();
            case SET -> order.getSets();
            case TRANSPORT -> order.getTransports();
            case UNSPECIFIED -> order.getUnspecified();
            default -> throw new IllegalArgumentException("Material type not found");
        };
    }

    private OrderConfirmationDTO createOrderEvent(OrderEntity orderEntity) {
        orderEntity = orderRepository.findById(orderEntity.getId()).get();
        mapEvent(orderEntity);

        return new OrderConfirmationDTO.Builder()
                .orderStatus(orderEntity.getOrderStatus())
                .orderId(orderEntity.getId())
                .orderNumber(orderEntity.getOrderNumber())
                .constructionSiteName(orderEntity.getConstructionSite().getName())
                .constructionSiteNumber(orderEntity.getConstructionSite().getConstructionNumber())
                .build();
    }

    private void mapEvent(OrderEntity orderEntity) {
        processAndSendEvent(orderEntity, orderEntity.getFasteners(), fastenerMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getGalvanisedSheets(), galvanisedSheetMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getMetals(), metalMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getPanels(), panelMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getRebars(), rebarMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getServices(), serviceMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getSets(), setMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getTransports(), transportMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getInsulation(), insulationMapper::toEvent);
        processAndSendEvent(orderEntity, orderEntity.getUnspecified(), unspecifiedMapper::toEvent);
    }

    private <M, E> void processAndSendEvent(OrderEntity orderEntity, Set<M> materials, Function<M, E> mapper) {
        if (materials == null || materials.isEmpty()) {
            return;
        }

        Set<E> materialEvents = materials.stream()
                .map(mapper)
                .collect(Collectors.toSet());

        EventData<CreateOrderEvent<E>> orderEvent = new EventData<>();

        orderEvent.setEventType(orderEntity.getOrderStatus() != OrderStatus.CREATED ? EventType.ORDER_UPDATED : EventType.ORDER_CREATED);

        CreateOrderEvent<E> createOrderEvent = orderMapper.toEvent(orderEntity);
        createOrderEvent.setOrderId(orderEntity.getId());
        createOrderEvent.setEventType(orderEvent.getEventType());
        createOrderEvent.setEventTime(LocalDateTime.now());
        createOrderEvent.setMaterials(materialEvents);
        createOrderEvent.setEmail(orderEntity.getEmail());
        orderEvent.setEvent(createOrderEvent);

        orderQueryServiceClient.sendEvent(orderEvent, orderEvent.getEventType().toString());
    }
}
