package bg.mck.orderqueryservice.config.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray()) {
            JsonArray array = json.getAsJsonArray();
            if (array.size() != 7) {
                throw new IllegalStateException("Array must have size 7, but has size " + array.size());
            }

            int year = array.get(0).getAsInt();
            int month = array.get(1).getAsInt();
            int day = array.get(2).getAsInt();
            int hour = array.get(3).getAsInt();
            int minute = array.get(4).getAsInt();
            int second = array.get(5).getAsInt();
            int nanoOfSecond = array.get(6).getAsInt();

            return LocalDateTime.of(year, month, day, hour, minute, second, nanoOfSecond);
        } else {
            throw new JsonParseException("Expected JsonArray but found " + json.getClass().getSimpleName());
        }
    }
}