package bg.mck.entity.constructions;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "construction_sites")
public class ConstructionSiteEntity {

    private Long id;

    private String constructionNumber;

    private String name;

    public ConstructionSiteEntity() {
    }

    public ConstructionSiteEntity(Long id, String constructionNumber, String name) {
        this.id = id;
        this.constructionNumber = constructionNumber;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConstructionNumber() {
        return constructionNumber;
    }

    public void setConstructionNumber(String constructionNumber) {
        this.constructionNumber = constructionNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
