package bg.mck.ordercommandservice.dto;

public class CreateOrderDTO {
    private String constructionSiteNumber;
    private String constructionSiteName;
    private Integer OrderNumber;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(String constructionSiteNumber, String constructionSiteName, Integer orderNumber) {
        this.constructionSiteNumber = constructionSiteNumber;
        this.constructionSiteName = constructionSiteName;
        OrderNumber = orderNumber;
    }

    public String getConstructionSiteNumber() {
        return constructionSiteNumber;
    }

    public CreateOrderDTO setConstructionSiteNumber(String constructionSiteNumber) {
        this.constructionSiteNumber = constructionSiteNumber;
        return this;
    }

    public String getConstructionSiteName() {
        return constructionSiteName;
    }

    public CreateOrderDTO setConstructionSiteName(String constructionSiteName) {
        this.constructionSiteName = constructionSiteName;
        return this;
    }

    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public CreateOrderDTO setOrderNumber(Integer orderNumber) {
        OrderNumber = orderNumber;
        return this;
    }
}
