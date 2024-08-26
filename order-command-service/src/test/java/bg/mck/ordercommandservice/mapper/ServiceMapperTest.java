package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.ServiceDTO;
import bg.mck.ordercommandservice.entity.ServiceEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.event.ServiceEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceMapperTest {
    private ServiceMapper serviceMapper;
    private ServiceDTO serviceDTO;
    private ServiceEntity serviceEntity;

    @BeforeEach
    public void setUp() {
        serviceDTO = MaterialUtil.createServiceDTO();
        serviceEntity = MaterialUtil.createServiceEntity();
        serviceMapper = new ServiceMapperImpl();
    }

    @AfterEach
    public void tearDown() {
        serviceDTO = null;
        serviceEntity = null;
    }

    @Test
    public void shouldMap_ServiceDTO_To_ServiceEntity() {
        ServiceEntity result = serviceMapper.toServiceEntity(serviceDTO);

        assertNotNull(result);
        assertEquals(serviceDTO.getId(), result.getId());
        assertEquals(serviceDTO.getDescription(), result.getDescription());
        assertEquals(serviceDTO.getQuantity(), result.getQuantity());
        assertEquals(serviceDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(serviceDTO.getMaterialStatus(), result.getMaterialStatus().name());
        assertEquals(serviceDTO.getAdminNote(), result.getAdminNote());
    }

    @Test
    public void shouldMap_ServiceEntity_To_ServiceDTO() {
        ServiceDTO result = serviceMapper.toServiceDTO(serviceEntity);

        assertNotNull(result);
        assertEquals(serviceEntity.getId(), result.getId());
        assertEquals(serviceEntity.getDescription(), result.getDescription());
        assertEquals(serviceEntity.getQuantity(), result.getQuantity());
        assertEquals(serviceEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(serviceEntity.getMaterialStatus().name(), result.getMaterialStatus());
        assertEquals(serviceEntity.getAdminNote(), result.getAdminNote());
    }

    @Test
    public void shouldMap_ServiceEntity_To_ServiceEvent() {
        ServiceEvent result = serviceMapper.toEvent(serviceEntity);

        assertNotNull(result);
        assertEquals(serviceEntity.getId(), result.getId());
        assertEquals(serviceEntity.getDescription(), result.getDescription());
        assertEquals(serviceEntity.getQuantity(), result.getQuantity());
        assertEquals(serviceEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
        assertEquals(serviceEntity.getMaterialStatus(), result.getMaterialStatus());
        assertEquals(serviceEntity.getAdminNote(), result.getAdminNote());
    }
}
