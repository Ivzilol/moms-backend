package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.BaseDTO;
import bg.mck.ordercommandservice.dto.OrderDTO;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.exception.CouldNotSendToInventoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

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
            String inventoryServiceUrl = "http://inventory-command-service/" + APPLICATION_VERSION + "/admin/inventory/command/materials/create";

            ResponseEntity<Void> response = restTemplate.postForEntity(inventoryServiceUrl, materials, Void.class);

            System.out.println("Response: " + response.getStatusCode());

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

}
