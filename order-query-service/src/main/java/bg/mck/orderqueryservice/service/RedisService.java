package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RedisService {

    private final String CACHE_KEY = "orderQueryService";
    @Value("${spring.data.redis.database}")
    private Integer dbIndex;


    private final RedisTemplate<String, OrderDTO> redisTemplate;

    public RedisService(RedisTemplate<String, OrderDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public OrderDTO getCachedObjectById(Long id) {
        return redisTemplate.opsForValue().get(CACHE_KEY + id);
    }

    public List<OrderDTO> getCachedObjects() {
        return redisTemplate.opsForValue().multiGet(redisTemplate.keys(CACHE_KEY + "*"));
    }

    public void cacheOrder(OrderDTO orderDTO) {
        redisTemplate.opsForValue().set(CACHE_KEY + orderDTO.getId(), orderDTO);
    }

    public void clearCache() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    public void clearCacheInDatabase() {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.select(dbIndex);
            connection.serverCommands().flushDb();
            return null;
        });
    }
}
