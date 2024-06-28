package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.mapper.ConstructionSiteMapper;
import bg.mck.ordercommandservice.repository.ConstructionSiteRepository;
import bg.mck.ordercommandservice.exception.ConstructionSiteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConstructionSiteService {
    private final ConstructionSiteRepository constructionSiteRepository;
    private final ConstructionSiteMapper constructionSiteMapper;

    public ConstructionSiteService(ConstructionSiteRepository constructionSiteRepository, ConstructionSiteMapper constructionSiteMapper) {
        this.constructionSiteRepository = constructionSiteRepository;
        this.constructionSiteMapper = constructionSiteMapper;
    }

    public ConstructionSiteDTO getConstructionSite(Long id) {
        Optional<ConstructionSiteEntity> constructionSiteById = constructionSiteRepository.findById(id);
        if (constructionSiteById.isPresent()) {
            return constructionSiteMapper.toDTO(constructionSiteById.get());
        }
        throw new ConstructionSiteNotFoundException("Construction site with id " + id + " not found");
    }
    public ConstructionSiteEntity getConstructionSiteById(Long id) {
        Optional<ConstructionSiteEntity> constructionSiteById = constructionSiteRepository.findById(id);
        if (constructionSiteById.isPresent()) {
            return constructionSiteById.get();
        }
        throw new ConstructionSiteNotFoundException("Construction site with id " + id + " not found");
    }

    public ConstructionSiteEntity getConstructionSiteByNumberAndName(ConstructionSiteDTO constructionSite) {
        String constructionNumber = constructionSite.getConstructionNumber();
        String name = constructionSite.getName();
        Optional<ConstructionSiteEntity> constructionSiteByNumberAndName = constructionSiteRepository.findByConstructionNumberAndName(constructionNumber, name);
        if (constructionSiteByNumberAndName.isPresent()) {
            return constructionSiteByNumberAndName.get();
        }
        throw new ConstructionSiteNotFoundException("Construction site with number " + constructionNumber + " and name " + name + " not found");
    }
}

