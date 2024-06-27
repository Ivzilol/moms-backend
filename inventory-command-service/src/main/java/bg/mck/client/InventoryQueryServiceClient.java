package bg.mck.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "INVENTORY-QUERY-SERVICE")
public interface InventoryQueryServiceClient {


}
