package bg.mck.gatewayservice.validators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static String APPLICATION_VERSION;


    public RouteValidator(@Value("${APPLICATION_VERSION}") String version) {
        APPLICATION_VERSION = version;
    }

    public static final List<String> openApiEndPoints = List.of(
            "/" + APPLICATION_VERSION + "/users/login",
            "/" + APPLICATION_VERSION + "/authentication/validate",
            "/" + APPLICATION_VERSION + "/authentication/generate-token",
            "/" + APPLICATION_VERSION + "/authentication/getroles",
            "/" + APPLICATION_VERSION + "/authorization/isauthorized",
            "http://localhost:8761"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndPoints.contains(request.getURI().getPath());
}
