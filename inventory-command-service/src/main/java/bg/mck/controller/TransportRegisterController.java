package bg.mck.controller;

import bg.mck.dto.CreateTransportDTO;
import bg.mck.service.TransportRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/admin/inventory/command/transports")
public class TransportRegisterController {

    private final TransportRegisterService transportRegisterService;

    public TransportRegisterController(TransportRegisterService transportRegisterService) {
        this.transportRegisterService = transportRegisterService;
    }


    @PostMapping("/create")
    public ResponseEntity<Void> createTransport(@RequestBody @Valid CreateTransportDTO createTransportDTO) {
        transportRegisterService.registerTransport(createTransportDTO);
        return ResponseEntity.ok().build();
    }
}
