package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class BaseMaterialEntity extends BaseEntity {


    private String description;

    private String specificationFileUrl;

    private String adminNote;

    private MaterialStatus materialStatus;

    public BaseMaterialEntity() {
    }

    public BaseMaterialEntity(String id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus) {
        super(id);
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.adminNote = adminNote;
        this.materialStatus = materialStatus;
    }

    public BaseMaterialEntity(String note, String specificationFileUrl) {
        this.description = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getDescription() {
        return description;
    }

    public BaseMaterialEntity setDescription(String note) {
        this.description = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public BaseMaterialEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public BaseMaterialEntity setAdminNote(String adminNote) {
        this.adminNote = adminNote;
        return this;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public BaseMaterialEntity setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseMaterialEntity that = (BaseMaterialEntity) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(specificationFileUrl, that.specificationFileUrl) &&
                Objects.equals(adminNote, that.adminNote) &&
                materialStatus == that.materialStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, specificationFileUrl, adminNote, materialStatus);
    }

    @Override
    public String toString() {
        return "BaseMaterialEntity{" +
                "id='" + getId() + '\'' +
                ", description='" + description + '\'' +
                ", specificationFileUrl='" + specificationFileUrl + '\'' +
                ", adminNote='" + adminNote + '\'' +
                ", materialStatus=" + materialStatus +
                '}';
    }
}
