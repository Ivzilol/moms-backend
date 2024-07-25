package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity{

    @Indexed
    private String name;

    private String type;

    private Double maxLength;

    private Double area;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(String id, String name, String type, Double maxLength, Double area, Double quantity, String description, String specificationFileUrl) {
        super(id);
        this.name = name;
        this.type = type;
        this.maxLength = maxLength;
        this.area = area;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }
}
