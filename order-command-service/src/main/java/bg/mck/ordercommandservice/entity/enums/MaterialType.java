package bg.mck.ordercommandservice.entity.enums;

public enum MaterialType {
    FASTENERS("крепежи"),
    GALVANIZED_SHEET("ламарина"),
    INSULATION("изолация"),
    METAL("метали"),
    PANELS("панели"),
    REBAR("армировка"),
    SET("oкомплектовка"),
    UNSPECIFIED("други");

    private final String description;

    MaterialType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
