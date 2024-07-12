package bg.mck.service;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.entity.serviceEntity.ServiceEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ServiceRedisService {

    private static final String CACHE_KEY = "inventoryServiceQueryCache";

    private final RedisTemplate<String, ServiceEntity> redisTemplate;

    public ServiceRedisService(RedisTemplate<String, ServiceEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public ServiceEntity getCachedObject(String id) {
        return redisTemplate.opsForValue().get(CACHE_KEY + id);
    }

    public void cacheObject(ServiceEntity serviceEntity) {
        redisTemplate.opsForValue().set(CACHE_KEY + serviceEntity.getId(), serviceEntity);
    }

    public void clearCache() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    public void clearCacheForObject(String id) {
        redisTemplate.delete(CACHE_KEY + id);
    }

}

