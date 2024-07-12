package bg.mck.entity.materialEntity;

import bg.mck.entity.categoryEntity.CategoryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

@Entity
@Table(name = "galvanised_sheets")
public class GalvanisedSheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // type
    private String name;

    private String type;
    @DecimalMin(value = "0.0", inclusive = false, message = "maxLength must be greater than 0")
    @Column(name = "max_length_in_centimeters")
    private Double maxLength;
    @DecimalMin(value = "0.0", inclusive = false, message = "area must be greater than 0")
    @Column(name = "area_in_square_meters")
    private Double area;

    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Double quantity;

    @Column(columnDefinition = "TEXT")
    private String description;


    private String specificationFileUrl;

    @ManyToOne
    private CategoryEntity category;

    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Long id, String name, String type, Double maxLength, Double area, Double quantity, String description, String specificationFileUrl, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maxLength = maxLength;
        this.area = area;
        this.quantity = quantity;
        this.description = description;
        this.specificationFileUrl = specificationFileUrl;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
