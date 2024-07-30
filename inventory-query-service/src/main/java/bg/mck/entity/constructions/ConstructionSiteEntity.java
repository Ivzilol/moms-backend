package bg.mck.entity.constructions;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "construction_sites")
public class ConstructionSiteEntity {

    private String id;

    private String constructionNumber;

    @Indexed
    private String name;

    public ConstructionSiteEntity() {
    }

    public ConstructionSiteEntity(String id, String constructionNumber, String name) {
        this.id = id;
        this.constructionNumber = constructionNumber;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
