package bg.mck.service;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.events.construction.ConstructionRegisteredEvent;
import bg.mck.repository.constructionSite.ConstructionSiteRepository;
import org.springframework.stereotype.Service;

@Service
public class ConstructionRegisterService {

    private final ConstructionSiteRepository constructionRepository;

    public ConstructionRegisterService(ConstructionSiteRepository constructionRepository) {
        this.constructionRepository = constructionRepository;
    }


    public void processingRegisterConstruction(ConstructionRegisteredEvent event) {
        ConstructionSiteEntity entity = new ConstructionSiteEntity();
        entity.setId(event.getConstructionId());
        entity.setName(event.getName());
        entity.setConstructionNumber(event.getConstructionNumber());

        constructionRepository.save(entity);
    }
}
