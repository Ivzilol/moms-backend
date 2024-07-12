package bg.mck.service;

import bg.mck.entity.materialEntity.BaseMaterialEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MaterialRedisService {

    private static final String CACHE_KEY = "inventoryMaterialQueryCache";

    private final RedisTemplate<String, BaseMaterialEntity> redisTemplate;

    public MaterialRedisService(RedisTemplate<String, BaseMaterialEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseMaterialEntity> T getCachedObject(String id, String materialType, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(CACHE_KEY + materialType + id);
    }

    public void cacheObject(BaseMaterialEntity materialEntity, String materialType) {
        redisTemplate.opsForValue().set(CACHE_KEY + materialType + materialEntity.getId(), materialEntity);
    }

    public void clearCache() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    public void clearCacheForObject(String id, String materialType) {
        redisTemplate.delete(CACHE_KEY + materialType + id);
    }

}

