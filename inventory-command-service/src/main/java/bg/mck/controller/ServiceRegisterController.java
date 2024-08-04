package bg.mck.controller;

import bg.mck.dto.CreateServiceDTO;
import bg.mck.service.ServiceRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/admin/inventory/command/services")
public class ServiceRegisterController {

    private final ServiceRegisterService serviceRegisterService;

    public ServiceRegisterController(ServiceRegisterService serviceRegisterService) {
        this.serviceRegisterService = serviceRegisterService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createService(@RequestBody @Valid CreateServiceDTO createServiceDTO) {
        serviceRegisterService.registerService(createServiceDTO);
        return ResponseEntity.ok().build();
    }
}
