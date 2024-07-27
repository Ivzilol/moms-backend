package bg.mck.dto;

import jakarta.validation.constraints.DecimalMin;

public class GalvanizedSheetUpdateDTO {
    private String name;
    private String type;
    @DecimalMin(value = "0.0", inclusive = false, message = "maxLength must be greater than 0")
    private Double maxLength;
    @DecimalMin(value = "0.0", inclusive = false, message = "area must be greater than 0")
    private Double area;
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;
    private String description;
    private String specificationFileUrl;

    public GalvanizedSheetUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public GalvanizedSheetUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public GalvanizedSheetUpdateDTO setType(String type) {
        this.type = type;
        return this;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public GalvanizedSheetUpdateDTO setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public GalvanizedSheetUpdateDTO setArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public GalvanizedSheetUpdateDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GalvanizedSheetUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public GalvanizedSheetUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
