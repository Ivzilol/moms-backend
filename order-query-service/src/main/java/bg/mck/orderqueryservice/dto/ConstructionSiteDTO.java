package bg.mck.orderqueryservice.dto;

public class ConstructionSiteDTO {

    private Long id;

    private String name;

    private String constructionNumber;

    public ConstructionSiteDTO() {
    }

    public ConstructionSiteDTO(Long id, String name, String constructionNumber) {
        this.id = id;
        this.name = name;
        this.constructionNumber = constructionNumber;
    }

    public Long getId() {
        return id;
    }

    public ConstructionSiteDTO setId(Long id) {
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