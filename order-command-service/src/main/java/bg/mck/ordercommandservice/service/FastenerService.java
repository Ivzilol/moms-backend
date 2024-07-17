package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
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

    public FastenerDTO createFastener(FastenerDTO fastenerDTO) {
        FastenerEntity fastenerEntity = fastenerMapper.toEntity(fastenerDTO);
        fastenerEntity = fastenerRepository.save(fastenerEntity);
        return fastenerMapper.toDTO(fastenerEntity);
    }

    public FastenerEntity getFastenerById(Long id) {
        return fastenerRepository.findById(id).orElse(null);
    }


}
