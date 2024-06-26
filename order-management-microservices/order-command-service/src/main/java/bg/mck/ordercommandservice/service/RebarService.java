package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.RebarMapper;
import bg.mck.ordercommandservice.repository.RebarRepository;
import org.springframework.stereotype.Service;

@Service
public class RebarService {
    private final RebarRepository rebarRepository;
    private final RebarMapper rebarMapper;

    public RebarService(RebarRepository rebarRepository, RebarMapper rebarMapper) {
        this.rebarRepository = rebarRepository;
        this.rebarMapper = rebarMapper;
    }
}
