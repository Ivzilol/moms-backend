package bg.mck.orderqueryservice.events;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;

public class ServiceEvent {

    private Long id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String adminNote;
    private MaterialStatus materialStatus;

    public ServiceEvent() {
    }

    public ServiceEvent(Long id, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
    }

    public Long getId() {
        return id;
    }

    public ServiceEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ServiceEvent setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public ServiceEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public ServiceEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public ServiceEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
