package bg.mck.ordercommandservice.dto.errorDTO;

import bg.mck.ordercommandservice.dto.Material.FastenerDTO;
import bg.mck.ordercommandservice.dto.Material.RebarDTO;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.Set;

public class OrderCreationErrorsDTO {

    private String orderDescriptionError;

    private String deliveryDateError;

    private ConstructionSiteErrorDTO constructionSiteError;

    private Set<FastenerErrorDTO> fastenerError;
    private Set<RebarErrorDTO> rebarError;

    public OrderCreationErrorsDTO() {
    }


}
