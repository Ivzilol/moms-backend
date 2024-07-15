package bg.mck.ordercommandservice.testUtils;

import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;

public class MaterialUtil {
    private FastenerDTO fastenerDTO;
    private FastenerEntity fastenerEntity;

    private static Long idGenerator = 0L;

    public static FastenerDTO createFastenerDTO() {
        FastenerDTO fastenerDTO = new FastenerDTO();
        fastenerDTO.setDiameter("M12")
                .setId(++idGenerator)
                .setDescription("Fastener description")
                .setQuantity(10.0)
                .setSpecificationFileUrl("https://fastener.com");
        fastenerDTO.setClazz("10.9")
                .setDiameter("M12")
                .setLength(55.0)
                .setModel("DIN 976")
                .setType("Болт М12х55");
        return fastenerDTO;
    }

    public static FastenerEntity createFastenerEntity() {
        FastenerEntity fastenerEntity = new FastenerEntity();
        fastenerEntity.setDiameter("M12")
                .setDescription("Fastener description")
                .setQuantity(10.0)
                .setSpecificationFileUrl("https://fastener.com")
                .setId(++idGenerator);
        fastenerEntity.setType("Болт М12х55")
                .setDiameter("M12")
                .setLength(55.0)
                .setModel("DIN 976")
                .setClazz("10.9");
        return fastenerEntity;
    }
}
