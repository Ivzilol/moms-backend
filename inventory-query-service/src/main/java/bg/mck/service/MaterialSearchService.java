package bg.mck.service;

import bg.mck.dto.MaterialDTO;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.enums.MaterialType;
import bg.mck.repository.material.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialSearchService {

    private final FastenerRepository fastenerRepository;

    private final InsulationRepository insulationRepository;

    private final MetalRepository metalRepository;

    private final PanelRepository panelRepository;

    private final RebarRepository rebarRepository;

    private final SetRepository setRepository;

    private final UnspecifiedRepository unspecifiedRepository;

    public MaterialSearchService(FastenerRepository fastenerRepository, InsulationRepository insulationRepository, MetalRepository metalRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository) {
        this.fastenerRepository = fastenerRepository;
        this.insulationRepository = insulationRepository;
        this.metalRepository = metalRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
    }

    public List<MaterialDTO> findMaterialByCategoryAndName(String category, String materialName) {
        String regex = "^" + materialName;
        List<MaterialDTO> materialDTO = new ArrayList<>();
        if (category.equals(String.valueOf(MaterialType.FASTENERS))) {
            processingFasteners(regex, materialDTO);
        } else if (category.equals(String.valueOf(MaterialType.GALVANIZED_SHEET))) {

        } else if (category.equals(String.valueOf(MaterialType.INSULATION))) {

        } else if (category.equals(String.valueOf(MaterialType.METAL))) {

        } else if (category.equals(String.valueOf(MaterialType.PANELS))) {

        } else if (category.equals(String.valueOf(MaterialType.REBAR))) {

        } else if (category.equals(String.valueOf(MaterialType.SET))) {

        } else if (category.equals(String.valueOf(MaterialType.UNSPECIFIED))) {

        }
        return materialDTO;
    }

    private void processingFasteners(String regex, List<MaterialDTO> materialDTO) {
        Sort sort = Sort.by(Sort.Direction.ASC, "materialName");
        List<FastenerEntity> fastenerEntities = this.fastenerRepository
                .findByPartOfName(regex, sort);
        for (FastenerEntity entity : fastenerEntities) {
            MaterialDTO currentMaterial = new MaterialDTO();
            currentMaterial.setId(entity.getId());
            currentMaterial.setName(entity.getName());
            currentMaterial.setType(entity.getType());
            currentMaterial.setDiameter(entity.getDiameter());
            currentMaterial.setLength(entity.getLength());
            currentMaterial.setModel(entity.getModel());
            currentMaterial.setClazz(entity.getClazz());
            currentMaterial.setQuantity(entity.getQuantity());
            currentMaterial.setDescription(entity.getDescription());
            currentMaterial.setSpecificationFileUrl(entity.getSpecificationFileUrl());
            materialDTO.add(currentMaterial);
        }
    }
}
