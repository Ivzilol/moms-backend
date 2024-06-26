package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.InsulationMapper;
import bg.mck.ordercommandservice.repository.InsulationRepository;
import org.springframework.stereotype.Service;

@Service
public class InsulationService {
    private final InsulationRepository insulationRepository;
    private final InsulationMapper insulationMapper;

    public InsulationService(InsulationRepository insulationRepository, InsulationMapper insulationMapper) {
        this.insulationRepository = insulationRepository;
        this.insulationMapper = insulationMapper;
    }
}
