package bg.mck.controller;

import bg.mck.dto.CreateMaterialDTO;
import bg.mck.dto.CreateOrderMaterialsDto;
import bg.mck.dto.ErrorCreateMaterialDTO;
import bg.mck.exceptions.DuplicatedInventoryItemException;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static bg.mck.errors.ErrorsCreateMaterial.MATERIAL_EXIST;

@RestController
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
                            content = {@Content(mediaType = "application/json")})
            }
    )
    @PostMapping("/${APPLICATION_VERSION}/admin/inventory/command/materials/create")
    public ResponseEntity<?> createMaterial(@RequestBody CreateMaterialDTO createMaterialDTO) throws MethodArgumentNotValidException, NoSuchMethodException {
        if (createMaterialDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        this.materialRegisterService.createMaterial(createMaterialDTO);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Create Materials From Order Service")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Successful new materials registration from order service"),
                    @ApiResponse(responseCode = "400", description = "Incorrect field",
                            content = {@Content(mediaType = "application/json")})
            }
    )
    @PostMapping("/orders/materials/create")
    public ResponseEntity<Void> createMaterialsFromOrder(@RequestBody CreateOrderMaterialsDto createOrderMaterialsDto) throws MethodArgumentNotValidException, NoSuchMethodException {
        if (createOrderMaterialsDto == null || createOrderMaterialsDto.getMaterials() == null) {
            return ResponseEntity.badRequest().build();
        }
        materialRegisterService.createMaterialsFromOrder(createOrderMaterialsDto);
        return ResponseEntity.ok().build();
    }

//    private ResponseEntity<ErrorCreateMaterialDTO> errorRegistrationMaterial(
//            BindingResult result,
//            CreateMaterialDTO createMaterialDTO) {
//        ErrorCreateMaterialDTO errorCreateMaterialDTO = new ErrorCreateMaterialDTO();
//        boolean checkMaterialName = this.materialRegisterService.checkMaterialName(createMaterialDTO);
//        if (checkMaterialName) {
//            errorCreateMaterialDTO.setMaterialAlreadyExist(MATERIAL_EXIST);
//            throw new DuplicatedInventoryItemException("Material already exists.");
//        }
//        if (result.hasErrors()) {
//            List<String> errors = result.getAllErrors().stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .collect(Collectors.toList());
//            this.errorsService.setErrorsCreateMaterial(errors, errorCreateMaterialDTO);
//            return ResponseEntity.ok(errorCreateMaterialDTO);
//        }
//        return null;
//    }
}
