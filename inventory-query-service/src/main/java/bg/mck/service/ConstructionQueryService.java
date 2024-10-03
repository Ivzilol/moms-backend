package bg.mck.service;

import bg.mck.dto.ConstructionSiteDTO;
import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.mapper.ConstructionSiteMapper;
import bg.mck.repository.constructionSite.ConstructionSiteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConstructionQueryService {

   private final ConstructionSiteRepository constructionRepository;
   private final ConstructionEventService constructionEventService;
   private final ConstructionSiteMapper constructionMapper;

    public ConstructionQueryService(ConstructionSiteRepository constructionRepository, ConstructionEventService constructionEventService, ConstructionSiteMapper constructionMapper) {
        this.constructionRepository = constructionRepository;
        this.constructionEventService = constructionEventService;
        this.constructionMapper = constructionMapper;
    }


    public ConstructionSiteDTO getConstructionById(String id) {
        ConstructionSiteEntity entity = findById(id);
        return constructionMapper.toConstructionDTO(entity);
    }

    public List<ConstructionSiteDTO> getAllConstructionSites() {
        List<ConstructionSiteDTO> dtos = new ArrayList<>();

        List<ConstructionSiteEntity> entities = constructionRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        for (ConstructionSiteEntity entity : entities) {
            dtos.add(getConstructionById(entity.getId()));
        }

        return dtos;
    }

    @Cacheable(value = "constructions", key = "#constructionName")
    public List<ConstructionSiteDTO> findConstructionByName(String constructionName) {
        String regex = "^" + constructionName;
        Sort sort = Sort.by(Sort.Direction.ASC, "constructionName");

        List<ConstructionSiteEntity> entities = constructionRepository.findByPartOfName(regex, sort);

        return constructionMapper.toConstructionDTOs(entities);
    }



    private ConstructionSiteEntity findById(String id) {
        Optional<ConstructionSiteEntity> cachedObject = constructionRepository.findById(id);

        return cachedObject.orElseGet(() -> constructionEventService.reconstructConstructionEntity(id));

    }


}
