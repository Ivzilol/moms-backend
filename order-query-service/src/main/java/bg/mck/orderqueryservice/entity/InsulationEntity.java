package bg.mck.orderqueryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("insulation")
public class InsulationEntity extends BaseMaterialEntity {

    private String type;
    private String thickness;

    public InsulationEntity() {
    }


    public String getType() {
        return type;
    }

    public InsulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getThickness() {
        return thickness;
    }

    public InsulationEntity setThickness(String thickness) {
        this.thickness = thickness;
        return this;
    }
}
