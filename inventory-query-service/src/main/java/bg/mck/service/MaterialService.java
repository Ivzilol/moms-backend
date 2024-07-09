package bg.mck.service;

import bg.mck.dto.CategoryDTO;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.enums.MaterialType;
import bg.mck.events.RegisterMaterialEvent;
import bg.mck.repository.FastenerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MaterialService {

    private final FastenerRepository fastenerRepository;


    public MaterialService(FastenerRepository fastenerRepository) {
        this.fastenerRepository = fastenerRepository;
    }

    void processingRegisterMaterial(RegisterMaterialEvent event) {
        String category= extractCategoryString(event.getCategory());
        assert category != null;
        if (category.equals(String.valueOf(MaterialType.FASTENERS))) {
            saveFastenerMaterial(event);
        }
    }

    private void saveFastenerMaterial(RegisterMaterialEvent event) {
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

    private String extractCategoryString(String category) {
        String regex = "materialType=([^}]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(category);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
