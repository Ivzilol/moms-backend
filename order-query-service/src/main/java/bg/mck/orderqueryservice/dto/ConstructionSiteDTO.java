package bg.mck.orderqueryservice.dto;

public class ConstructionSiteDTO {

    private String name;

    private String constructionNumber;

    public ConstructionSiteDTO() {
    }

    public ConstructionSiteDTO(String name, String constructionNumber) {
        this.name = name;
        this.constructionNumber = constructionNumber;
    }

    public String getName() {
        return name;
    }

    public ConstructionSiteDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getConstructionNumber() {
        return constructionNumber;
    }

    public ConstructionSiteDTO setConstructionNumber(String constructionNumber) {
        this.constructionNumber = constructionNumber;
        return this;
    }
}
