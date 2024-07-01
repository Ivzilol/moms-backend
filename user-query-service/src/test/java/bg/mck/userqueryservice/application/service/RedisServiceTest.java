package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RedisServiceTest {

    @Mock
    private RedisTemplate<String, UserEntity> redisTemplate;

    @Mock
    private ValueOperations<String, UserEntity> valueOperations;

    @InjectMocks
    private RedisService redisService;

    private UserEntity userEntity;
    private static final String CACHE_KEY = "userQueryCache";
    private Long userId = 1L;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(userId.toString());


    }

    @Test
    void testGetCachedObject() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(CACHE_KEY + userId)).thenReturn(userEntity);

        UserEntity result = redisService.getCachedObject(userId);

        assertNotNull(result);
        assertEquals(userEntity, result);
        verify(valueOperations).get(CACHE_KEY + userId);
    }

    @Test
    void testCacheObject() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        doNothing().when(valueOperations).set(CACHE_KEY + userEntity.getId(), userEntity);

        redisService.cacheObject(userEntity);

        verify(valueOperations).set(CACHE_KEY + userEntity.getId(), userEntity);
    }

    @Test
    void testClearCache() {
        Set<String> keys = new HashSet<>();
        keys.add(CACHE_KEY + userId);

        when(redisTemplate.keys(CACHE_KEY + "*")).thenReturn(keys);

        redisService.clearCache();

        verify(redisTemplate).delete(keys);
    }

    @Test
    void testClearCache_NoKeys() {
        when(redisTemplate.keys(CACHE_KEY + "*")).thenReturn(null);

        redisService.clearCache();

        verify(redisTemplate, never()).delete(anySet());
    }
}