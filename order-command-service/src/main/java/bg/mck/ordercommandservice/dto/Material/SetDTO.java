package bg.mck.ordercommandservice.dto.Material;

import jakarta.persistence.Column;

public class SetDTO {
    private String description;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public SetDTO() {
    }

    public SetDTO(String description, Double quantity, String note, String specificationFileUrl) {
        this.description = description;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getDescription() {
        return description;
    }

    public SetDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public SetDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public SetDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public SetDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
