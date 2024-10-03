package bg.mck.service;

import bg.mck.dto.*;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.MaterialType;
import bg.mck.exceptions.InvalidCategoryException;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.material.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialQueryService {

    private final FastenerRepository fastenerRepository;
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final InsulationRepository insulationRepository;
    private final PanelRepository panelRepository;
    private final RebarRepository rebarRepository;
    private final SetRepository setRepository;
    private final UnspecifiedRepository unspecifiedRepository;
    private final MetalRepository metalRepository;
    private final MaterialMapper materialMapper;
    private final MaterialEventService materialEventService;

    public MaterialQueryService(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, MetalRepository metalRepository, MaterialMapper materialMapper, MaterialEventService materialEventService) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
        this.metalRepository = metalRepository;
        this.materialMapper = materialMapper;
        this.materialEventService = materialEventService;
    }

    public MaterialDTO getMaterialByCategoryAndId(String category, String id) {
        String categoryUpperCase = category.toUpperCase();

        if (categoryUpperCase.equals(MaterialType.FASTENERS.name())) {
            return getFastenerById(id);
        } else if (categoryUpperCase.equals(MaterialType.GALVANIZED_SHEET.name())) {
            return getGalvanizedById(id);
        } else if (categoryUpperCase.equals(MaterialType.INSULATION.name())) {
            return getInsulationById(id);
        } else if (categoryUpperCase.equals(MaterialType.PANELS.name())) {
            return getPanelById(id);
        } else if (categoryUpperCase.equals(MaterialType.REBAR.name())) {
            return getRebarById(id);
        } else if (categoryUpperCase.equals(MaterialType.SET.name())) {
            return getSetById(id);
        } else if (categoryUpperCase.equals(MaterialType.UNSPECIFIED.name())) {
            return getUnspecifiedById(id);
        } else if (categoryUpperCase.equals(MaterialType.METAL.name())) {
            return getMetalById(id);
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + category);
        }
    }

    public List<MaterialDTO> getAllMaterialsByCategory(String category) {
        String categoryUpperCase = category.toUpperCase();
        List<MaterialDTO> dtos = new ArrayList<>();

        if (categoryUpperCase.equals(MaterialType.FASTENERS.name())) {
            List<FastenerEntity> fasteners = fastenerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (FastenerEntity fastener : fasteners) {
                dtos.add(getFastenerById(fastener.getId()));
            }
        } else if (categoryUpperCase.equals(MaterialType.GALVANIZED_SHEET.name())) {
            List<GalvanisedSheetEntity> galvanisedSheets = galvanisedSheetRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (GalvanisedSheetEntity galvanisedSheet : galvanisedSheets) {
                dtos.add(getGalvanizedById(galvanisedSheet.getId()));
            }
        } else if (categoryUpperCase.equals(MaterialType.INSULATION.name())) {
            List<InsulationEntity> installations = insulationRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (InsulationEntity insulation : installations) {
                dtos.add(getInsulationById(insulation.getId()));
            }
        } else if (categoryUpperCase.equals(MaterialType.PANELS.name())) {
            List<PanelEntity> panels = panelRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (PanelEntity panel : panels) {
                dtos.add(getPanelById(panel.getId()));
            }
        } else if (categoryUpperCase.equals(MaterialType.REBAR.name())) {
            List<RebarEntity> rebars = rebarRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (RebarEntity rebar : rebars) {
                dtos.add(getRebarById(rebar.getId()));
            }
        } else if (categoryUpperCase.equals(MaterialType.SET.name())) {
            List<SetEntity> sets = setRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (SetEntity set : sets) {
                dtos.add(getSetById(set.getId()));
            }
        } else if (categoryUpperCase.equals(MaterialType.UNSPECIFIED.name())) {
            List<UnspecifiedEntity> unspecifiedEntities = unspecifiedRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (UnspecifiedEntity unspecified : unspecifiedEntities) {
                dtos.add(getUnspecifiedById(unspecified.getId()));
            }
        } else if (categoryUpperCase.equals(MaterialType.METAL.name())) {
            List<MetalEntity> metals = metalRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

            for (MetalEntity metal : metals) {
                dtos.add(getMetalById(metal.getId()));
            }
        } else {
            throw new InvalidCategoryException("Unhandled category type: " + category);
        }

        return dtos;
    }

    private FastenersDTO getFastenerById(String id) {
        FastenerEntity fastenerEntity = findById(id, MaterialType.FASTENERS, FastenerEntity.class);
        return materialMapper.toFastenerDTO(fastenerEntity);
    }

    private GalvanisedDTO getGalvanizedById(String id) {
        GalvanisedSheetEntity galvanisedEntity = findById(id, MaterialType.GALVANIZED_SHEET, GalvanisedSheetEntity.class);
        return materialMapper.toGalvanizedDTO(galvanisedEntity);
    }

    private InsulationDTO getInsulationById(String id) {
        InsulationEntity insulationEntity = findById(id, MaterialType.INSULATION, InsulationEntity.class);
        return materialMapper.toInsulationDTO(insulationEntity);
    }

    private MetalDTO getMetalById(String id) {
        MetalEntity metalEntity = findById(id, MaterialType.METAL, MetalEntity.class);
        return materialMapper.toMetalDTO(metalEntity);
    }

    private PanelsDTO getPanelById(String id) {
        PanelEntity panelEntity = findById(id, MaterialType.PANELS, PanelEntity.class);
        return materialMapper.toPanelDTO(panelEntity);
    }

    private RebarDTO getRebarById(String id) {
        RebarEntity rebarEntity = findById(id, MaterialType.REBAR, RebarEntity.class);
        return materialMapper.toRebarDTO(rebarEntity);
    }

    private SetDTO getSetById(String id) {
        SetEntity setEntity = findById(id, MaterialType.SET, SetEntity.class);
        return materialMapper.toSetDTO(setEntity);
    }

    private UnspecifiedDTO getUnspecifiedById(String id) {
        UnspecifiedEntity unspecifiedEntity = findById(id, MaterialType.UNSPECIFIED, UnspecifiedEntity.class);
        return materialMapper.toUnspecifiedDTO(unspecifiedEntity);
    }

    private <T extends BaseMaterialEntity> T findById(String id, MaterialType materialType, Class<T> clazz) {
        return materialEventService.reconstructMaterialEntity(id, materialType.name(), clazz);
    }



}
