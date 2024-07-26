package bg.mck.orderqueryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("galvanised_sheets")
public class GalvanisedSheetEntity extends BaseMaterialEntity {

    private String type;

    @Field(name = "max_length")
    private String maxLength;

    @Field(name = "area")
    private String area;


    public GalvanisedSheetEntity() {
    }

    public GalvanisedSheetEntity(Double quantity, String note, String specificationFileUrl, String type, String maxLength, String area) {
        super(quantity, note, specificationFileUrl);
        this.type = type;
        this.maxLength = maxLength;
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public GalvanisedSheetEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public GalvanisedSheetEntity setMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getArea() {
        return area;
    }

    public GalvanisedSheetEntity setArea(String area) {
        this.area = area;
        return this;
    }
}
