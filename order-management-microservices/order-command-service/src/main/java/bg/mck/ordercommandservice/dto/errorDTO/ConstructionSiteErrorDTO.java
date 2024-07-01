package bg.mck.ordercommandservice.dto.errorDTO;

public class ConstructionSiteErrorDTO {

    private String nameError;
    private String constructionNumberError;

    public ConstructionSiteErrorDTO() {
    }

    public ConstructionSiteErrorDTO(String nameError, String constructionNumberError) {
        this.nameError = nameError;
        this.constructionNumberError = constructionNumberError;
    }

    public String getNameError() {
        return nameError;
    }

    public ConstructionSiteErrorDTO setNameError(String nameError) {
        this.nameError = nameError;
        return this;
    }

    public String getConstructionNumberError() {
        return constructionNumberError;
    }

    public ConstructionSiteErrorDTO setConstructionNumberError(String constructionNumberError) {
        this.constructionNumberError = constructionNumberError;
        return this;
    }
}
