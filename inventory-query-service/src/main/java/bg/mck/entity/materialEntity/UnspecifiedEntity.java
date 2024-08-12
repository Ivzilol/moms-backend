package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "unspecified")
public class UnspecifiedEntity extends BaseMaterialEntity {

    @Indexed
    private String name;
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    // Default constructor
    public UnspecifiedEntity() {
    }

    // Constructor accepting Builder
    private UnspecifiedEntity(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.specificationFileUrl = builder.specificationFileUrl;
    }

    // Copy constructor
    public UnspecifiedEntity(UnspecifiedEntity other) {
        super(other.getId());
        this.name = other.name;
        this.quantity = other.quantity;
        this.description = other.description;
        this.specificationFileUrl = other.specificationFileUrl;
    }

    // Getters and setters with method chaining
    public String getName() {
        return name;
    }

    public UnspecifiedEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnspecifiedEntity that = (UnspecifiedEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(description, that.description) &&
                Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, description, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "UnspecifiedEntity{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static class Builder {
        private String id;
        private String name;
        private Double quantity;
        private String description;
        private String specificationFileUrl;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setQuantity(Double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setSpecificationFileUrl(String specificationFileUrl) {
            this.specificationFileUrl = specificationFileUrl;
            return this;
        }

        public UnspecifiedEntity build() {
            return new UnspecifiedEntity(this);
        }
    }
}
