package bg.mck.orderqueryservice.events;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;

public class MetalEvent {

    private Long id;
    private String description;
    private String specificationFileUrl;
    private String totalWeight;
    private String adminNote;
    private MaterialStatus materialStatus;
    private String kind;

    public MetalEvent() {
    }

    public MetalEvent(Long id, String kind ,String description, String specificationFileUrl, String totalWeight, String adminNote, MaterialStatus materialStatus) {
        this.id = id;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.totalWeight = totalWeight;
        this.adminNote = adminNote;
        this.materialStatus = materialStatus;
        this.kind = kind;
    }

    public Long getId() {
        return id;
    }

    public MetalEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetalEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public MetalEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public MetalEvent setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public MetalEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public MetalEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public MetalEvent setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
