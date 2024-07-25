package bg.mck.ordercommandservice.testUtils;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;

public class ConstructionSiteUtil {
    public static ConstructionSiteDTO createConstructionSiteDTO() {
        ConstructionSiteDTO constructionSiteDTO = new ConstructionSiteDTO();
        constructionSiteDTO.setConstructionNumber("1234");
        constructionSiteDTO.setName("Site Name");
        return constructionSiteDTO;
    }

    public static ConstructionSiteEntity createConstructionSiteEntity() {
        ConstructionSiteEntity constructionSiteEntity = new ConstructionSiteEntity();
        constructionSiteEntity.setConstructionNumber("1234");
        constructionSiteEntity.setName("Site Name");
        return constructionSiteEntity;
    }

    public static ConstructionSiteEntity createConstructionSiteEntityWithID() {
        ConstructionSiteEntity constructionSiteEntity = new ConstructionSiteEntity();
        constructionSiteEntity.setId(1L);
        constructionSiteEntity.setConstructionNumber("1234");
        constructionSiteEntity.setName("Site Name");
        return constructionSiteEntity;
    }
}
