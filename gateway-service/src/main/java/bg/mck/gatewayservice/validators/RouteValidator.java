package bg.mck.gatewayservice.validators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    @Value("${APPLICATION_VERSION}")
    public static String APPLICATION_VERSION;

    public static final List<String> openApiEndPoints = List.of(
            "/" + APPLICATION_VERSION + "/users/login",
            "/" + APPLICATION_VERSION + "/auth/validate",
            "/" + APPLICATION_VERSION + "/auth/generate-token",
            "http://localhost:8761"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndPoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}
