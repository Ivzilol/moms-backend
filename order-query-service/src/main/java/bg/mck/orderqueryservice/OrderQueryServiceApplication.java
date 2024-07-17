package bg.mck.orderqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderQueryServiceApplication.class, args);
	}

}