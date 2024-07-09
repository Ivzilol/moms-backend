package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.UnspecifiedMapper;
import bg.mck.ordercommandservice.repository.UnspecifiedRepository;
import org.springframework.stereotype.Service;

@Service
public class UnspecifiedService {
    private final UnspecifiedRepository unspecifiedRepository;
    private final UnspecifiedMapper unspecifiedMapper;

    public UnspecifiedService(UnspecifiedRepository unspecifiedRepository, UnspecifiedMapper unspecifiedMapper) {
        this.unspecifiedRepository = unspecifiedRepository;
        this.unspecifiedMapper = unspecifiedMapper;
    }
}
