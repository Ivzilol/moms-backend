package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import org.springframework.stereotype.Service;

@Service
public class FastenerService {
    private final FastenerRepository fastenerRepository;
    private final FastenerMapper fastenerMapper;

    public FastenerService(FastenerRepository fastenerRepository, FastenerMapper fastenerMapper) {
        this.fastenerRepository = fastenerRepository;
        this.fastenerMapper = fastenerMapper;
    }
}
