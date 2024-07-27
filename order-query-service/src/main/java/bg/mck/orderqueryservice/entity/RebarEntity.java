package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("rebars")
public class RebarEntity extends BaseMaterialEntity {

    private String maxLength;
    private String weight;

    public RebarEntity() {

    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public RebarEntity setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}
