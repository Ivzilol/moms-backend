package bg.mck.entity.materialEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "insulation")
public class InsulationEntity {

    private String id;

    private String name;

    private Double thickness;

    public InsulationEntity() {
    }

    public InsulationEntity(String id, String name, Double thickness) {
        this.id = id;
        this.name = name;
        this.thickness = thickness;
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

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }
}
