package bg.mck.orderqueryservice.entity;

import bg.mck.orderqueryservice.entity.enums.MaterialStatus;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("transports")
public class TransportEntity extends BaseMaterialEntity {

    private String maxLength;
    private String weight;
    private String truck;

    public TransportEntity() {
    }

    public TransportEntity(String id, Double quantity, String description, String specificationFileUrl, String adminNote, MaterialStatus materialStatus, String maxLength, String weight, String truck) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.weight = weight;
        this.truck = truck;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public TransportEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public TransportEntity setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTruck() {
        return truck;
    }

    public TransportEntity setTruck(String truck) {
        this.truck = truck;
        return this;
    }
}
