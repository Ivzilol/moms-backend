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
import java.util.Optional;

@Service
public class ServiceQueryService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final ServiceEventService serviceEventService;

    public ServiceQueryService(ServiceRepository serviceRepository, ServiceMapper serviceMapper, ServiceEventService serviceEventService) {
        this.serviceRepository = serviceRepository;
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
        Optional<ServiceEntity> cachedObject = serviceRepository.findById(id);

        return cachedObject.orElseGet(() -> serviceEventService.reconstructServiceEntity(id));

    }


}
