package bg.mck.userqueryservice;

import jakarta.ws.rs.core.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static bg.mck.userqueryservice.application.constants.ApplicationConstants.APPLICATION_VERSION;


@SpringBootApplication
@EnableDiscoveryClient
public class UserQueryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserQueryServiceApplication.class, args);
        System.out.println(APPLICATION_VERSION);
    }

}
