package bg.mck.ordercommandservice.client;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.event.EventData;
import bg.mck.ordercommandservice.event.EventType;
import bg.mck.ordercommandservice.service.EventMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class EventMapperServiceWithClientTest {

    @Mock
    private OrderQueryServiceClient orderQueryServiceClient;

    @InjectMocks
    private EventMapperService eventMapperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenEventData_whenSendEvent_thenClientCalledWithCorrectParameters() {
        EventData eventData = new EventData();
        EventType eventType = EventType.ORDER_CREATED;

        // When
        orderQueryServiceClient.sendEvent(eventData, eventType.toString());

        // Then
        ArgumentCaptor<EventData> eventCaptor = ArgumentCaptor.forClass(EventData.class);
        ArgumentCaptor<String> headerCaptor = ArgumentCaptor.forClass(String.class);
        verify(orderQueryServiceClient).sendEvent(eventCaptor.capture(), headerCaptor.capture());

        assertEquals(eventData, eventCaptor.getValue());
        assertEquals(eventType.toString(), headerCaptor.getValue());
    }
}

