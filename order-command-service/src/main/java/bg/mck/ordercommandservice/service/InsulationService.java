package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.InsulationEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.InsulationMapper;
import bg.mck.ordercommandservice.repository.InsulationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsulationService {
    private final InsulationRepository insulationRepository;
    private final InsulationMapper insulationMapper;

    public InsulationService(InsulationRepository insulationRepository, InsulationMapper insulationMapper) {
        this.insulationRepository = insulationRepository;
        this.insulationMapper = insulationMapper;
    }

    public void cancelInsulation(Long id) {
        Optional<InsulationEntity> insulationById = insulationRepository.findById(id);
        if (insulationById.isEmpty()) {
            throw new RuntimeException("Insulation with id " + id + " not found");
        }
        insulationById.get().setMaterialStatus(MaterialStatus.CANCELED);
        insulationRepository.save(insulationById.get());
    }
}
