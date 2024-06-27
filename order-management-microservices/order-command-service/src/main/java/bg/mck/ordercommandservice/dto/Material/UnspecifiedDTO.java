package bg.mck.ordercommandservice.dto.Material;

public class UnspecifiedDTO {

    private String description;

    private Double quantity;
    private String note;
    private String specificationFileUrl;

    public UnspecifiedDTO() {
    }

    public UnspecifiedDTO(String description, Double quantity, String note, String specificationFileUrl) {
        this.description = description;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public String getDescription() {
        return description;
    }

    public UnspecifiedDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnspecifiedDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNote() {
        return note;
    }

    public UnspecifiedDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
