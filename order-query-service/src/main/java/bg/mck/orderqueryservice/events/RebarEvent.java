package bg.mck.orderqueryservice.events;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;

public class RebarEvent {

    private Long id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String maxLength;
    private String weight;
    private String adminNote;
    private MaterialStatus materialStatus;

    public RebarEvent() {
    }

    public RebarEvent(Long id, Double quantity, String description, String specificationFileUrl, String maxLength, String weight) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.maxLength = maxLength;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public RebarEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public RebarEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public RebarEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public RebarEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RebarEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public RebarEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public RebarEvent setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}
