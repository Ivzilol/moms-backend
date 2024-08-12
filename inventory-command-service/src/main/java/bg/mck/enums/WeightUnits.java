package bg.mck.enums;

public enum WeightUnits {
    G("g"),
    KG("kg"),
    T("t");

    private final String value;

    WeightUnits(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
