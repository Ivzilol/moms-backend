package bg.mck.orderqueryservice.dto;

public class BaseDTO {

    private String id;

    private String description;

    private String specificationFileUrl;

    private String adminNote;
    private String materialStatus;

    public BaseDTO() {
    }

    public BaseDTO(String id, String description, String specificationFileUrl, String adminNote, String materialStatus) {
        this.id = id;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.adminNote = adminNote;
        this.materialStatus = materialStatus;
    }

    public String getId() {
        return id;
    }

    public BaseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public BaseDTO setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public BaseDTO setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public BaseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public BaseDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
