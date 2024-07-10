package bg.mck.service;

import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.entity.materialEntity.GalvanisedSheetEntity;
import bg.mck.entity.materialEntity.InsulationEntity;
import bg.mck.entity.materialEntity.MetalEntity;
import bg.mck.enums.MaterialType;
import bg.mck.events.RegisterFastenerEvent;
import bg.mck.events.RegisterGalvanizedEvent;
import bg.mck.events.RegisterInsulationEvent;
import bg.mck.events.RegisterMetalEvent;
import bg.mck.repository.FastenerRepository;
import bg.mck.repository.GalvaniseRepository;
import bg.mck.repository.InsulationRepository;
import bg.mck.repository.MetalRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MaterialService {

    private final FastenerRepository fastenerRepository;

    private final GalvaniseRepository galvaniseRepository;

    private final InsulationRepository insulationRepository;

    private final MetalRepository metalRepository;


    public MaterialService(FastenerRepository fastenerRepository, GalvaniseRepository galvaniseRepository, InsulationRepository insulationRepository, MetalRepository metalRepository) {
        this.fastenerRepository = fastenerRepository;
        this.galvaniseRepository = galvaniseRepository;
        this.insulationRepository = insulationRepository;
        this.metalRepository = metalRepository;
    }

    void processingRegisterMaterial(RegisterFastenerEvent event) {
        String category = extractCategoryString(event.getCategory());
        assert category != null;
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

    public void processingRegisterGalvanized(RegisterGalvanizedEvent event) {
        String category = extractCategoryString(event.getCategory());
        assert category != null;
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
}
