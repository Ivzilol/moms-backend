package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.MetalMapper;
import bg.mck.ordercommandservice.repository.MetalRepository;
import org.springframework.stereotype.Service;

@Service
public class MetalService {
    private final MetalRepository metalRepository;
    private final MetalMapper metalMapper;

    public MetalService(MetalRepository metalRepository, MetalMapper metalMapper) {
        this.metalRepository = metalRepository;
        this.metalMapper = metalMapper;
    }
}
