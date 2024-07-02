package bg.mck.userqueryservice.application.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConstants {

    public static String APPLICATION_VERSION;

    public ApplicationConstants(@Value("${APPLICATION_VERSION}") String version) {
        APPLICATION_VERSION = version;
    }
}
