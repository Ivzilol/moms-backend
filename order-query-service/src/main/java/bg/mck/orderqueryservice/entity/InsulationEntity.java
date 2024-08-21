package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("insulation")
public class InsulationEntity extends BaseMaterialEntity {

    private String type;
    private String thickness;
    private String quantity;

    public InsulationEntity() {
    }

    public InsulationEntity(String type, String thickness, String quantity) {
        this.type = type;
        this.thickness = thickness;
        this.quantity = quantity;
    }

    public InsulationEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String type, String thickness, String quantity) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.type = type;
        this.thickness = thickness;
        this.quantity = quantity;
    }

    public InsulationEntity(String note, String specificationFileUrl, String type, String thickness, String quantity) {
        super(note, specificationFileUrl);
        this.type = type;
        this.thickness = thickness;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public InsulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationEntity setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public InsulationEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InsulationEntity that = (InsulationEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(thickness, that.thickness) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, thickness, quantity);
    }

    @Override
    public String toString() {
        return "InsulationEntity{" +
                "type='" + type + '\'' +
                ", thickness='" + thickness + '\'' +
                ", quantity='" + quantity + '\'' +
                "} " + super.toString();
    }
}
