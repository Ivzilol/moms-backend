package bg.mck.config;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.entity.materialEntity.BaseMaterialEntity;
import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.entity.transportEntity.TransportEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, BaseMaterialEntity> materialRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, BaseMaterialEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, ConstructionSiteEntity> constructionRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ConstructionSiteEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, ServiceEntity> serviceRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ServiceEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, TransportEntity> transportRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, TransportEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }


}
