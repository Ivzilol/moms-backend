package bg.mck.orderqueryservice.events;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;

public class FasterEvent {

    private Long id;
    private String quantity;
    private String description;
    private String specificationFileUrl;
    private String type;
    private String diameter;
    private String length;
    private String standard;
    private String clazz;
    private String adminNote;
    private MaterialStatus materialStatus;

    public FasterEvent() {
    }

    public FasterEvent(Long id, String quantity, String description, String specificationFileUrl, String type, String diameter, String length, String standard, String clazz, String adminNote, MaterialStatus materialStatus) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.diameter = diameter;
        this.length = length;
        this.standard = standard;
        this.clazz = clazz;
        this.adminNote = adminNote;
        this.materialStatus = materialStatus;
    }

    public Long getId() {
        return id;
    }

    public FasterEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public FasterEvent setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FasterEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public FasterEvent setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public FasterEvent setType(String type) {
        this.type = type;
        return this;
    }

    public String getDiameter() {
        return diameter;
    }

    public FasterEvent setDiameter(String diameter) {
        this.diameter = diameter;
        return this;
    }

    public String getLength() {
        return length;
    }

    public FasterEvent setLength(String length) {
        this.length = length;
        return this;
    }

    public String getStandard() {
        return standard;
    }

    public FasterEvent setStandard(String standard) {
        this.standard = standard;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public FasterEvent setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public FasterEvent setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public FasterEvent setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }
}
