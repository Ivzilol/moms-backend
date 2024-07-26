package bg.mck.orderqueryservice.config.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ZonedDateTimeDeserializer implements JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateTimeString = json.getAsString();

        try {
            // Attempt to parse as an epoch second
            long epochSecond = Long.parseLong(dateTimeString.split("\\.")[0]);
            int nanoAdjustment = Integer.parseInt(dateTimeString.split("\\.")[1]);
            return ZonedDateTime.ofInstant(Instant.ofEpochSecond(epochSecond, nanoAdjustment), ZoneOffset.UTC);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // If it fails, fall back to parsing as ISO date-time string
            try {
                return ZonedDateTime.parse(dateTimeString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            } catch (DateTimeParseException ex) {
                throw new JsonParseException("Unable to parse ZonedDateTime", ex);
            }
        }
    }
}
