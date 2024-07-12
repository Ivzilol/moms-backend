package bg.mck.controller;

import bg.mck.dto.CreateMaterialDTO;
import bg.mck.dto.ErrorCreateMaterialDTO;
import bg.mck.service.ErrorsService;
import bg.mck.service.MaterialRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static bg.mck.errors.ErrorsCreateMaterial.MATERIAL_EXIST;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/inventory/command")
public class MaterialRegisterController {

    private final MaterialRegisterService materialRegisterService;

    private final ErrorsService errorsService;

    public MaterialRegisterController(MaterialRegisterService materialRegisterService, ErrorsService errorsService) {
        this.materialRegisterService = materialRegisterService;
        this.errorsService = errorsService;
    }

    @Operation(summary = "Create Material")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Successful registration"),
                    @ApiResponse(responseCode = "400", description = "Incorrect field",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorCreateMaterialDTO.class))})
            }
    )
    @PostMapping("/create")
    public ResponseEntity<?> createMaterial(@RequestBody @Valid CreateMaterialDTO createMaterialDTO,
                                            BindingResult result) {
        ResponseEntity<ErrorCreateMaterialDTO> errorCreateMaterialDTO =
                errorRegistrationMaterial(result, createMaterialDTO);
        if (errorCreateMaterialDTO != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorCreateMaterialDTO.getBody());
        }

        this.materialRegisterService.createMaterial(createMaterialDTO);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<ErrorCreateMaterialDTO> errorRegistrationMaterial(
            BindingResult result,
            CreateMaterialDTO createMaterialDTO) {
        ErrorCreateMaterialDTO errorCreateMaterialDTO = new ErrorCreateMaterialDTO();
        boolean checkMaterialName = this.materialRegisterService.checkMaterialName(createMaterialDTO);
        if (checkMaterialName) {
            errorCreateMaterialDTO.setMaterialAlreadyExist(MATERIAL_EXIST);
        }
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            this.errorsService.setErrorsCreateMaterial(errors, errorCreateMaterialDTO);
            return ResponseEntity.ok(errorCreateMaterialDTO);
        }
        return null;
    }
}
