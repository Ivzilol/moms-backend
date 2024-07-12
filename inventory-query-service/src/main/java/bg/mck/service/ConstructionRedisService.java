package bg.mck.service;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.entity.materialEntity.BaseMaterialEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ConstructionRedisService {

    private static final String CACHE_KEY = "inventoryConstructionQueryCache";

    private final RedisTemplate<String, ConstructionSiteEntity> redisTemplate;

    public ConstructionRedisService(RedisTemplate<String, ConstructionSiteEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public ConstructionSiteEntity getCachedObject(String id) {
        return redisTemplate.opsForValue().get(CACHE_KEY + id);
    }

    public void cacheObject(ConstructionSiteEntity constructionEntity) {
        redisTemplate.opsForValue().set(CACHE_KEY + constructionEntity.getId(), constructionEntity);
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

