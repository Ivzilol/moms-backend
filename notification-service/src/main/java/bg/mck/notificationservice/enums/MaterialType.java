package bg.mck.notificationservice.enums;

public enum MaterialType {
    FASTENERS("fastener"),
    GALVANIZED_SHEET("galvanisedSheet"),
    INSULATION("insulation"),
    METAL("metal"),
    PANELS("panel"),
    REBAR("rebar"),
    SET("set"),
    UNSPECIFIED("unspecified"),
    SERVICE("service"),
    TRANSPORT("transport");

    private final String description;

    MaterialType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
