package bg.mck.ordercommandservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "construction_sites")
public class ConstructionSiteEntity extends BaseEntity {

    @Column(name = "construction_number", nullable = false, unique = true)
    private String constructionNumber;
    @Column(name = "construction_name", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstructionSiteEntity that = (ConstructionSiteEntity) o;
        return Objects.equals(constructionNumber, that.constructionNumber) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constructionNumber, name);
    }

    @Override
    public String toString() {
        return "ConstructionSiteEntity{" +
                "constructionNumber='" + constructionNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

