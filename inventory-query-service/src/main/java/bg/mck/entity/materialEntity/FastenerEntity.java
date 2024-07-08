package bg.mck.entity.materialEntity;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fastener")
public class FastenerEntity {


    private String id;

    private String name;

    private String type;

    private Double length;

    private String model;

    private String clazz;

    public FastenerEntity() {
    }

    public FastenerEntity(String id, String name, String type, Double length, String model, String clazz) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.length = length;
        this.model = model;
        this.clazz = clazz;
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
}
