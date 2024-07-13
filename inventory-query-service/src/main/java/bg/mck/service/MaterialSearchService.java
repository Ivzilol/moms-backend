package bg.mck.service;

import bg.mck.dto.MaterialDTO;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.enums.MaterialType;
import bg.mck.repository.material.FastenerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialSearchService {

    private final FastenerRepository fastenerRepository;

    public MaterialSearchService(FastenerRepository fastenerRepository) {
        this.fastenerRepository = fastenerRepository;
    }

    public List<MaterialDTO> findMaterialByCategoryAndName(String category, String materialName) {
        List<MaterialDTO> materialDTO = new ArrayList<>();
        if (category.equals(String.valueOf(MaterialType.FASTENERS))) {
            String regex = "^" + materialName;
            List<FastenerEntity> fastenerEntities = this.fastenerRepository.findByPartOfName(regex);
            for (FastenerEntity entity : fastenerEntities) {
                MaterialDTO currentMaterial = new MaterialDTO();
                currentMaterial.setId(entity.getId());
                materialDTO.add(currentMaterial);
            }
        }
        return materialDTO;
    }
}
