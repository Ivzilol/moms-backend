package bg.mck.service;

import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.entity.transportEntity.TransportEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TransportRedisService {

    private static final String CACHE_KEY = "inventoryTransportQueryCache";

    private final RedisTemplate<String, TransportEntity> redisTemplate;

    public TransportRedisService(RedisTemplate<String, TransportEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public TransportEntity getCachedObject(String id) {
        return redisTemplate.opsForValue().get(CACHE_KEY + id);
    }

    public void cacheObject(TransportEntity transportEntity) {
        redisTemplate.opsForValue().set(CACHE_KEY + transportEntity.getId(), transportEntity);
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

