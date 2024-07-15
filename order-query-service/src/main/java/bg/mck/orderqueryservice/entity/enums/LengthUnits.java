package bg.mck.orderqueryservice.entity.enums;

public enum LengthUnits {
    MM("mm"),
    CM("cm"),
    M("m");

    private final String value;

    LengthUnits(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
