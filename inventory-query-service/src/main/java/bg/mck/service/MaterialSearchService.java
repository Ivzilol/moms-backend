package bg.mck.service;

import bg.mck.dto.MaterialDTO;
import bg.mck.enums.MaterialType;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.repository.material.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialSearchService {

    private final FastenerRepository fastenerRepository;

    private final GalvanisedSheetRepository galvanisedSheetRepository;

    private final InsulationRepository insulationRepository;

    private final MetalRepository metalRepository;

    private final PanelRepository panelRepository;

    private final RebarRepository rebarRepository;

    private final SetRepository setRepository;

    private final UnspecifiedRepository unspecifiedRepository;

    public MaterialSearchService(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, MetalRepository metalRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.metalRepository = metalRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
    }

    @Cacheable(value = "materials", key = "#category + '_' + #materialName")
    public List<? extends MaterialDTO> findMaterialByCategoryAndName(String category, String materialName) {
        String regex = "^" + materialName;
        Sort sort = Sort.by(Sort.Direction.ASC, "materialName");
        if (category.equals(String.valueOf(MaterialType.FASTENERS))) {
            return this.fastenerRepository.findByPartOfName(regex, sort);
        } else if (category.equals(String.valueOf(MaterialType.GALVANIZED_SHEET))) {
            return this.galvanisedSheetRepository.findByPartOfName(regex, sort);
        } else if (category.equals(String.valueOf(MaterialType.INSULATION))) {
            return this.insulationRepository.findByPartOfName(regex, sort);
        } else if (category.equals(String.valueOf(MaterialType.METAL))) {
            return this.metalRepository.findByPartOfName(regex, sort);
        } else if (category.equals(String.valueOf(MaterialType.PANELS))) {
            return this.panelRepository.findByPartOfName(regex, sort);
        } else if (category.equals(String.valueOf(MaterialType.REBAR))) {
            return this.rebarRepository.findByPartOfName(regex, sort);
        } else if (category.equals(String.valueOf(MaterialType.SET))) {
            return this.setRepository.findByPartOfName(regex, sort);
        } else if (category.equals(String.valueOf(MaterialType.UNSPECIFIED))) {
            return this.unspecifiedRepository.findByPartOfName(regex, sort);
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + category);
        }

    }
}
