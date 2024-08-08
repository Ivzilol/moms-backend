package bg.mck.orderqueryservice.events;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;

public class UnspecifiedEvent {

    private Long id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String adminNote;
    private MaterialStatus materialStatus;

    public UnspecifiedEvent() {
    }

    public UnspecifiedEvent(Long id, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public Long getId() {
        return id;
    }

    public UnspecifiedEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public UnspecifiedEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public UnspecifiedEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
