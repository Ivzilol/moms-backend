package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.OrderStatus;

public class OrderConfirmationDTO {
    private final OrderStatus orderStatus;
    private final Long orderId;
    private final Integer orderNumber;
    private final String constructionSiteNumber;
    private final String constructionSiteName;

    private OrderConfirmationDTO(Builder builder) {
        this.orderStatus = builder.orderStatus;
        this.orderId = builder.orderId;
        this.orderNumber = builder.orderNumber;
        this.constructionSiteNumber = builder.constructionSiteNumber;
        this.constructionSiteName = builder.constructionSiteName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public String getConstructionSiteNumber() {
        return constructionSiteNumber;
    }

    public String getConstructionSiteName() {
        return constructionSiteName;
    }

    public static class Builder {
        private OrderStatus orderStatus;
        private Long orderId;
        private Integer orderNumber;
        private String constructionSiteNumber;
        private String constructionSiteName;

        public Builder() {
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderNumber(Integer orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public Builder constructionSiteNumber(String constructionSiteNumber) {
            this.constructionSiteNumber = constructionSiteNumber;
            return this;
        }

        public Builder constructionSiteName(String constructionSiteName) {
            this.constructionSiteName = constructionSiteName;
            return this;
        }

        public OrderConfirmationDTO build() {
            return new OrderConfirmationDTO(this);
        }
    }

    @Override
    public String toString() {
        return "CreateOrderDTO{" +
                "orderStatus=" + orderStatus +
                "orderId=" + orderId +
                ", orderNumber=" + orderNumber +
                ", constructionSiteNumber='" + constructionSiteNumber + '\'' +
                ", constructionSiteName='" + constructionSiteName + '\'' +
                '}';
    }
}
