package bg.mck.ordercommandservice.mapper.util;

public interface Splitter {
    default String[] split(String unit) {
        if (unit == null) {
            return null;
        }
        return unit.split(" ");
    }
}
