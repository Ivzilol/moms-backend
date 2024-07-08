package bg.mck.service;

import bg.mck.entity.materialEntity.BaseMaterialEntity;
import bg.mck.userqueryservice.application.entity.UserEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisService {

    private static final String CACHE_KEY = "inventoryQueryCache";

    private final RedisTemplate<String, BaseMaterialEntity> redisTemplate;

    public RedisService(RedisTemplate<String, BaseMaterialEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public BaseMaterialEntity getCachedObject(Long id) {
        return redisTemplate.opsForValue().get(CACHE_KEY + id);
    }

    public void cacheObject(BaseMaterialEntity materialEntity) {
        redisTemplate.opsForValue().set(CACHE_KEY + materialEntity.getId(), materialEntity);
    }

    public void clearCache() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

}
