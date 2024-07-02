package bg.mck.ordercommandservice.dto.errorDTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public class FastenerErrorDTO {
    private String descriptionError;
    private String diameterError;
    private String lengthError;
    private String modelError;
    private String clazzError;
    private String quantityError;
    private String noteError;
    private String specificationFileUrlError;

    public FastenerErrorDTO() {
    }

    public FastenerErrorDTO(String descriptionError, String diameterError, String lengthError, String modelError, String clazzError, String quantityError, String noteError, String specificationFileUrlError) {
        this.descriptionError = descriptionError;
        this.diameterError = diameterError;
        this.lengthError = lengthError;
        this.modelError = modelError;
        this.clazzError = clazzError;
        this.quantityError = quantityError;
        this.noteError = noteError;
        this.specificationFileUrlError = specificationFileUrlError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public FastenerErrorDTO setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
        return this;
    }

    public String getDiameterError() {
        return diameterError;
    }

    public FastenerErrorDTO setDiameterError(String diameterError) {
        this.diameterError = diameterError;
        return this;
    }

    public String getLengthError() {
        return lengthError;
    }

    public FastenerErrorDTO setLengthError(String lengthError) {
        this.lengthError = lengthError;
        return this;
    }

    public String getModelError() {
        return modelError;
    }

    public FastenerErrorDTO setModelError(String modelError) {
        this.modelError = modelError;
        return this;
    }

    public String getClazzError() {
        return clazzError;
    }

    public FastenerErrorDTO setClazzError(String clazzError) {
        this.clazzError = clazzError;
        return this;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public FastenerErrorDTO setQuantityError(String quantityError) {
        this.quantityError = quantityError;
        return this;
    }

    public String getNoteError() {
        return noteError;
    }

    public FastenerErrorDTO setNoteError(String noteError) {
        this.noteError = noteError;
        return this;
    }

    public String getSpecificationFileUrlError() {
        return specificationFileUrlError;
    }

    public FastenerErrorDTO setSpecificationFileUrlError(String specificationFileUrlError) {
        this.specificationFileUrlError = specificationFileUrlError;
        return this;
    }
}
