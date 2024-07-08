package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "panels")
public class PanelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DecimalMin(value = "0.0", message = "Length must be positive")
    @Column(name = "length_in_centimeters")
    private Double length;

    @DecimalMin(value = "0.0", message = "Width must be positive")
    @Column(name = "width_in_centimeters")
    private Double width;

    @DecimalMin(value = "0.0", message = "Thickness must be positive")
    @Column(name = "total_thickness_in_mm")
    private Double totalThickness;

    @DecimalMin(value = "0.0", message = "FrontSheetThickness must be positive")
    @Column(name = "front_sheet_thickness_in_mm")
    private Double FrontSheetThickness;

    @DecimalMin(value = "0.0", message = "BackSheetThickness must be positive")
    @Column(name = "back_sheet_thickness_in_mm")
    private Double BackSheetThickness;

    @ManyToOne
    private CategoryEntity category;

    public PanelEntity() {
    }

    public PanelEntity(Long id, String name, Double length, Double width, Double totalThickness, Double frontSheetThickness, Double backSheetThickness, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.totalThickness = totalThickness;
        FrontSheetThickness = frontSheetThickness;
        BackSheetThickness = backSheetThickness;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
