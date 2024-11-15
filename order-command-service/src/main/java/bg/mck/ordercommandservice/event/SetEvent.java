package bg.mck.ordercommandservice.event;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;

public class SetEvent {

    private Long id;
    private String quantity;
    private String description;
    private String specificationFileUrl;
    private String color; // TODO remove this field
    private String maxLength;
    private String adminNote;
    private MaterialStatus materialStatus;

    public SetEvent() {
    }

    public SetEvent(Long id, String quantity, String description, String specificationFileUrl, String color, String maxLength, String adminNote, MaterialStatus materialStatus) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.color = color;
        this.maxLength = maxLength;
        this.adminNote = adminNote;
        this.materialStatus = materialStatus;
    }

    public Long getId() {
        return id;
    }

    public SetEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public SetEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public SetEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public SetEvent setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SetEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }


    public String getColor() {
        return color;
    }

    public SetEvent setColor(String color) {
        this.color = color;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public SetEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }
}
