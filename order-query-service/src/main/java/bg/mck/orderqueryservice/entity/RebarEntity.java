package bg.mck.orderqueryservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("rebars")
public class RebarEntity extends BaseMaterialEntity {

    private String maxLength;
    private String quantity;

    public RebarEntity() {

    }

    public String getMaxLength() {
        return maxLength;
    }

    public RebarEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public RebarEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
