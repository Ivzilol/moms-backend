package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }
}
