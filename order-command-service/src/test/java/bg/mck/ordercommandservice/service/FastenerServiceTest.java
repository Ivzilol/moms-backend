package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.mapper.FastenerMapper;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FastenerServiceTest {

    @InjectMocks
    private FastenerService fastenerService;
    @Mock
    private FastenerRepository fastenerRepository;
    @Mock
    private FastenerMapper fastenerMapper;




}
