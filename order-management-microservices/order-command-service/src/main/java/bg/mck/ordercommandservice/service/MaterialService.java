package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.MaterialMapper;
import bg.mck.ordercommandservice.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }
}
