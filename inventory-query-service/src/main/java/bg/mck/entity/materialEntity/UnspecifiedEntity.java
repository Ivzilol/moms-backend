package bg.mck.entity.materialEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "unspecified")
public class UnspecifiedEntity {

    @Id
    private String id;
    private String description;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public UnspecifiedEntity() {
    }

    public UnspecifiedEntity(String id, String description, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public UnspecifiedEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UnspecifiedEntity setNote(String note) {
        this.note = note;
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
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(quantity, that.quantity) && Objects.equals(note, that.note) && Objects.equals(specificationFileUrl, that.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "UnspecifiedEntity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
