package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.TransportDTO;
import bg.mck.ordercommandservice.entity.TransportEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.TransportMapper;
import bg.mck.ordercommandservice.repository.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransportService {
    private final TransportRepository transportRepository;
    private final TransportMapper transportMapper;

    public TransportService(TransportRepository transportRepository, TransportMapper transportMapper) {
        this.transportRepository = transportRepository;
        this.transportMapper = transportMapper;
    }

    public TransportDTO getTransportById(Long transportsId) {
        Optional<TransportEntity> transportById = transportRepository.findById(transportsId);
        if (transportById.isPresent()) {
            return transportMapper.toTransportDTO(transportById.get());
        }
        throw new RuntimeException("Transport with id " + transportsId + " not found");
    }

    public TransportEntity saveTransport(TransportDTO transport) {
        return transportRepository.save(transportMapper.toTransportEntity(transport));
    }

    public void cancelTransport(Long id) {
        Optional<TransportEntity> transportById = transportRepository.findById(id);
        if (transportById.isEmpty()) {
            throw new IllegalArgumentException("Transport with id " + id + " not found");
        }
        transportById.get().setMaterialStatus(MaterialStatus.CANCELED);
        transportRepository.save(transportById.get());
    }
}
