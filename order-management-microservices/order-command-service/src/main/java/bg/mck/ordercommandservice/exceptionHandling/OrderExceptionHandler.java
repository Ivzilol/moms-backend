package bg.mck.ordercommandservice.exceptionHandling;

import bg.mck.ordercommandservice.dto.errorDTO.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class OrderExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<OrderCreationErrorsDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
//
//        OrderCreationErrorsDTO orderCreationErrorsDTO = new OrderCreationErrorsDTO();
//        ServiceErrorDTO serviceError = new ServiceErrorDTO();
//        TransportErrorDTO transportError = new TransportErrorDTO();
//        MaterialErrorDTO materialError = new MaterialErrorDTO();
//        ConstructionSiteErrorDTO constructionSiteError = new ConstructionSiteErrorDTO();
//
//        orderCreationErrorsDTO.setMaterialError(materialError)
//                .setConstructionSiteError(constructionSiteError)
//                .setServiceError(serviceError)
//                .setTransportError(transportError);
//
//
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//
//            switch (error.getField().split("\\.")[0]) {
//                case "orderDescription":
//                    orderCreationErrorsDTO.setOrderDescriptionError(error.getDefaultMessage());
//                    break;
//                case "orderDate":
//                    orderCreationErrorsDTO.setOrderDateError(error.getDefaultMessage());
//                    break;
//                case "deliveryDate":
//                    orderCreationErrorsDTO.setDeliveryDateError(error.getDefaultMessage());
//                    break;
//                case "constructionSite":
//                    orderCreationErrorsDTO.getConstructionSiteError().setNameError(error.getDefaultMessage());
//                    orderCreationErrorsDTO.getConstructionSiteError().setConstructionNumberError(error.getDefaultMessage());
//                    break;
//                case "material":
//                    switch (error.getField().split("\\.")[1]) {
//                        case "fasteners[]":
//                            FastenerErrorDTO currentfastenerErrorDTO = new FastenerErrorDTO();
//                            Set<FastenerErrorDTO> fasteners = new HashSet<>();
//                            fasteners.add(currentfastenerErrorDTO);
//                            switch (error.getField().split("\\.")[2]) {
//                                case "description":
//
//
//                                case "diameter":
//
//                                case "length":
//
//                                case "model":
//
//                                case "clazz":
//
//                                case "quantity":
//                                    currentfastenerErrorDTO.setQuantityError(error.getDefaultMessage());
//                                    orderCreationErrorsDTO.getMaterialError().setFasteners(fasteners);
//                                    break;
//                                case "note":
//                                    currentfastenerErrorDTO.setNoteError(error.getDefaultMessage());
//                                    orderCreationErrorsDTO.getMaterialError().setFasteners(fasteners);
//                                    break;
//                                case "specificationFileUrl":
//
//                                default:
//                                    System.out.println("Error not handled: " + error.getField() + " " + error.getDefaultMessage());
//                                    break;
//                            }
//                            break;
//                    }
//
//                    break;
//                case "service":
//                    switch (error.getField().split("\\.")[1]) {
//                        case "description":
//                            serviceError.setDescriptionError(error.getDefaultMessage());
//                            break;
//                        case "price":
//                            serviceError.setPriceError(error.getDefaultMessage());
//                            break;
//                        default:
//                            System.out.println("Error not handled: " + error.getField() + " " + error.getDefaultMessage());
//                            break;
//                    }
//                    break;
//                case "transport":
//                    switch (error.getField().split("\\.")[1]) {
//                        case "description":
//                            transportError.setDescriptionError(error.getDefaultMessage());
//                            break;
//                        case "distance":
//                            transportError.setDistanceError(error.getDefaultMessage());
//                            break;
//                        default:
//                            System.out.println("Error not handled: " + error.getField() + " " + error.getDefaultMessage());
//                            break;
//                    }
//                    break;
//                default:
//                    System.out.println("Error not handled: " + error.getField() + " " + error.getDefaultMessage());
//                    System.out.println(error.getField().split("\\.")[0]);
//                    break;
//            }
//        }
//
//        return new ResponseEntity<>(orderCreationErrorsDTO, HttpStatus.BAD_REQUEST);
//    }
}





