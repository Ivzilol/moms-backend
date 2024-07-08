package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "galvanised_sheets")
public class GalvanisedSheetEntity {

    private String id;

    private String name;

    private String type;

    private Double maxLength;

    private Double area;

    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(String id, String name, String type, Double maxLength, Double area) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maxLength = maxLength;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
