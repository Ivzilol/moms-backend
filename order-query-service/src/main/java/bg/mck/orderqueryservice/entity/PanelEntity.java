package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("panels")
public class PanelEntity {

    private String id;
    private Double quantity;
    private String description;
    private String specificationFileUrl;
    private String type;
    private String color;
    @Field("length_in_centimeters")
    private Double length;
    @Field("width_in_centimeters")
    private Double width;
    @Field("total_thickness_in_mm")
    private Double totalThickness;
    @Field("front_sheet_thickness_in_mm")
    private Double frontSheetThickness;
    @Field("back_sheet_thickness_in_mm")
    private Double backSheetThickness;


    public PanelEntity() {

    }


    public PanelEntity(String id, Double quantity, String description, String specificationFileUrl, String type, String color, Double length, Double width, Double totalThickness, Double frontSheetThickness, Double backSheetThickness) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
        this.type = type;
        this.color = color;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        this.frontSheetThickness = frontSheetThickness;
        this.backSheetThickness = backSheetThickness;
    }

    public String getId() {
        return id;
    }

    public PanelEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PanelEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PanelEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public PanelEntity setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public PanelEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PanelEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public PanelEntity setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public PanelEntity setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getTotalThickness() {
        return totalThickness;
    }

    public PanelEntity setTotalThickness(Double totalThickness) {
        this.totalThickness = totalThickness;
        return this;
    }

    public Double getFrontSheetThickness() {
        return frontSheetThickness;
    }

    public PanelEntity setFrontSheetThickness(Double frontSheetThickness) {
        this.frontSheetThickness = frontSheetThickness;
        return this;
    }

    public Double getBackSheetThickness() {
        return backSheetThickness;
    }

    public PanelEntity setBackSheetThickness(Double backSheetThickness) {
        this.backSheetThickness = backSheetThickness;
        return this;
    }
}
