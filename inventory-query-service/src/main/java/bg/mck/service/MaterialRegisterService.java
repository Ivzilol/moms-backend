package bg.mck.service;

import bg.mck.entity.materialEntity.*;
import bg.mck.enums.MaterialType;
import bg.mck.events.material.*;
import bg.mck.repository.material.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MaterialRegisterService {

    private final FastenerRepository fastenerRepository;

    private final GalvaniseRepository galvaniseRepository;

    private final InsulationRepository insulationRepository;

    private final MetalRepository metalRepository;

    private final PanelRepository panelRepository;

    private final RebarRepository rebarRepository;

    private final SetRepository setRepository;

    private final UnspecifiedRepository unspecifiedRepository;


    public MaterialRegisterService(FastenerRepository fastenerRepository, GalvaniseRepository galvaniseRepository, InsulationRepository insulationRepository, MetalRepository metalRepository, PanelRepository panelRepository, RebarRepository rebarRepository, SetRepository setRepository, UnspecifiedRepository unspecifiedRepository) {
        this.fastenerRepository = fastenerRepository;
        this.galvaniseRepository = galvaniseRepository;
        this.insulationRepository = insulationRepository;
        this.metalRepository = metalRepository;
        this.panelRepository = panelRepository;
        this.rebarRepository = rebarRepository;
        this.setRepository = setRepository;
        this.unspecifiedRepository = unspecifiedRepository;
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterMaterial(RegisterFastenerEvent event) {
        String category = event.getCategory();
        if (category.equals(String.valueOf(MaterialType.FASTENERS))) {
            saveFastenerMaterial(event);
        }
    }

    private void saveFastenerMaterial(RegisterFastenerEvent event) {
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setId(String.valueOf(event.getMaterialId()));
        fastenerEntity.setName(event.getName());
        fastenerEntity.setType(event.getType());
        fastenerEntity.setDiameter(event.getDiameter());
        fastenerEntity.setLength(event.getLength());
        fastenerEntity.setModel(event.getModel());
        fastenerEntity.setClazz(event.getClazz());
        fastenerEntity.setQuantity(event.getQuantity());
        fastenerEntity.setDescription(event.getDescription());
        fastenerEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.fastenerRepository.save(fastenerEntity);
    }

    public static String extractCategoryString(String category) {
        String regex = "materialType=([^}]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(category);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterGalvanized(RegisterGalvanizedEvent event) {
        String category = event.getCategory();
        if (category.equals(String.valueOf(MaterialType.GALVANIZED_SHEET))) {
            saveGalvanizedMaterial(event);
        }
    }

    private void saveGalvanizedMaterial(RegisterGalvanizedEvent event) {
        GalvanisedSheetEntity galvanisedSheetEntity = new GalvanisedSheetEntity();
        galvanisedSheetEntity.setId(String.valueOf(event.getMaterialId()));
        galvanisedSheetEntity.setName(event.getName());
        galvanisedSheetEntity.setType(event.getType());
        galvanisedSheetEntity.setMaxLength(event.getMaxLength());
        galvanisedSheetEntity.setArea(event.getArea());
        galvanisedSheetEntity.setQuantity(event.getQuantity());
        galvanisedSheetEntity.setDescription(event.getDescription());
        galvanisedSheetEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.galvaniseRepository.save(galvanisedSheetEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterInsulation(RegisterInsulationEvent event) {
        InsulationEntity insulationEntity = new InsulationEntity();
        insulationEntity.setId(String.valueOf(event.getMaterialId()));
        insulationEntity.setName(event.getName());
        insulationEntity.setType(event.getType());
        insulationEntity.setThickness(event.getThickness());
        insulationEntity.setQuantity(event.getQuantity());
        insulationEntity.setDescription(event.getDescription());
        insulationEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.insulationRepository.save(insulationEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterMetal(RegisterMetalEvent event) {
        MetalEntity metalEntity = new MetalEntity();
        metalEntity.setId(String.valueOf(event.getMaterialId()));
        metalEntity.setName(event.getName());
        metalEntity.setTotalWeight(event.getTotalWeight());
        metalEntity.setQuantity(event.getQuantity());
        metalEntity.setDescription(event.getDescription());
        metalEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.metalRepository.save(metalEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterPanel(RegisterPanelEvent event) {
        PanelEntity panelEntity = new PanelEntity();
        panelEntity.setId(String.valueOf(event.getMaterialId()));
        panelEntity.setName(event.getName());
        panelEntity.setType(event.getType());
        panelEntity.setColor(event.getColor());
        panelEntity.setLength(event.getLength());
        panelEntity.setWidth(event.getWidth());
        panelEntity.setTotalThickness(event.getTotalThickness());
        panelEntity.setFrontSheetThickness(event.getFrontSheetThickness());
        panelEntity.setBackSheetThickness(event.getBackSheetThickness());
        panelEntity.setQuantity(event.getQuantity());
        panelEntity.setDescription(event.getDescription());
        panelEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.panelRepository.save(panelEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterRebar(RegisterRebarEvent event) {
        RebarEntity rebarEntity = new RebarEntity();
        rebarEntity.setId(String.valueOf(event.getMaterialId()));
        rebarEntity.setName(event.getName());
        rebarEntity.setMaxLength(event.getMaxLength());
        rebarEntity.setWeight(event.getWeight());
        rebarEntity.setQuantity(event.getQuantity());
        rebarEntity.setDescription(event.getDescription());
        rebarEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.rebarRepository.save(rebarEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterSet(RegisterSetEvent event) {
        SetEntity setEntity = new SetEntity();
        setEntity.setId(String.valueOf(event.getMaterialId()));
        setEntity.setName(event.getName());
        setEntity.setGalvanisedSheetThickness(event.getGalvanisedSheetThickness());
        setEntity.setColor(event.getColor());
        setEntity.setMaxLength(event.getMaxLength());
        setEntity.setQuantity(event.getQuantity());
        setEntity.setDescription(event.getDescription());
        setEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.setRepository.save(setEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterUnspecified(RegisterUnspecifiedEvent event) {
        UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
        unspecifiedEntity.setId(String.valueOf(event.getMaterialId()));
        unspecifiedEntity.setName(event.getName());
        unspecifiedEntity.setQuantity(event.getQuantity());
        unspecifiedEntity.setDescription(event.getDescription());
        unspecifiedEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        unspecifiedRepository.save(unspecifiedEntity);
    }
}
