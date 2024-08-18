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
        fastenerEntity.setLengthUnit(event.getLengthUnit());
        fastenerEntity.setStandard(event.getStandard());
        fastenerEntity.setClazz(event.getClazz());
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
        galvanisedSheetEntity.setMaxLengthUnit(event.getMaxLengthUnit());
        galvanisedSheetEntity.setNumberOfSheets(event.getNumberOfSheets());
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
        insulationEntity.setThicknessUnit(event.getThicknessUnit());
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
        metalEntity.setKind(event.getKind());
        metalEntity.setTotalWeightUnit(event.getTotalWeightUnit());
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
        panelEntity.setLengthUnit(event.getLengthUnit());
        panelEntity.setWidth(event.getWidth());
        panelEntity.setWidthUnit(event.getWidthUnit());
        panelEntity.setTotalThickness(event.getTotalThickness());
        panelEntity.setTotalThicknessUnit(event.getTotalThicknessUnit());
        panelEntity.setFrontSheetThickness(event.getFrontSheetThickness());
        panelEntity.setFrontSheetThicknessUnit(event.getFrontSheetThicknessUnit());
        panelEntity.setBackSheetThickness(event.getBackSheetThickness());
        panelEntity.setBackSheetThicknessUnit(event.getBackSheetThicknessUnit());
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
        rebarEntity.setMaxLengthUnit(event.getMaxLengthUnit());
        rebarEntity.setDescription(event.getDescription());
        rebarEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.rebarRepository.save(rebarEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterSet(RegisterSetEvent event) {
        SetEntity setEntity = new SetEntity();
        setEntity.setId(String.valueOf(event.getMaterialId()));
        setEntity.setName(event.getName());
        setEntity.setColor(event.getColor());
        setEntity.setMaxLength(event.getMaxLength());
        setEntity.setMaxLengthUnit(event.getMaxLengthUnit());
        setEntity.setDescription(event.getDescription());
        setEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        this.setRepository.save(setEntity);
    }

    @CacheEvict(value = "materials", key = "#event.category + '_' + #event.name.substring(0,2)")
    public void processingRegisterUnspecified(RegisterUnspecifiedEvent event) {
        UnspecifiedEntity unspecifiedEntity = new UnspecifiedEntity();
        unspecifiedEntity.setId(String.valueOf(event.getMaterialId()));
        unspecifiedEntity.setName(event.getName());
        unspecifiedEntity.setDescription(event.getDescription());
        unspecifiedEntity.setSpecificationFileUrl(event.getSpecificationFileUrl());
        unspecifiedRepository.save(unspecifiedEntity);
    }
}
