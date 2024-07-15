package bg.mck.orderqueryservice.config;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeDeserializer implements JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonElement json, Type typeOfT, com.google.gson.JsonDeserializationContext context) throws JsonParseException {
        return ZonedDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
