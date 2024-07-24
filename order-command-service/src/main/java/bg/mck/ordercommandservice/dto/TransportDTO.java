package bg.mck.ordercommandservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.web.multipart.MultipartFile;

public class TransportDTO extends BaseDTO {

    @DecimalMin(value = "0.0", message = "MaxLength must be positive")
    @Column(name = "max_length_in_centimeters")
    private Double maxLength;

    @DecimalMin(value = "0.0", message = "weight must be positive")
    @Column(name = "weight_in_kg")
    private Double weight;

    private String truck;

    public TransportDTO() {
    }

    public TransportDTO(Long id, Double quantity, String description, String specificationFileUrl, String adminNote, String materialStatus, Double maxLength, Double weight, String truck) {
        super(id, quantity, description, specificationFileUrl, adminNote, materialStatus);
        this.maxLength = maxLength;
        this.weight = weight;
        this.truck = truck;
    }

    public Double getMaxLength() {
        return maxLength;
    }

    public TransportDTO setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public TransportDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getTruck() {
        return truck;
    }

    public TransportDTO setTruck(String truck) {
        this.truck = truck;
        return this;
    }
}
