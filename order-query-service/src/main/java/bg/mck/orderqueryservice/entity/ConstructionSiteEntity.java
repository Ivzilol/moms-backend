package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("construction_sites")
public class ConstructionSiteEntity {

    private String id;

    private String constructionNumber;
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public ConstructionSiteEntity setId(String id) {
        this.id = id;
        return this;
    }

    public ConstructionSiteEntity setConstructionNumber(String constructionNumber) {
        this.constructionNumber = constructionNumber;
        return this;
    }

    public ConstructionSiteEntity setName(String name) {
        this.name = name;
        return this;
    }

    public static class Builder {
        private String id;
        private String constructionNumber;
        private String name;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
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
