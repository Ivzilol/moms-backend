package bg.mck.service;

import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.service.ServiceRepository;
import bg.mck.repository.transport.TransportRepository;
import org.springframework.stereotype.Service;

@Service
public class TransportDeleteService {

    private final TransportRepository transportRepository;

    public TransportDeleteService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }


    public void deleteTransportById(String id) {
        boolean exists = transportRepository.existsById(id);
        if (exists) {
            transportRepository.deleteById(id);
        } else {
            throw new InventoryItemNotFoundException("Transport with id " + id + " not found");
        }
    }
}
