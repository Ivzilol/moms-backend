package bg.mck.ordercommandservice.event;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;

public class InsulationEvent {

    private Long id;
    private String quantity;
    private String description;
    private String specificationFileUrl;
    private String type;
    private String thickness;
    private String adminNote;
    private MaterialStatus materialStatus;

    public InsulationEvent() {
    }

    public InsulationEvent(Long id, String quantity, String description, String specificationFileUrl, String type, String thickness, String adminNote, MaterialStatus materialStatus) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.thickness = thickness;
        this.adminNote = adminNote;
        this.materialStatus = materialStatus;
    }

    public Long getId() {
        return id;
    }

    public InsulationEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public InsulationEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public InsulationEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public InsulationEvent setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InsulationEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public InsulationEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public InsulationEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationEvent setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }
}
