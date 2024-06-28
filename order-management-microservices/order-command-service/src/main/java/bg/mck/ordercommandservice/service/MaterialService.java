package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.MaterialDTO;
import bg.mck.ordercommandservice.entity.material._MaterialEntity;
import bg.mck.ordercommandservice.mapper.MaterialMapper;
import bg.mck.ordercommandservice.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    public MaterialDTO getMaterialById(Long materialsId) {
        Optional<_MaterialEntity> materialById = materialRepository.findById(materialsId);
        if (materialById.isPresent()) {
            return materialMapper.toMaterialDTO(materialById.get());
        }
        return null;
    }

    public _MaterialEntity saveMaterial(MaterialDTO material) {
        return null;
    }
}
