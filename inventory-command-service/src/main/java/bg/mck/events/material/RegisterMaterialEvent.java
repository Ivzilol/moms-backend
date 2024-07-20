package bg.mck.events.material;

import bg.mck.entity.categoryEntity.CategoryEntity;
import bg.mck.enums.EventType;
import bg.mck.enums.MaterialType;

public class RegisterMaterialEvent extends BaseMaterialEvent {

    private String category;
    private String name;
    private String description;
    private String diameter;
    private Double length;
    private String model;
    private String clazz;
    private Double quantity;
    private String note;
    private String specificationFileUrl;


    public RegisterMaterialEvent() {

    }


    public RegisterMaterialEvent(Long materialId, EventType eventType, String category, String name, String description, String diameter, Double length, String model, String clazz, Double quantity, String note, String specificationFileUrl) {
        super(materialId, eventType);
        this.category = category;
        this.name = name;
        this.description = description;
        this.diameter = diameter;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
        this.quantity = quantity;
        this.note = note;
        this.specificationFileUrl = specificationFileUrl;
    }

    public RegisterMaterialEvent(MaterialType materialType, Long id, CategoryEntity category, String name, String description, String diameter, Double length, String model, String clazz, Double quantity, String note, String specificationFileUrl) {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSpecificationFileUrl() {
        return specificationFileUrl;
    }

    public void setSpecificationFileUrl(String specificationFileUrl) {
        this.specificationFileUrl = specificationFileUrl;
    }
}
