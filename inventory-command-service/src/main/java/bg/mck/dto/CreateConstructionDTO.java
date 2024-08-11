package bg.mck.dto;

public class CreateConstructionDTO {

    private String constructionNumber;
    private String name;

    public String getConstructionNumber() {
        return constructionNumber;
    }

    public CreateConstructionDTO setConstructionNumber(String constructionNumber) {
        this.constructionNumber = constructionNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateConstructionDTO setName(String name) {
        this.name = name;
        return this;
    }
}
