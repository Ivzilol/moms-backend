package bg.mck.orderqueryservice.entity.enums;

public enum AreaUnits {
    CM2("cm2"),
    M2("m2");

    private final String value;

    AreaUnits(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
