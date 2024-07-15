package bg.mck.ordercommandservice.testUtils;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.entity.enums.OrderStatus;

import java.time.ZonedDateTime;

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
                .setMaterialType(MaterialType.FASTENERS);
        orderEntity.setId(1L);
        return orderEntity;
    }
}
