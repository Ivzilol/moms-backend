package bg.mck.ordercommandservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ORDER-QUERY-SERVICE")
public interface OrderQueryServiceClient {
}
