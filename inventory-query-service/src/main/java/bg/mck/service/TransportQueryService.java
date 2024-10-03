package bg.mck.service;

import bg.mck.dto.TransportDTO;
import bg.mck.entity.transportEntity.TransportEntity;
import bg.mck.mapper.TransportMapper;
import bg.mck.repository.transport.TransportRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransportQueryService {

   private final TransportRepository transportRepository;
   private final TransportEventService transportEventService;
   private final TransportMapper transportMapper;

    public TransportQueryService(TransportRepository transportRepository, TransportEventService transportEventService, TransportMapper transportMapper) {
        this.transportRepository = transportRepository;
        this.transportEventService = transportEventService;
        this.transportMapper = transportMapper;
    }

    public TransportDTO getTransportById(String id) {
        TransportEntity entity = findById(id);
        return transportMapper.toTransportDTO(entity);
    }

    public List<TransportDTO> getAllTransports() {
        List<TransportDTO> dtos = new ArrayList<>();

        List<TransportEntity> entities = transportRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        for (TransportEntity entity : entities) {
            dtos.add(getTransportById(entity.getId()));
        }

        return dtos;
    }

    @Cacheable(value = "transports", key = "#transportName")
    public List<TransportDTO> findTransportByName(String transportName) {
        String regex = "^" + transportName;
        Sort sort = Sort.by(Sort.Direction.ASC, "transportName");

        List<TransportEntity> entities = transportRepository.findByPartOfName(regex, sort);

        return transportMapper.toTransportDTOs(entities);
    }



    private TransportEntity findById(String id) {
        Optional<TransportEntity> cachedObject = transportRepository.findById(id);

        return cachedObject.orElseGet(() -> transportEventService.reconstructTransportEntity(id));
    }


}
