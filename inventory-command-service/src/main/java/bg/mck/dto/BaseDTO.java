package bg.mck.dto;

public class BaseDTO {

    private String description;
    private String specificationFileUrl;

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
