package bg.mck.service;

import bg.mck.dto.FastenersDTO;
import bg.mck.dto.MaterialDTO;
import bg.mck.enums.MaterialType;
import bg.mck.repository.material.*;
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

        } else if (category.equals(String.valueOf(MaterialType.REBAR))) {

        } else if (category.equals(String.valueOf(MaterialType.SET))) {

        } else if (category.equals(String.valueOf(MaterialType.UNSPECIFIED))) {

        }
        return null;
    }
}
