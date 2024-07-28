package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.RebarEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.RebarMapper;
import bg.mck.ordercommandservice.repository.RebarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RebarService {
    private final RebarRepository rebarRepository;
    private final RebarMapper rebarMapper;

    public RebarService(RebarRepository rebarRepository, RebarMapper rebarMapper) {
        this.rebarRepository = rebarRepository;
        this.rebarMapper = rebarMapper;
    }

    public void cancelRebar(Long id) {
        Optional<RebarEntity> rebarById = rebarRepository.findById(id);
        if (rebarById.isEmpty()) {
            throw new IllegalArgumentException("Rebar with id " + id + " not found");
        }
        rebarById.get().setMaterialStatus(MaterialStatus.CANCELED);
        rebarRepository.save(rebarById.get());
    }
}
