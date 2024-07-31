package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.event.ConstructionSiteEvent;
import bg.mck.ordercommandservice.event.EventType;
import bg.mck.ordercommandservice.event.EventData;
import bg.mck.ordercommandservice.exception.ConstructionSiteAlreadyExists;
import bg.mck.ordercommandservice.exception.ConstructionSiteNotFoundException;
import bg.mck.ordercommandservice.mapper.ConstructionSiteMapper;
import bg.mck.ordercommandservice.repository.ConstructionSiteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConstructionSiteService {

    private final ConstructionSiteRepository constructionSiteRepository;
    private final ConstructionSiteMapper constructionSiteMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final OrderQueryServiceClient orderQueryServiceClient;

    public ConstructionSiteService(ConstructionSiteRepository constructionSiteRepository, ConstructionSiteMapper constructionSiteMapper, OrderQueryServiceClient orderQueryServiceClient) {
        this.constructionSiteRepository = constructionSiteRepository;
        this.constructionSiteMapper = constructionSiteMapper;
        this.orderQueryServiceClient = orderQueryServiceClient;
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

    @Transactional
    public ConstructionSiteDTO createConstructionSite(ConstructionSiteDTO constructionSiteDTO) {
        ConstructionSiteEntity constructionSiteEntity = constructionSiteMapper.toEntity(constructionSiteDTO);

        checkIfExistsByNameAndNumber(constructionSiteEntity);

        ConstructionSiteEntity savedConstructionSite = constructionSiteRepository.save(constructionSiteEntity);

        ConstructionSiteDTO newConstrictionSite = constructionSiteMapper.toDTO(savedConstructionSite);
        LOGGER.info("Construction site with id: {} name: {} and number: {} created successfully", newConstrictionSite.getId(), newConstrictionSite.getName(), newConstrictionSite.getConstructionNumber());

        boolean isUpdate = false;
        EventData<ConstructionSiteEvent> constructionSiteEvent = createConstructionSiteEvent(savedConstructionSite, isUpdate);
        sendConstructionSiteEvent(constructionSiteEvent);
        return newConstrictionSite;
    }

    private void checkIfExistsByNameAndNumber(ConstructionSiteEntity constructionSiteEntity) {
        String name = constructionSiteEntity.getName();
        Optional<ConstructionSiteEntity> constructionSiteByName = constructionSiteRepository.findByName(name);
        if (constructionSiteByName.isPresent()) {
            throw new ConstructionSiteAlreadyExists("Construction site with name " + name + " already exists");
        }

        String constructionNumber = constructionSiteEntity.getConstructionNumber();
        Optional<ConstructionSiteEntity> constructionSiteByNumber = constructionSiteRepository.findByConstructionNumber(constructionNumber);
        if (constructionSiteByNumber.isPresent()) {
            throw new ConstructionSiteAlreadyExists("Construction site with number " + constructionNumber + " already exists");
        }
    }


    public ConstructionSiteEntity getConstructionSiteByName(String name) {
        return constructionSiteRepository.findByName(name)
                .orElseThrow(() -> new ConstructionSiteNotFoundException("Construction site with name " + name + " not found"));
    }

    private void sendConstructionSiteEvent(EventData<ConstructionSiteEvent> constructionSiteEvent) {
        orderQueryServiceClient.sendConstructionSiteEvent(constructionSiteEvent, constructionSiteEvent.getEventType().toString());
    }

    @Transactional
    public ConstructionSiteDTO updateConstructionSite(ConstructionSiteDTO constructionSiteDTO) {
        ConstructionSiteEntity constructionSiteEntity = constructionSiteMapper.toEntity(constructionSiteDTO);
        constructionSiteEntity.setId(constructionSiteDTO.getId());

        checkIfExistsById(constructionSiteEntity);
        checkIfExistsByNameAndNumber(constructionSiteEntity);

        ConstructionSiteEntity updatedConstructionSite = constructionSiteRepository.save(constructionSiteEntity);
        ConstructionSiteDTO updatedConstructionSiteDTO = constructionSiteMapper.toDTO(updatedConstructionSite);
        LOGGER.info("Construction site with id: {} updated successfully", updatedConstructionSiteDTO.getId());

        boolean isUpdate = true;
        EventData<ConstructionSiteEvent> constructionSiteEvent = createConstructionSiteEvent(updatedConstructionSite, isUpdate);
        sendConstructionSiteEvent(constructionSiteEvent);
        return updatedConstructionSiteDTO;
    }

    private void checkIfExistsById(ConstructionSiteEntity constructionSiteEntity) {
        if (!constructionSiteRepository.existsById(constructionSiteEntity.getId())) {
            throw new ConstructionSiteNotFoundException("Construction site with id " + constructionSiteEntity.getId() + " not found");
        }
    }

    private EventData<ConstructionSiteEvent> createConstructionSiteEvent(ConstructionSiteEntity savedConstructionSite, boolean isUpdate) {
        ConstructionSiteEvent constructionSiteEvent = constructionSiteMapper.toEvent(savedConstructionSite);
        constructionSiteEvent.setEventTime(LocalDateTime.now());
        constructionSiteEvent.setEventType(EventType.CONSTRUCTION_SITE_CREATED);

        EventData<ConstructionSiteEvent> eventData = new EventData<>();
        eventData.setEventType(EventType.CONSTRUCTION_SITE_CREATED);
        eventData.setEvent(constructionSiteEvent);

        if (isUpdate) {
            constructionSiteEvent.setEventType(EventType.CONSTRUCTION_SITE_UPDATED);
            eventData.setEventType(EventType.CONSTRUCTION_SITE_UPDATED);
        }

        return eventData;
    }
}

