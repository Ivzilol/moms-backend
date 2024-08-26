package bg.mck.orderqueryservice.service;

import bg.mck.orderqueryservice.dto.ConstructionSiteDTO;
import bg.mck.orderqueryservice.entity.ConstructionSiteEntity;
import bg.mck.orderqueryservice.exception.ConstructionSiteNotFoundException;
import bg.mck.orderqueryservice.mapper.ConstructionSiteMapper;
import bg.mck.orderqueryservice.repository.ConstructionSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConstructionSiteService {

    private final ConstructionSiteRepository constructionSiteRepository;
    private final ConstructionSiteMapper constructionSiteMapper;

    @Autowired
    public ConstructionSiteService(ConstructionSiteRepository constructionSiteRepository, ConstructionSiteMapper constructionSiteMapper) {
        this.constructionSiteRepository = constructionSiteRepository;
        this.constructionSiteMapper = constructionSiteMapper;
    }

    public void saveConstructionSite(ConstructionSiteEntity constructionSiteEntity) {
        constructionSiteRepository.save(constructionSiteEntity);
    }

    public ConstructionSiteDTO getConstructionSiteByName(String constructionSiteName) {
        return constructionSiteRepository.findByName(constructionSiteName).map(constructionSiteMapper::toDto).orElseThrow(() ->
                new ConstructionSiteNotFoundException("Construction site not found"));
    }

    public ConstructionSiteDTO getConstructionSiteById(String constructionSiteId) {
        return constructionSiteRepository.findById(constructionSiteId).map(constructionSiteMapper::toDto).orElseThrow(() ->
                new ConstructionSiteNotFoundException("Construction site not found"));
    }

    public List<ConstructionSiteDTO> getAllConstructionSites() {
        return constructionSiteRepository.findAll().stream().map(constructionSiteMapper::toDto).collect(Collectors.toList());
    }

    public void updateConstructionSite(ConstructionSiteEntity constructionSiteEntity) {
        this.constructionSiteRepository.save(constructionSiteEntity);
    }
}
