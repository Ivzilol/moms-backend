package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.TransportDTO;
import bg.mck.ordercommandservice.entity.TransportEntity;
import bg.mck.ordercommandservice.event.TransportEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransportMapperTest {
    private TransportMapper transportMapper;
    private TransportDTO transportDTO;
    private TransportEntity transportEntity;

    @BeforeEach
    public void setUp() {
        transportDTO = MaterialUtil.createTransportDTO();
        transportEntity = MaterialUtil.createTransportEntity();
        transportMapper = new TransportMapperImpl();
    }

    @AfterEach
    public void tearDown() {
        transportDTO = null;
        transportEntity = null;
    }

    @Test
    public void shouldMap_TransportDTO_To_TransportEntity() {
        TransportEntity result = transportMapper.toTransportEntity(transportDTO);

        assertNotNull(result);
        assertEquals(transportDTO.getId(), result.getId());
        assertEquals(transportDTO.getDescription(), result.getDescription());
        assertEquals(transportDTO.getQuantity(), result.getQuantity());
        assertEquals(transportDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(transportDTO.getMaterialStatus(), result.getMaterialStatus().name());
        assertEquals(transportDTO.getAdminNote(), result.getAdminNote());
        assertEquals(transportDTO.getMaxLength(), result.getMaxLength().split(" ")[0]);
        assertEquals(transportDTO.getMaxLengthUnit().name(), result.getMaxLength().split(" ")[1]);
        assertEquals(transportDTO.getWeight(), result.getWeight().split(" ")[0]);
        assertEquals(transportDTO.getWeightUnit().name(), result.getWeight().split(" ")[1]);
    }

    @Test
    public void shouldMap_TransportEntity_To_TransportDTO() {
        TransportDTO result = transportMapper.toTransportDTO(transportEntity);

        assertNotNull(result);
        assertEquals(transportEntity.getId(), result.getId());
        assertEquals(transportEntity.getDescription(), result.getDescription());
        assertEquals(transportEntity.getQuantity(), result.getQuantity());
        assertEquals(transportEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(transportEntity.getMaterialStatus().name(), result.getMaterialStatus());
        assertEquals(transportEntity.getAdminNote(), result.getAdminNote());
        assertEquals(transportEntity.getMaxLength().split(" ")[0], result.getMaxLength());
        assertEquals(transportEntity.getMaxLength().split(" ")[1], result.getMaxLengthUnit().name());
        assertEquals(transportEntity.getWeight().split(" ")[0], result.getWeight());
        assertEquals(transportEntity.getWeight().split(" ")[1], result.getWeightUnit().name());
    }

    @Test
    public void shouldMap_TransportEntity_To_TransportEvent() {
        TransportEvent result = transportMapper.toEvent(transportEntity);

        assertNotNull(result);
        assertEquals(transportEntity.getId(), result.getId());
        assertEquals(transportEntity.getDescription(), result.getDescription());
        assertEquals(transportEntity.getQuantity(), result.getQuantity());
        assertEquals(transportEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(transportEntity.getMaterialStatus(), result.getMaterialStatus());
        assertEquals(transportEntity.getAdminNote(), result.getAdminNote());
        assertEquals(transportEntity.getMaxLength(), result.getMaxLength());
        assertEquals(transportEntity.getWeight(), result.getWeight());
    }

}
