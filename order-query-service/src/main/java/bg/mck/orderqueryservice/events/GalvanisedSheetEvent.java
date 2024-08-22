package bg.mck.orderqueryservice.events;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;

public class GalvanisedSheetEvent extends BaseMaterialEvent {

    private Long id;
    private String quantity;
    private String description;
    private String specificationFileUrl;
    private String type;
    private String maxLength;
    private String numberOfSheets;
    private String adminNote;
    private MaterialStatus materialStatus;

    public GalvanisedSheetEvent() {
    }

    public GalvanisedSheetEvent(Long id, String quantity, String description, String specificationFileUrl, String type, String maxLength, String numberOfSheets, String adminNote, MaterialStatus materialStatus) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.maxLength = maxLength;
        this.numberOfSheets = numberOfSheets;
        this.adminNote = adminNote;
        this.materialStatus = materialStatus;
    }

    public Long getId() {
        return id;
    }

    public GalvanisedSheetEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public GalvanisedSheetEvent setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GalvanisedSheetEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanisedSheetEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEvent setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getNumberOfSheets() {
        return numberOfSheets;
    }

    public GalvanisedSheetEvent setNumberOfSheets(String numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public GalvanisedSheetEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public GalvanisedSheetEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }
}
