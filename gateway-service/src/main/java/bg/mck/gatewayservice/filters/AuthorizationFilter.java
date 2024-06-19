package bg.mck.gatewayservice.filters;

import bg.mck.gatewayservice.models.dto.AuthorizationDTO;
import bg.mck.gatewayservice.validators.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static bg.mck.gatewayservice.validators.RouteValidator.APPLICATION_VERSION;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final RouteValidator routeValidator;
    private final RestTemplate restTemplate;


    @Autowired
    public AuthorizationFilter(RouteValidator routeValidator, RestTemplate restTemplate) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.restTemplate = restTemplate;
    }

    public static class Config {
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return chain.filter(exchange);
            }


            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (token == null || !token.startsWith("Bearer ") || token.trim().isBlank()) {
                return chain.filter(exchange);
            }

            token = token.substring(7);

            String path = exchange.getRequest().getURI().getPath().split("/+")[2];
            AuthorizationDTO authorizationDTO = new AuthorizationDTO(token, path);

            HttpEntity<AuthorizationDTO> entity = new HttpEntity<>(authorizationDTO);

            ResponseEntity<String> responseDto = restTemplate.exchange("http://authorization-service/" + APPLICATION_VERSION + "/authorization/isauthorized",
                    HttpMethod.POST, entity, String.class);

            if (responseDto.getStatusCode() == HttpStatus.OK) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }));
    }
}
