package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.UnspecifiedEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.UnspecifiedMapper;
import bg.mck.ordercommandservice.repository.UnspecifiedRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnspecifiedService {
    private final UnspecifiedRepository unspecifiedRepository;
    private final UnspecifiedMapper unspecifiedMapper;

    public UnspecifiedService(UnspecifiedRepository unspecifiedRepository, UnspecifiedMapper unspecifiedMapper) {
        this.unspecifiedRepository = unspecifiedRepository;
        this.unspecifiedMapper = unspecifiedMapper;
    }

    public void cancelUnspecified(Long id) {
        Optional<UnspecifiedEntity> unspecifiedById = unspecifiedRepository.findById(id);
        if (unspecifiedById.isEmpty()) {
            throw new IllegalArgumentException("Unspecified with id " + id + " not found");
        }
        unspecifiedById.get().setMaterialStatus(MaterialStatus.CANCELED);
        unspecifiedRepository.save(unspecifiedById.get());
    }
}
