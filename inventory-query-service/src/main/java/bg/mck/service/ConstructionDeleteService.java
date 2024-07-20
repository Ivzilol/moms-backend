package bg.mck.service;

import bg.mck.exceptions.InventoryItemNotFoundException;
import bg.mck.repository.constructionSite.ConstructionSiteRepository;
import bg.mck.repository.service.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ConstructionDeleteService {

    private final ConstructionSiteRepository constructionRepository;

    public ConstructionDeleteService(ConstructionSiteRepository constructionRepository) {
        this.constructionRepository = constructionRepository;
    }


    public void deleteConstructionSiteById(String id) {
        boolean exists = constructionRepository.existsById(id);
        if (exists) {
            constructionRepository.deleteById(id);
        } else {
            throw new InventoryItemNotFoundException("Construction site with id " + id + " not found");
        }
    }
}
