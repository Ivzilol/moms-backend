package bg.mck.orderqueryservice.dto;

public class MetalDTO extends BaseDTO{

    private String weight;

    public MetalDTO() {
    }

    public MetalDTO(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public MetalDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}
