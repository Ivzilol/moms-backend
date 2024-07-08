package bg.mck.entity.materialEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "panels")
public class PanelEntity {

    private String id;

    private String name;

    private Double length;

    private Double width;

    private Double totalThickness;

    private Double FrontSheetThickness;

    private Double BackSheetThickness;

    private Double quantity;

    private String description;

    private String specificationFileUrl;

    public PanelEntity() {
    }

    public PanelEntity(String id, String name, Double length, Double width, Double totalThickness, Double frontSheetThickness, Double backSheetThickness, Double quantity, String description, String specificationFileUrl) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        FrontSheetThickness = frontSheetThickness;
        BackSheetThickness = backSheetThickness;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public void setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
    }

    public Double getFrontSheetThickness() {
        return FrontSheetThickness;
    }

    public void setFrontSheetThickness(Double frontSheetThickness) {
        FrontSheetThickness = frontSheetThickness;
    }

    public Double getBackSheetThickness() {
        return BackSheetThickness;
    }

    public void setBackSheetThickness(Double backSheetThickness) {
        BackSheetThickness = backSheetThickness;
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