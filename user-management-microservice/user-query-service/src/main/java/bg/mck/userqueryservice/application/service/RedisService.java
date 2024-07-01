package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisService {

    private static final String CACHE_KEY = "userQueryCache";

    private final RedisTemplate<String, UserEntity> redisTemplate;

    public RedisService(RedisTemplate<String, UserEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public UserEntity getCachedObject(Long id) {
        return redisTemplate.opsForValue().get(CACHE_KEY + id);
    }

    public void cacheObject(UserEntity userEntity) {
        redisTemplate.opsForValue().set(CACHE_KEY + userEntity.getId(), userEntity);
    }

    public void clearCache() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

}
