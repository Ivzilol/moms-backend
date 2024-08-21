package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("metals")
public class MetalEntity extends BaseMaterialEntity {

    private String kind;
    private String totalWeight;

    public MetalEntity() {
    }

    public MetalEntity(String kind, String totalWeight) {
        this.kind = kind;
        this.totalWeight = totalWeight;
    }

    public MetalEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String kind, String totalWeight) {
        super(id, description, specificationFileUrl, adminNote, materialStatus);
        this.kind = kind;
        this.totalWeight = totalWeight;
    }

    public MetalEntity(String note, String specificationFileUrl, String kind, String totalWeight) {
        super(note, specificationFileUrl);
        this.kind = kind;
        this.totalWeight = totalWeight;
    }

    public String getKind() {
        return kind;
    }

    public MetalEntity setKind(String kind) {
        this.kind = kind;
        return this;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalEntity setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MetalEntity that = (MetalEntity) o;
        return Objects.equals(kind, that.kind) &&
                Objects.equals(totalWeight, that.totalWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), kind, totalWeight);
    }

    @Override
    public String toString() {
        return "MetalEntity{" +
                "kind='" + kind + '\'' +
                ", totalWeight='" + totalWeight + '\'' +
                "} " + super.toString();
    }
}
