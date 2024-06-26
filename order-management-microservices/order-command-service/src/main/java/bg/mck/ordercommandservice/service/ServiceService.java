package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.ServiceDTO;
import bg.mck.ordercommandservice.entity.service.ServiceEntity;
import bg.mck.ordercommandservice.mapper.ServiceMapper;
import bg.mck.ordercommandservice.repository.ServiceRepository;
import bg.mck.ordercommandservice.exception.ServiceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;


    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public ServiceDTO getServiceById(Long servicesId) {
        Optional<ServiceEntity> serviceById = serviceRepository.findById(servicesId);
        if (serviceById.isPresent()) {
            return serviceMapper.toServiceDTO(serviceById.get());
        }
        throw new ServiceNotFoundException("Service with id " + servicesId + " not found");
    }
}
