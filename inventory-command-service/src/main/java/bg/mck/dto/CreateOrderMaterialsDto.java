package bg.mck.dto;

import java.util.List;
import java.util.Map;

public class CreateOrderMaterialsDto {


    private Map<String, List<CreateMaterialDTO>> materials;

    public CreateOrderMaterialsDto() {
    }

    public Map<String, List<CreateMaterialDTO>> getMaterials() {
        return materials;
    }

    public CreateOrderMaterialsDto setMaterials(Map<String, List<CreateMaterialDTO>> materials) {
        this.materials = materials;
        return this;
    }
}
