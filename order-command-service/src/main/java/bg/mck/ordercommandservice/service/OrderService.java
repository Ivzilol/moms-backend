package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.*;
import bg.mck.ordercommandservice.entity.*;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.*;
import bg.mck.ordercommandservice.exception.FileMatcherNotFoundException;
import bg.mck.ordercommandservice.mapper.*;
import bg.mck.ordercommandservice.repository.OrderRepository;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
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
    private final InventoryService inventoryService;

    private RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, ConstructionSiteService constructionSiteService, OrderMapper orderMapper, OrderQueryServiceClient orderQueryServiceClient, FastenerMapper fastenerMapper, GalvanisedSheetMapper galvanisedSheetMapper, InsulationMapper insulationMapper, MetalMapper metalMapper, PanelMapper panelMapper, RebarMapper rebarMapper, ServiceMapper serviceMapper, SetMapper setMapper, TransportMapper transportMapper, UnspecifiedMapper unspecifiedMapper, MaterialService materialService, InventoryService inventoryService, RestTemplate restTemplate) {
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
        this.inventoryService = inventoryService;
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

        sendMaterialsToInventory(order);

        return createOrderEvent(orderEntity);
    }

    private void sendMaterialsToInventory(OrderDTO orderDTO) {
        Map<MaterialType, List<? extends BaseDTO>> materials = new HashMap<>();
        switch (orderDTO.getMaterialType()) {
            case FASTENERS -> materials.put(MaterialType.FASTENERS, orderDTO.getFasteners());
            case GALVANIZED_SHEET -> materials.put(MaterialType.GALVANIZED_SHEET, orderDTO.getGalvanisedSheets());
            case INSULATION -> materials.put(MaterialType.INSULATION, orderDTO.getInsulation());
            case METAL -> materials.put(MaterialType.METAL, orderDTO.getMetals());
            case PANELS -> materials.put(MaterialType.PANELS, orderDTO.getPanels());
            case REBAR -> materials.put(MaterialType.REBAR, orderDTO.getRebars());
            case SERVICE -> materials.put(MaterialType.SERVICE, orderDTO.getServices());
            case SET -> materials.put(MaterialType.SET, orderDTO.getSets());
            case TRANSPORT -> materials.put(MaterialType.TRANSPORT, orderDTO.getTransports());
            case UNSPECIFIED -> materials.put(MaterialType.UNSPECIFIED, orderDTO.getUnspecified());
        }

        inventoryService.addMaterialsToInventory(materials);
    }

    @Transactional
    public OrderConfirmationDTO updateOrder(OrderDTO order, String email, List<MultipartFile> files) {

        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        ConstructionSiteEntity constructionSiteByName =
                constructionSiteService.getConstructionSiteByName(order.getConstructionSite().getName());

        orderEntity
                .setEmail(email)
                .setConstructionSite(constructionSiteByName);

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

    public OrderConfirmationDTO updateOrderStatus(OrderDTO order, String fullName) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + order.getId() + " not found"));
        orderEntity.setOrderStatus(order.getOrderStatus());
        updateMaterialStatus(orderEntity, order, fullName);
        orderRepository.save(orderEntity);
        return createOrderEvent(orderEntity);
    }

    public Object addAnswerToAdminNote(OrderDTO order, String fullName) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + order.getId() + " not found"));

        addAnswerPerMaterial(orderEntity, order, fullName);
        orderRepository.save(orderEntity);

        return createOrderEvent(orderEntity);
    }

    private void addAnswerPerMaterial(OrderEntity orderEntity, OrderDTO order, String fullName) {
        switch (order.getMaterialType()) {
            case FASTENERS -> addAnswer(orderEntity.getFasteners(), order.getFasteners(), fullName);
            case GALVANIZED_SHEET ->
                    addAnswer(orderEntity.getGalvanisedSheets(), order.getGalvanisedSheets(), fullName);
            case INSULATION -> addAnswer(orderEntity.getInsulation(), order.getInsulation(), fullName);
            case METAL -> addAnswer(orderEntity.getMetals(), order.getMetals(), fullName);
            case PANELS -> addAnswer(orderEntity.getPanels(), order.getPanels(), fullName);
            case REBAR -> addAnswer(orderEntity.getRebars(), order.getRebars(), fullName);
            case SERVICE -> addAnswer(orderEntity.getServices(), order.getServices(), fullName);
            case SET -> addAnswer(orderEntity.getSets(), order.getSets(), fullName);
            case TRANSPORT -> addAnswer(orderEntity.getTransports(), order.getTransports(), fullName);
            case UNSPECIFIED -> addAnswer(orderEntity.getUnspecified(), order.getUnspecified(), fullName);
        }
    }

    private void addAnswer(Set<? extends BaseMaterialEntity> materials, List<? extends BaseDTO> materialsDTO, String fullName) {
        materials.forEach(material -> {
            materialsDTO.forEach(materialDTO -> {
                if (material.getId().equals(materialDTO.getId()) && materialDTO.getAdminNote() != null) {
                    if (!materialDTO.getAdminNote().contains("##")){
                        return;
                    }
                    String noteUntilNow = materialDTO.getAdminNote().split("##")[0];
                    String newAnswer = materialDTO.getAdminNote().split("##")[1];
                    LocalDateTime timeOfAnswer = LocalDateTime.now();
                    StringBuilder sb = new StringBuilder(noteUntilNow)
                            .append("\n")
                            .append(timeOfAnswer)
                            .append(" ")
                            .append(fullName)
                            .append(": ")
                            .append(newAnswer);
                    material.setAdminNote(sb.toString());
                }
            });
        });
    }

    private void updateMaterialStatus(OrderEntity orderEntity, OrderDTO order, String fullName) {
        switch (order.getMaterialType()) {
            case FASTENERS -> updateMaterialStatus(orderEntity.getFasteners(), order.getFasteners(), fullName);
            case GALVANIZED_SHEET ->
                    updateMaterialStatus(orderEntity.getGalvanisedSheets(), order.getGalvanisedSheets(), fullName);
            case INSULATION -> updateMaterialStatus(orderEntity.getInsulation(), order.getInsulation(), fullName);
            case METAL -> updateMaterialStatus(orderEntity.getMetals(), order.getMetals(), fullName);
            case PANELS -> updateMaterialStatus(orderEntity.getPanels(), order.getPanels(), fullName);
            case REBAR -> updateMaterialStatus(orderEntity.getRebars(), order.getRebars(), fullName);
            case SERVICE -> updateMaterialStatus(orderEntity.getServices(), order.getServices(), fullName);
            case SET -> updateMaterialStatus(orderEntity.getSets(), order.getSets(), fullName);
            case TRANSPORT -> updateMaterialStatus(orderEntity.getTransports(), order.getTransports(), fullName);
            case UNSPECIFIED -> updateMaterialStatus(orderEntity.getUnspecified(), order.getUnspecified(), fullName);
        }
    }

    private void updateMaterialStatus(Set<? extends BaseMaterialEntity> materials, List<? extends BaseDTO> materialsDTO, String fullName) {
        materials.forEach(material -> {
            materialsDTO.forEach(materialDTO -> {
                if (material.getId().equals(materialDTO.getId())) {

                    if (materialDTO.getAdminNote() == null){
                        return;
                    }

                    if (!materialDTO.getAdminNote().contains("##")){
                        return;
                    }

                    if (material.getAdminNote() != null) {
                        String noteUntilNow = materialDTO.getAdminNote().split("##")[0];
                        String newAnswer = materialDTO.getAdminNote().split("##")[1];
                        LocalDateTime timeOfAnswer = LocalDateTime.now();
                        StringBuilder sb = new StringBuilder(noteUntilNow)
                                .append("\n")
                                .append(timeOfAnswer)
                                .append(" ")
                                .append(fullName)
                                .append(": ")
                                .append(newAnswer);
                        material.setAdminNote(sb.toString());
                    } else {
                        String noteUntilNow = materialDTO.getAdminNote().replace("##", "");
                        LocalDateTime timeOfAnswer = LocalDateTime.now();
                        StringBuilder sb = new StringBuilder()
                                .append(timeOfAnswer)
                                .append(" ")
                                .append(fullName)
                                .append(": ")
                                .append(noteUntilNow);
                        material.setAdminNote(sb.toString());
                    }
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

            if (fileDTO == null) {
                throw new FileMatcherNotFoundException("The file has no matching pattern");
            }

            if (fileDTO.getFileMatcher().equals("000")) {
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

        BaseDTO baseDTO = (BaseDTO) currentMaterials.get(Integer.parseInt(fileDTO.getFileMatcher()) - 1);
        baseDTO.setSpecificationFileUrl(fileDTO.getFileUrl());
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
