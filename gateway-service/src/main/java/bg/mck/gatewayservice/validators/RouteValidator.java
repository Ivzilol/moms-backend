package bg.mck.gatewayservice.validators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class RouteValidator {

    public List<String> openApiEndPoints;
    public Predicate<ServerHttpRequest> isSecured;

    public RouteValidator(@Value("${APPLICATION_VERSION}") String applicationVersion) {
        this.openApiEndPoints = List.of(
                "/" + applicationVersion + "/user/user/query/login",
                "/" + applicationVersion + "/user/user/command/reset-password",
                "/" + applicationVersion + "/user/user/command/reset-password/update-password",
                "/" + applicationVersion + "/authentication/validate",
                "/" + applicationVersion + "/authentication/generate-token",
                "/" + applicationVersion + "/authentication/getroles",
                "/" + applicationVersion + "/authorization/isauthorized",
                "http://localhost:8761"
        );
        this.isSecured = request -> openApiEndPoints.contains(request.getURI().getPath());
    }
}
