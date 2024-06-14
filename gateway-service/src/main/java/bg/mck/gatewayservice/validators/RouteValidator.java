package bg.mck.gatewayservice.validators;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndPoints = List.of(
            "/user/login",
            "/auth/generate-token",
            "http://localhost:8761"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndPoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}
