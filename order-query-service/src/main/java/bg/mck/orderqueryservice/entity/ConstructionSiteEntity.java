package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("construction_sites")
public class ConstructionSiteEntity extends BaseEntity {

    @Field(name = "construction_number")
    private String constructionNumber;
    @Field(name = "construction_name")
    private String name;

    public ConstructionSiteEntity() {
    }

    private ConstructionSiteEntity(Builder builder) {
        this.constructionNumber = builder.constructionNumber;
        this.name = builder.name;
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

    public static class Builder {
        private String constructionNumber;
        private String name;

        public Builder() {
        }

        public Builder constructionNumber(String constructionNumber) {
            this.constructionNumber = constructionNumber;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public ConstructionSiteEntity build() {
            return new ConstructionSiteEntity(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}

