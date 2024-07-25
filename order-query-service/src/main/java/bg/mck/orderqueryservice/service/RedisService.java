package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.OrderDTO;
import bg.mck.orderqueryservice.entity.OrderEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RedisService {

    private final String CACHE_KEY = "orderQueryService";


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

    public void cacheObject(OrderDTO orderDTO) {
        redisTemplate.opsForValue().set(CACHE_KEY + orderDTO.getId(), orderDTO);
    }


    public void clearCache() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
