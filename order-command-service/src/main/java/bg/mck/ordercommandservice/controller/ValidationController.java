package bg.mck.ordercommandservice.controller;

import bg.mck.ordercommandservice.dto.*;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.exception.ErrorMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/order/command")
public class ValidationController {

    private static final Map<String, Class<?>> dtoClassMap = Map.of(
            MaterialType.FASTENERS.getDescription(), FastenerDTO.class,
            MaterialType.GALVANIZED_SHEET.getDescription(), GalvanisedSheetDTO.class,
            MaterialType.INSULATION.getDescription(), InsulationDTO.class,
            MaterialType.METAL.getDescription(), MetalDTO.class,
            MaterialType.PANELS.getDescription(), PanelDTO.class,
            MaterialType.REBAR.getDescription(), RebarDTO.class,
            MaterialType.SET.getDescription(), SetDTO.class,
            MaterialType.UNSPECIFIED.getDescription(), RebarDTO.class,
            MaterialType.TRANSPORT.getDescription(), TransportDTO.class,
            MaterialType.SERVICE.getDescription(), ServiceDTO.class
    );

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public ValidationController(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @Operation(summary = "Validate Order Fields",
            description = "Validates fields of an order based on the specified category. " +
                    "Returns validation errors if any fields are incorrect.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validation successful, no errors"),
            @ApiResponse(responseCode = "400", description = "Validation failed or invalid category provided",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/validate/{category}")
    public ResponseEntity<Map<String, String>> validateOrderFields(@Valid @RequestBody Map<String, Object> FieldsDataMap,
                                                                   @PathVariable String category,
                                                                   BindingResult bindingResult) {

        Class<?> dtoClass = dtoClassMap.get(category);
        if (dtoClass == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid category"));
        }

        Object dto = objectMapper.convertValue(FieldsDataMap, dtoClass);
        validator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = ErrorMapper.mapValidationError(bindingResult);
            return ResponseEntity.badRequest().body(fieldErrors);
        }
        return ResponseEntity.ok().build();
    }

}
