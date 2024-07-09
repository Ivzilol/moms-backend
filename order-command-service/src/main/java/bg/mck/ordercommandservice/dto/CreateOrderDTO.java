package bg.mck.ordercommandservice.dto;

public class CreateOrderDTO {
    private final Long orderId;
    private final Integer orderNumber;
    private final String constructionSiteNumber;
    private final String constructionSiteName;

    private CreateOrderDTO(Builder builder) {
        this.orderId = builder.orderId;
        this.orderNumber = builder.orderNumber;
        this.constructionSiteNumber = builder.constructionSiteNumber;
        this.constructionSiteName = builder.constructionSiteName;
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
        private Long orderId;
        private Integer orderNumber;
        private String constructionSiteNumber;
        private String constructionSiteName;

        public Builder() {
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

        public CreateOrderDTO build() {
            return new CreateOrderDTO(this);
        }
    }

    @Override
    public String toString() {
        return "CreateOrderDTO{" +
                "orderId=" + orderId +
                ", orderNumber=" + orderNumber +
                ", constructionSiteNumber='" + constructionSiteNumber + '\'' +
                ", constructionSiteName='" + constructionSiteName + '\'' +
                '}';
    }
}
