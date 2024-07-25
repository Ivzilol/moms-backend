package bg.mck.orderqueryservice.dto;

import jakarta.validation.constraints.NotNull;

public class ConstructionSiteDTO {

    private String id;

    private String name;

    private String constructionNumber;

    public ConstructionSiteDTO() {
    }

    public ConstructionSiteDTO(String id, String name, String constructionNumber) {
        this.id = id;
        this.name = name;
        this.constructionNumber = constructionNumber;
    }

    public String getId() {
        return id;
    }

    public ConstructionSiteDTO setId(String id) {
        this.id = id;
        return this;
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
