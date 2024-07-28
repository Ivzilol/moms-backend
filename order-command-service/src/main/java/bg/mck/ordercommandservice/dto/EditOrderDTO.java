package bg.mck.ordercommandservice.dto;

import bg.mck.ordercommandservice.entity.enums.OrderStatus;

public class EditOrderDTO {
    private final OrderStatus orderStatus;
    private final Long orderId;

    private EditOrderDTO(Builder builder) {
        this.orderStatus = builder.orderStatus;
        this.orderId = builder.orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }


    public static class Builder {
        private OrderStatus orderStatus;
        private Long orderId;

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

        public EditOrderDTO build() {
            return new EditOrderDTO(this);
        }
    }

    @Override
    public String toString() {
        return "EditOrderDTO{" +
                "orderStatus=" + orderStatus +
                ", orderId=" + orderId +
                '}';
    }
}
