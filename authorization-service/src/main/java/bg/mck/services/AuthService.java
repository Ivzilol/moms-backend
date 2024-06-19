package bg.mck.services;

import bg.mck.model.AuthorizationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final Logger logger = LoggerFactory.getLogger(AuthService .class);

    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public boolean isAuthorized(AuthorizationDTO authorizationDTO) {
        logger.info("Checking if the user is authorized to reach the path: " + authorizationDTO.getPathToReach());
        String roles = restTemplate.getForObject("http://authentication-service/"+ APPLICATION_VERSION +"/authentication/getroles/" + authorizationDTO.getToken()
                , String.class);
        try {
            boolean isAuthorized = roles.contains(authorizationDTO.getPathToReach());
            logger.info(isAuthorized ? "The user is authorized!" : "The user is not authorized!");
            return isAuthorized;
        } catch (NullPointerException e) {
            throw new NullPointerException("No roles found for the token: " + authorizationDTO.getToken());
        }
    }
}
