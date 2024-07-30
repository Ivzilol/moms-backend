package bg.mck.service;

import bg.mck.dto.ServiceDTO;
import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.mapper.ServiceMapper;
import bg.mck.repository.service.ServiceRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceQueryService {

    private final ServiceRepository serviceRepository;
    private final ServiceRedisService redisService;
    private final ServiceMapper serviceMapper;
    private final ServiceEventService serviceEventService;

    public ServiceQueryService(ServiceRepository serviceRepository, ServiceRedisService redisService, ServiceMapper serviceMapper, ServiceEventService serviceEventService) {
        this.serviceRepository = serviceRepository;
        this.redisService = redisService;
        this.serviceMapper = serviceMapper;
        this.serviceEventService = serviceEventService;
    }

    public ServiceDTO getServiceById(String id) {
        ServiceEntity entity = findById(id);
        return serviceMapper.toServiceDTO(entity);
    }

    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> dtos = new ArrayList<>();

        List<ServiceEntity> entities = serviceRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        for (ServiceEntity entity : entities) {
            dtos.add(getServiceById(entity.getId()));
        }

        return dtos;
    }

    @Cacheable(value = "services", key = "#serviceName")
    public List<ServiceDTO> findServiceByName(String serviceName) {
        String regex = "^" + serviceName;
        Sort sort = Sort.by(Sort.Direction.ASC, "serviceName");

        List<ServiceEntity> entities = serviceRepository.findByPartOfName(regex, sort);

        return serviceMapper.toServiceDTOs(entities);
    }



    private ServiceEntity findById(String id) {
        ServiceEntity cachedObject = redisService.getCachedObject(id);

        if (cachedObject != null) {
            return cachedObject;
        }

        return serviceEventService.reconstructServiceEntity(id);
    }


}
