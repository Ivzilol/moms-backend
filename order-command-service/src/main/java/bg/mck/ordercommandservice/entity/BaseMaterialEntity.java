package bg.mck.ordercommandservice.entity;

import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMin;

@MappedSuperclass
public class BaseMaterialEntity extends BaseEntity {



    @Column(columnDefinition = "TEXT")
    private String description;

    private String specificationFileUrl;

    @Column(columnDefinition = "TEXT")
    private String adminNote;

    @Enumerated(EnumType.STRING)
    private MaterialStatus materialStatus;

    public BaseMaterialEntity() {
    }

    public BaseMaterialEntity(Long id, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus) {
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
}
