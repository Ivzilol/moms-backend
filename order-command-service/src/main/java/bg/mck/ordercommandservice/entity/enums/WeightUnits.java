package bg.mck.ordercommandservice.entity.enums;

public enum WeightUnits {
    G("г"),
    KG("кг"),
    T("т");

    private final String value;

    WeightUnits(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
