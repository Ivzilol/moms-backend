package bg.mck.ordercommandservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "construction_sites")
public class ConstructionSiteEntity extends BaseEntity {

    private String constructionNumber;
    private String name;

    public ConstructionSiteEntity() {
    }

    public ConstructionSiteEntity(String constructionNumber, String name) {
        this.constructionNumber = constructionNumber;
        this.name = name;
    }

    public String getConstructionNumber() {
        return constructionNumber;
    }

    public ConstructionSiteEntity setConstructionNumber(String constructionNumber) {
        this.constructionNumber = constructionNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConstructionSiteEntity setName(String name) {
        this.name = name;
        return this;
    }
}
