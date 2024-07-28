package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.MetalMapper;
import bg.mck.ordercommandservice.repository.MetalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetalService {
    private final MetalRepository metalRepository;
    private final MetalMapper metalMapper;

    public MetalService(MetalRepository metalRepository, MetalMapper metalMapper) {
        this.metalRepository = metalRepository;
        this.metalMapper = metalMapper;
    }

    public void cancelMetal(Long id) {
        Optional<MetalEntity> metalById = metalRepository.findById(id);
        if (metalById.isEmpty()){
            throw new IllegalArgumentException("Metal with id " + id + " not found");
        }
        metalById.get().setMaterialStatus(MaterialStatus.CANCELED);
        metalRepository.save(metalById.get());
    }
}
