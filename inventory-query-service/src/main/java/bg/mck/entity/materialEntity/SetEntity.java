package bg.mck.entity.materialEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "sets")
public class SetEntity {

    @Id
    private String id;
    private String description;
    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public SetEntity() {
    }

    public SetEntity(String id, String description, Double quantity, String note, String specificationFileUrl) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getId() {
        return id;
    }

    public SetEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SetEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public SetEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public SetEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetEntity setEntity = (SetEntity) o;
        return Objects.equals(id, setEntity.id) && Objects.equals(description, setEntity.description) && Objects.equals(quantity, setEntity.quantity) && Objects.equals(note, setEntity.note) && Objects.equals(specificationFileUrl, setEntity.specificationFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, quantity, note, specificationFileUrl);
    }

    @Override
    public String toString() {
        return "SetEntity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                '}';
    }
}
