package bg.mck.orderqueryservice.config.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class MongoConfig {

    private static class ZonedDateTimeCodec implements org.bson.codecs.Codec<ZonedDateTime> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        @Override
        public void encode(org.bson.BsonWriter writer, ZonedDateTime value, org.bson.codecs.EncoderContext encoderContext) {
            writer.writeString(value.format(FORMATTER));
        }

        @Override
        public ZonedDateTime decode(org.bson.BsonReader reader, org.bson.codecs.DecoderContext decoderContext) {
            return ZonedDateTime.parse(reader.readString(), FORMATTER);
        }

        @Override
        public Class<ZonedDateTime> getEncoderClass() {
            return ZonedDateTime.class;
        }
    }

    @Bean
    public MongoClient mongoClient() {
        CodecProvider pojoCodecProvider = org.bson.codecs.pojo.PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(pojoCodecProvider);
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(new ZonedDateTimeCodec()),
                pojoCodecRegistry
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .applyConnectionString(new com.mongodb.ConnectionString("mongodb://your_username:your_password@localhost:27020/order-query-db?authSource=admin"))
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new ZonedDateTimeToStringConverter(),
                new StringToZonedDateTimeConverter()
        ));
    }
}
