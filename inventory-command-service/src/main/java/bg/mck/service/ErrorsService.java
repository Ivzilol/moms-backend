package bg.mck.service;

import bg.mck.dto.ErrorCreateMaterialDTO;
import org.springframework.stereotype.Service;

import java.util.List;

import static bg.mck.errors.ErrorsCreateMaterial.*;

@Service
public class ErrorsService {

    public void setErrorsCreateMaterial(List<String> errors, ErrorCreateMaterialDTO errorCreateMaterialDTO) {

        for (String error : errors) {
            switch (error) {
                case INVALID_MATERIAL_TYPE ->
                        errorCreateMaterialDTO.setInvalidMaterialType(INVALID_MATERIAL_TYPE);
                case INVALID_LENGTH ->
                        errorCreateMaterialDTO.setInvalidLength(INVALID_LENGTH);
                case INVALID_QUANTITY ->
                        errorCreateMaterialDTO.setInvalidQuantity(INVALID_QUANTITY);
                case INVALID_AREA ->
                        errorCreateMaterialDTO.setInvalidArea(INVALID_AREA);
                case INVALID_THICKNESS ->
                        errorCreateMaterialDTO.setInvalidThickness(INVALID_THICKNESS);
                case INVALID_FRONT_THICKNESS ->
                        errorCreateMaterialDTO.setInvalidFrontThickness(INVALID_FRONT_THICKNESS);
                case INVALID_BACK_THICKNESS ->
                        errorCreateMaterialDTO.setInvalidBackThickness(INVALID_BACK_THICKNESS);
                case INVALID_WIDTH ->
                        errorCreateMaterialDTO.setInvalidWidth(INVALID_WIDTH);
                case INVALID_MAX_LENGTH ->
                        errorCreateMaterialDTO.setInvalidMaxLength(INVALID_MAX_LENGTH);
                case INVALID_TOTAL_WEIGHT ->
                        errorCreateMaterialDTO.setInvalidTotalWeight(INVALID_TOTAL_WEIGHT);
                case INVALID_GALVANISE ->
                        errorCreateMaterialDTO.setInvalidGalvanise(INVALID_GALVANISE);
            }
        }

    }
}
