package bg.mck.ordercommandservice.entity.enums;

public enum LengthUnits {
    MM("мм"),
    CM("см"),
    M("м");

    private final String value;

    LengthUnits(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
