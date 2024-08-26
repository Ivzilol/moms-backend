package bg.mck.ordercommandservice.mapper.util;

public interface Concatenator {
    default <T extends Enum<T>> String concatenate(String unit, T unitType) {
        if (unit == null && unitType == null) {
            return null;
        }
        if (unit == null) {
            return unitType.toString();
        }
        if (unitType == null) {
            return unit;
        }
        return unit + " " + unitType;
    }
}
