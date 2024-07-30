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

@Service
public class TransportQueryService {

   private final TransportRepository transportRepository;
   private final TransportEventService transportEventService;
   private final TransportRedisService redisService;
   private final TransportMapper transportMapper;

    public TransportQueryService(TransportRepository transportRepository, TransportEventService transportEventService, TransportRedisService redisService, TransportMapper transportMapper) {
        this.transportRepository = transportRepository;
        this.transportEventService = transportEventService;
        this.redisService = redisService;
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
        TransportEntity cachedObject = redisService.getCachedObject(id);

        if (cachedObject != null) {
            return cachedObject;
        }

        return transportEventService.reconstructTransportEntity(id);
    }


}
