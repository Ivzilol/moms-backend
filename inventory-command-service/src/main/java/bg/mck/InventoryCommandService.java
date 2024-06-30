package bg.mck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InventoryCommandService {
    public static void main(String[] args) {
        SpringApplication.run(InventoryCommandService.class, args);
    }
}