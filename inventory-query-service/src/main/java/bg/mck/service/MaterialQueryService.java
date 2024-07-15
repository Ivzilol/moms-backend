package bg.mck.service;

import bg.mck.dto.FastenersDTO;
import bg.mck.dto.GalvanisedDTO;
import bg.mck.dto.InsulationDTO;
import bg.mck.dto.MetalDTO;
import bg.mck.entity.materialEntity.*;
import bg.mck.enums.MaterialType;
import bg.mck.mapper.MaterialMapper;
import bg.mck.repository.material.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    private final MaterialRedisService redisService;
    private final MaterialEventService materialEventService;

    public MaterialQueryService(FastenerRepository fastenerRepository, GalvanisedSheetRepository galvanisedSheetRepository, InsulationRepository insulationRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository, MetalRepository metalRepository, MaterialMapper materialMapper, MaterialRedisService redisService, MaterialEventService materialEventService) {
        this.fastenerRepository = fastenerRepository;
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.insulationRepository = insulationRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
        this.metalRepository = metalRepository;
        this.materialMapper = materialMapper;
        this.redisService = redisService;
        this.materialEventService = materialEventService;
    }

    public FastenersDTO getFastenerById(Long id) {
        FastenerEntity fastenerEntity = findById(id, MaterialType.FASTENERS, FastenerEntity.class);
        return materialMapper.toFastenerDTO(fastenerEntity);
    }

    public GalvanisedDTO getGalvanizedById(Long id) {
        GalvanisedSheetEntity galvanisedEntity = findById(id, MaterialType.GALVANIZED_SHEET, GalvanisedSheetEntity.class);
        return materialMapper.toGalvanizedDTO(galvanisedEntity);
    }

    public InsulationDTO getInsulationById(Long id) {
        InsulationEntity insulationEntity = findById(id, MaterialType.INSULATION, InsulationEntity.class);
        return materialMapper.toInsulationDTO(insulationEntity);
    }

    public MetalDTO getMetalById(Long id) {
        MetalEntity metalEntity = findById(id, MaterialType.METAL, MetalEntity.class);
        return materialMapper.toMetalDTO(metalEntity);
    }



    private <T extends BaseMaterialEntity> T findById(Long id, MaterialType materialType, Class<T> clazz) {
        T cachedEntity = redisService.getCachedObject(String.valueOf(id), materialType.name(), clazz);
        if (cachedEntity != null) {
            return cachedEntity;
        }

        return materialEventService.reconstructMaterialEntity(id, materialType.name(), clazz);
    }
}
