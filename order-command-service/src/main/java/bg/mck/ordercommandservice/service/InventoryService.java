package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.BaseDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.exception.CouldNotSendToInventoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final RestTemplate restTemplate;

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;

    public InventoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addMaterialsToInventory(Map<MaterialType, List<? extends BaseDTO>> materials) {

        try {
            String inventoryServiceUrl = "http://inventory-command-service/orders/materials/create";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("materials", materials);

            ResponseEntity<Void> response = restTemplate.postForEntity(inventoryServiceUrl, requestBody, Void.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                LOGGER.info("Order sent successfully to inventory service");
            } else {
                LOGGER.warn("Failed to send order to inventory service. Status code: {}", response.getStatusCode());
            }

        } catch (Exception e) {
            LOGGER.error("Failed to send order to inventory service", e);
            throw new CouldNotSendToInventoryException("Failed to send order to inventory service");
        }
    }

    public void sendMaterialsToInventory(OrderDTO orderDTO) {
        Map<MaterialType, List<? extends BaseDTO>> materials = mapOrderDTOToMaterials(orderDTO);
        addMaterialsToInventory(materials);
    }

    private Map<MaterialType, List<? extends BaseDTO>> mapOrderDTOToMaterials(OrderDTO orderDTO) {
        Map<MaterialType, List<? extends BaseDTO>> materials = new HashMap<>();
        switch (orderDTO.getMaterialType()) {
            case FASTENERS -> materials.put(MaterialType.FASTENERS, orderDTO.getFasteners());
            case GALVANIZED_SHEET -> materials.put(MaterialType.GALVANIZED_SHEET, orderDTO.getGalvanisedSheets());
            case INSULATION -> materials.put(MaterialType.INSULATION, orderDTO.getInsulation());
            case METAL -> materials.put(MaterialType.METAL, orderDTO.getMetals());
            case PANELS -> materials.put(MaterialType.PANELS, orderDTO.getPanels());
            case REBAR -> materials.put(MaterialType.REBAR, orderDTO.getRebars());
            case SERVICE -> materials.put(MaterialType.SERVICE, orderDTO.getServices());
            case SET -> materials.put(MaterialType.SET, orderDTO.getSets());
            case TRANSPORT -> materials.put(MaterialType.TRANSPORT, orderDTO.getTransports());
            case UNSPECIFIED -> materials.put(MaterialType.UNSPECIFIED, orderDTO.getUnspecified());
        }
        return materials;
    }

}
