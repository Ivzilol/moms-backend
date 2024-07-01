package bg.mck.userqueryservice.application.service;

import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import bg.mck.userqueryservice.application.exceptions.UserNotFoundException;
import bg.mck.userqueryservice.application.mapper.UserMapper;
import bg.mck.userqueryservice.application.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @Mock
    private RedisService redisService;

    @Mock
    private EventService eventService;

    @InjectMocks
    private UserQueryService userQueryService;

    private UserEntity userEntity;

    private Long userId = 1L;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity()
                .setId(userId.toString());

    }

    @Test
    void testGetUserDetailsById() {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO()
                .setId(userId.toString());

        when(userRepository.findById(userId.toString())).thenReturn(Optional.of(userEntity));
        when(redisService.getCachedObject(userId)).thenReturn(null);
        when(eventService.reconstructUserEntity(userId)).thenReturn(userEntity);
        when(mapper.toDTO(userEntity)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userQueryService.getUserDetailsById(userId);

        assertNotNull(result);
        assertEquals(userDetailsDTO, result);
        verify(userRepository).findById(userId.toString());
        verify(redisService).getCachedObject(userId);
        verify(eventService).reconstructUserEntity(userId);
        verify(mapper).toDTO(userEntity);
    }

    @Test
    void testGetUserCredentialsById() {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO()
                .setEmail(userEntity.getEmail())
                .setHashedPassword(userEntity.getPassword());

        when(userRepository.findById(userId.toString())).thenReturn(Optional.of(userEntity));
        when(redisService.getCachedObject(userId)).thenReturn(null);
        when(eventService.reconstructUserEntity(userId)).thenReturn(userEntity);
        when(mapper.toUserCredentialsDTO(userEntity)).thenReturn(userCredentialsDTO);

        UserCredentialsDTO result = userQueryService.getUserCredentialsById(userId);

        assertNotNull(result);
        assertEquals(userCredentialsDTO, result);
        verify(userRepository).findById(userId.toString());
        verify(redisService).getCachedObject(userId);
        verify(eventService).reconstructUserEntity(userId);
        verify(mapper).toUserCredentialsDTO(userEntity);
    }

    @Test
    void testGetUserDetailsById_UserNotFound() {
        when(userRepository.findById(userId.toString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userQueryService.getUserDetailsById(userId);
        });

    }

    @Test
    void testGetUserDetailsById_CachedUser() {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO()
                .setId(userId.toString());

        when(userRepository.findById(userId.toString())).thenReturn(Optional.of(userEntity));
        when(redisService.getCachedObject(userId)).thenReturn(userEntity);
        when(mapper.toDTO(userEntity)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userQueryService.getUserDetailsById(userId);

        assertNotNull(result);
        verify(userRepository).findById(userId.toString());
        verify(redisService).getCachedObject(userId);
        verify(mapper).toDTO(userEntity);
        verifyNoInteractions(eventService);
    }
}