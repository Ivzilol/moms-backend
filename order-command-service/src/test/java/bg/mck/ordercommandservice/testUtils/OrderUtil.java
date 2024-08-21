package bg.mck.ordercommandservice.testUtils;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.dto.FileDTO;
import bg.mck.ordercommandservice.dto.OrderConfirmationDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;
import bg.mck.ordercommandservice.event.CreateOrderEvent;
import bg.mck.ordercommandservice.event.FasterEvent;
import bg.mck.ordercommandservice.event.EventType;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderUtil {
    private ConstructionSiteDTO constructionSiteDTO;
    private ConstructionSiteEntity constructionSiteEntity;


    public static OrderDTO createOrderDTO() {
        return new OrderDTO().setOrderDescription("Order Description")
                .setConstructionSite(ConstructionSiteUtil.createConstructionSiteDTO())
                .setOrderStatus(OrderStatus.CREATED)
                .setOrderDate(ZonedDateTime.parse("2024-07-05T14:58:04Z"))
                .setDeliveryDate(ZonedDateTime.parse("2025-07-05T14:58:04Z"))
                .setMaterialType(MaterialType.FASTENERS)
                .setSpecificationFileUrl("https://www.example.com");

    }

    public static OrderEntity createOrderEntity() {

        OrderEntity orderEntity = new OrderEntity()
                .setOrderDescription("Order Description")
                .setOrderNumber(50)
                .setConstructionSite(ConstructionSiteUtil.createConstructionSiteEntity())
                .setOrderStatus(OrderStatus.CREATED)
                .setOrderDate(ZonedDateTime.parse("2024-07-05T14:58:04Z"))
                .setDeliveryDate(ZonedDateTime.parse("2025-07-05T14:58:04Z"))
                .setSpecificationFileUrl("https://www.example.com")
                .setMaterialType(MaterialType.FASTENERS)
                .setFasteners(Set.of(MaterialUtil.createFastenerEntity(),
                        MaterialUtil.createFastenerEntity()));
        orderEntity.setId(1L);
        return orderEntity;
    }

    public static CreateOrderEvent<FasterEvent> createCreateOrderEvent() {
        CreateOrderEvent<FasterEvent> createOrderEvent = new CreateOrderEvent<>();
        OrderEntity orderEntity = createOrderEntity();
        Set<FasterEvent> fasterEventSet = orderEntity.getFasteners().stream()
                .map(fastenerEntity -> new FasterEvent()
                        .setDiameter(fastenerEntity.getDiameter())
                        .setDescription(fastenerEntity.getDescription())
                        .setQuantity(fastenerEntity.getQuantity())
                        .setSpecificationFileUrl(fastenerEntity.getSpecificationFileUrl())
                        .setClazz(fastenerEntity.getClazz())
                        .setLength(fastenerEntity.getLength())
                        .setStandard(fastenerEntity.getStandard())
                        .setType(fastenerEntity.getType()))
                .collect(Collectors.toSet());
        createOrderEvent.setOrderId(1L)
                .setEventType(EventType.ORDER_CREATED)
                .setEventTime(LocalDateTime.now());
        createOrderEvent.setEmail(orderEntity.getEmail())
                .setOrderNumber(orderEntity.getOrderNumber())
                .setOrderDescription(orderEntity.getOrderDescription())
                .setOrderDate(orderEntity.getOrderDate())
                .setDeliveryDate(orderEntity.getDeliveryDate())
                .setMaterialType(orderEntity.getMaterialType())
                .setOrderStatus(orderEntity.getOrderStatus())
                .setConstructionSite(orderEntity.getConstructionSite())
                .setMaterials(fasterEventSet);

        return createOrderEvent;
    }

    public static OrderConfirmationDTO createOrderConfirmationDTO() {
        return new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.CREATED)
                .orderId(1L)
                .orderNumber(50)
                .constructionSiteNumber("1234")
                .constructionSiteName("Site Name")
                .build();

    }

    public static OrderConfirmationDTO updateOrderConfirmationDTO() {
        return new OrderConfirmationDTO.Builder()
                .orderStatus(OrderStatus.UPDATED)
                .orderId(1L)
                .orderNumber(50)
                .constructionSiteNumber("1234")
                .constructionSiteName("Site Name")
                .build();

    }

    public static FileDTO createFileDTO() {
        return new FileDTO().setFileUrl("https://www.example.com")
                .setFileName("file.txt")
                .setFileMatcher("001")
                .setUploaderEmail("test@abv.bg")
                .setUploadTime(LocalDateTime.now())
                .setId("fileID");

    }
}
