package bg.mck.dto;

public class CategoryDTO {

    private int id;
    private String materialType;


    public CategoryDTO() {
    }

    public CategoryDTO(int id, String materialType) {
        this.id = id;
        this.materialType = materialType;
    }

    public int getId() {
        return id;
    }

    public String getMaterialType() {
        return materialType;
    }
}
