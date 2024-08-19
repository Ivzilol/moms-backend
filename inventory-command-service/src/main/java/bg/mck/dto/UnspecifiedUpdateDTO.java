package bg.mck.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;

public class UnspecifiedUpdateDTO {

    private String name;

    private String description;

    private String specificationFileUrl;

    public UnspecifiedUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public UnspecifiedUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public UnspecifiedUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public UnspecifiedUpdateDTO setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }
}
