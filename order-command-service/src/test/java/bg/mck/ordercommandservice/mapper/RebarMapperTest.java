package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.RebarDTO;
import bg.mck.ordercommandservice.entity.RebarEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.RebarEvent;
import bg.mck.ordercommandservice.testUtils.MaterialUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RebarMapperTest {

        private RebarMapper rebarMapper;
        private RebarDTO rebarDTO;
        private RebarEntity rebarEntity;

        @BeforeEach
        public void setUp(){
            rebarDTO = MaterialUtil.createRebarDTO();
            rebarEntity = MaterialUtil.createRebarEntity();
            rebarMapper = new RebarMapperImpl();
        }
        @AfterEach
        public void tearDown(){
            rebarDTO = null;
            rebarEntity = null;
        }

        @Test
        public void shouldMap_RebarDTO_To_RebarEntity(){
            RebarEntity result = rebarMapper.toEntity(rebarDTO);

            assertNotNull(result);
            assertEquals(rebarDTO.getId(), result.getId());
            assertEquals(rebarDTO.getDescription(), result.getDescription());
            assertEquals(rebarDTO.getQuantity(), result.getQuantity().split(" ")[0]);
            assertEquals(rebarDTO.getQuantityUnit(), WeightUnits.valueOf(result.getQuantity().split(" ")[1]));
            assertEquals(rebarDTO.getSpecificationFileUrl(), result.getSpecificationFileUrl());
            assertEquals(rebarDTO.getMaxLength(), result.getMaxLength().split(" ")[0]);
            assertEquals(rebarDTO.getMaxLengthUnit(), LengthUnits.valueOf(result.getMaxLength().split(" ")[1]));
            assertEquals(rebarDTO.getAdminNote(), result.getAdminNote());
            assertEquals(rebarDTO.getMaterialStatus(), result.getMaterialStatus().name());
        }

        @Test
        public void shouldMap_RebarEntity_To_RebarDTO(){
            RebarDTO result = rebarMapper.toDTO(rebarEntity);

            assertNotNull(result);
            assertEquals(rebarEntity.getId(), result.getId());
            assertEquals(rebarEntity.getDescription(), result.getDescription());
            assertEquals(rebarEntity.getQuantity().split(" ")[0], result.getQuantity());
            assertEquals(WeightUnits.valueOf(rebarEntity.getQuantity().split(" ")[1]), result.getQuantityUnit());
            assertEquals(rebarEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
            assertEquals(rebarEntity.getMaxLength().split(" ")[0], result.getMaxLength());
            assertEquals(LengthUnits.valueOf(rebarEntity.getMaxLength().split(" ")[1]), result.getMaxLengthUnit());
            assertEquals(rebarEntity.getAdminNote(), result.getAdminNote());
            assertEquals(rebarEntity.getMaterialStatus().name(), result.getMaterialStatus());
        }

        @Test
        public void shouldMap_RebarEntity_To_RebarEvent(){
            RebarEvent result = rebarMapper.toEvent(rebarEntity);

            assertNotNull(result);
            assertEquals(rebarEntity.getId(), result.getId());
            assertEquals(rebarEntity.getDescription(), result.getDescription());
            assertEquals(rebarEntity.getQuantity(), result.getQuantity());
            assertEquals(rebarEntity.getQuantity(), result.getQuantity());
            assertEquals(rebarEntity.getSpecificationFileUrl(), result.getSpecificationFileUrl());
            assertEquals(rebarEntity.getMaxLength(), result.getMaxLength());
            assertEquals(rebarEntity.getMaxLength(), result.getMaxLength());
            assertEquals(rebarEntity.getAdminNote(), result.getAdminNote());
            assertEquals(rebarEntity.getMaterialStatus(), result.getMaterialStatus());
        }

}
