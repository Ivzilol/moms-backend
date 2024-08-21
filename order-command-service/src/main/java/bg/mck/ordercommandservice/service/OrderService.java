package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.BaseDTO;
import bg.mck.ordercommandservice.dto.FileDTO;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.BaseMaterialEntity;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.exception.FileMatcherNotFoundException;
import bg.mck.ordercommandservice.exception.OrderNotFoundException;
import bg.mck.ordercommandservice.mapper.OrderMapper;
import bg.mck.ordercommandservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class OrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ConstructionSiteService constructionSiteService;
    private final OrderMapper orderMapper;
    private final MaterialService materialService;
    private final InventoryService inventoryService;
    private final OrderEventService orderEventService;


    public OrderService(OrderRepository orderRepository, ConstructionSiteService constructionSiteService, OrderMapper orderMapper, MaterialService materialService, InventoryService inventoryService, OrderEventService orderEventService) {
        this.orderRepository = orderRepository;
        this.constructionSiteService = constructionSiteService;
        this.orderMapper = orderMapper;
        this.materialService = materialService;
        this.inventoryService = inventoryService;
        this.orderEventService = orderEventService;
    }

    @Transactional
    public OrderConfirmationDTO createOrder(OrderDTO order, String email, List<FileDTO> fileUrls) {

        matchFilesToMaterials(order, fileUrls);

        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        ConstructionSiteEntity constructionSiteByNumberAndName = constructionSiteService.getConstructionSiteByNumberAndName(order.getConstructionSite());
        Optional<Integer> lastOrderNumber = orderRepository.findLastOrderNumber();

        orderEntity.setEmail(email).setOrderNumber(lastOrderNumber.orElse(0) + 1).setOrderStatus(OrderStatus.CREATED).setOrderDate(ZonedDateTime.now(ZoneId.of("Europe/Sofia")).plusHours(3)) //FIXME: find a better way to set the time and timezone
                .setConstructionSite(constructionSiteByNumberAndName);

        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} created successfully", orderEntity.getId());

        inventoryService.sendMaterialsToInventory(order);

        return orderEventService.createOrderEvent(orderEntity);
    }


    @Transactional
    public OrderConfirmationDTO updateOrder(OrderDTO order, String email, List<MultipartFile> files) {

        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        ConstructionSiteEntity constructionSiteByName = constructionSiteService.getConstructionSiteByName(order.getConstructionSite().getName());

        orderEntity.setEmail(email).setConstructionSite(constructionSiteByName);

        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} updated successfully", orderEntity.getId());

        return orderEventService.createOrderEvent(orderEntity);

    }


    public OrderConfirmationDTO deleteOrder(Long order, String email) {
        OrderEntity orderEntity = orderRepository.findById(order)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + order + " not found"));
        orderEntity.setOrderStatus(OrderStatus.CANCELLED).setEmail(email);
        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} cancelled successfully", orderEntity.getId());

        return orderEventService.createOrderEvent(orderEntity);
    }

    public OrderConfirmationDTO restoreOrder(Long orderId, String email) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        orderEntity.setOrderStatus(OrderStatus.PENDING).setEmail(email);
        orderRepository.save(orderEntity);
        LOGGER.info("Order with id {} restored successfully", orderEntity.getId());

        return orderEventService.createOrderEvent(orderEntity);
    }

    public OrderConfirmationDTO deleteMaterial(Long orderId, Long materialId, String email) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));

        materialService.deleteMaterial(materialId, orderEntity.getMaterialType().toString());

        LOGGER.info("Material with id {} deleted from order with id {}", materialId, orderId);

        return orderEventService.createOrderEvent(orderEntity);
    }

    public OrderConfirmationDTO updateOrderStatus(OrderDTO order, String fullName) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + order.getId() + " not found"));
        orderEntity.setOrderStatus(order.getOrderStatus());
        updateMaterialStatus(orderEntity, order, fullName);
        orderRepository.save(orderEntity);

        return orderEventService.createOrderEvent(orderEntity);
    }

    public Object addAnswerToAdminNote(OrderDTO order, String fullName) {
        OrderEntity orderEntity = orderRepository.findByOrderNumber(order.getOrderNumber());

        addAnswerPerMaterial(orderEntity, order, fullName);
        orderRepository.save(orderEntity);

        return orderEventService.createOrderEvent(orderEntity);
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
                    if (!materialDTO.getAdminNote().contains("##")) {
                        return;
                    }
                    if (material.getAdminNote().equals(materialDTO.getAdminNote())) {
                        return;
                    }
                    String noteUntilNow = materialDTO.getAdminNote().split("##")[1];
                    String newAnswer = materialDTO.getAdminNote().split("##")[2];
                    LocalDateTime timeOfAnswer = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String formattedDateTime = timeOfAnswer.format(formatter);
                    StringBuilder sb = new StringBuilder(noteUntilNow)
                            .append("<br>")
                            .append(formattedDateTime)
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

                    if (materialDTO.getAdminNote() == null) {
                        return;
                    }

                    if (!materialDTO.getAdminNote().contains("##")) {
                        return;
                    }
                    if (material.getAdminNote() != null && material.getAdminNote().equals(materialDTO.getAdminNote())) {
                        return;
                    }

                    if (material.getAdminNote() != null) {
                        String noteUntilNow = materialDTO.getAdminNote().split("##")[1];
                        String newAnswer = materialDTO.getAdminNote().split("##")[2];
                        LocalDateTime timeOfAnswer = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String formattedDateTime = timeOfAnswer.format(formatter);
                        StringBuilder sb = new StringBuilder(noteUntilNow)
                                .append("<br>")
                                .append(formattedDateTime)
                                .append(" ")
                                .append(fullName)
                                .append(": ")
                                .append(newAnswer);
                        material.setAdminNote(sb.toString());
                    } else {
                        String noteUntilNow = materialDTO.getAdminNote().replace("##", "");
                        LocalDateTime timeOfAnswer = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String formattedDateTime = timeOfAnswer.format(formatter);
                        StringBuilder sb = new StringBuilder()
                                .append(formattedDateTime)
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
        int matcher = Integer.parseInt(fileDTO.getFileMatcher());
        if (matcher > currentMaterials.size()) {
            throw new FileMatcherNotFoundException(String.format("The file %s has no matching material", fileDTO.getFileName()));
        }
        BaseDTO baseDTO = (BaseDTO) currentMaterials.get(matcher - 1);
        baseDTO.setSpecificationFileUrl(fileDTO.getFileUrl());
    }


}
