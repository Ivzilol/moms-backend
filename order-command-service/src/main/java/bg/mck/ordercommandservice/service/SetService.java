package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.SetEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.SetMapper;
import bg.mck.ordercommandservice.repository.SetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetService {
    private final SetRepository setRepository;
    private final SetMapper setMapper;

    public SetService(SetRepository setRepository, SetMapper setMapper) {
        this.setRepository = setRepository;
        this.setMapper = setMapper;
    }

    public void cancelSet(Long id) {
        Optional<SetEntity> byId = setRepository.findById(id);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Set with id " + id + " not found");
        }
        byId.get().setMaterialStatus(MaterialStatus.CANCELED);
        setRepository.save(byId.get());
    }
}
