package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
