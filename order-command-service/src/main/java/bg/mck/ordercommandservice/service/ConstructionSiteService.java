package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.client.OrderQueryServiceClient;
import bg.mck.ordercommandservice.dto.ConstructionSiteDTO;
import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import bg.mck.ordercommandservice.entity.OrderEntity;
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
import java.util.List;
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

    @Transactional
    public ConstructionSiteDTO updateConstructionSite(ConstructionSiteDTO constructionSiteDTO) {

        ConstructionSiteEntity existingSite = constructionSiteRepository.findById(constructionSiteDTO.getId())
                .orElseThrow(() ->
                        new ConstructionSiteNotFoundException("Construction site with id " + constructionSiteDTO.getId() + " not found"));

        if (hasOrders(existingSite.getId()) > 0) {
            throw new ConstructionSiteAlreadyExists("Construction site with id " + constructionSiteDTO.getId() +
                    " has orders and cannot be updated");
        }
        String newName = constructionSiteDTO.getName();
        String newNumber = constructionSiteDTO.getConstructionNumber();

        if (newName != null && !newName.equals(existingSite.getName())
                && constructionSiteRepository.existsByName(newName)) {
            throw new IllegalArgumentException("Construction site with the name '" + newName + "' already exists.");
        }

        if (newNumber != null && !newNumber.equals(existingSite.getConstructionNumber())
                && constructionSiteRepository.existsByConstructionNumber(newNumber)) {
            throw new IllegalArgumentException("Construction site with the number '" + newNumber + "' already exists.");
        }

        if (newName != null && newNumber != null && !newName.equals(existingSite.getName())
                & !newNumber.equals(existingSite.getConstructionNumber())
                && constructionSiteRepository.existsByNameAndConstructionNumber(newName, newNumber)) {
            throw new IllegalArgumentException("Construction site with the name '" + newName + "' and number '" + newNumber + "' already exists.");
        }

        if (newName != null && !newName.equals(existingSite.getName())) {
            existingSite.setName(newName);
        }

        if (newNumber != null && !newNumber.equals(existingSite.getConstructionNumber())) {
            existingSite.setConstructionNumber(newNumber);
        }

        ConstructionSiteEntity updatedConstructionSite = constructionSiteRepository.save(existingSite);
        ConstructionSiteDTO updatedConstructionSiteDTO = constructionSiteMapper.toDTO(updatedConstructionSite);
        LOGGER.info("Construction site with id: {} updated successfully", updatedConstructionSiteDTO.getId());

        boolean isUpdate = true;
        EventData<ConstructionSiteEvent> constructionSiteEvent = createConstructionSiteEvent(updatedConstructionSite, isUpdate);
        sendConstructionSiteEvent(constructionSiteEvent);
        return updatedConstructionSiteDTO;
    }

    private int hasOrders(Long constructionSiteId) {
        return constructionSiteRepository.existsByIdAndOrdersIsNotNull(constructionSiteId);
    }

    private void checkIfExistsByNameAndNumber(ConstructionSiteDTO constructionSiteDTO) {
        String name = constructionSiteDTO.getName();
        String constructionNumber = constructionSiteDTO.getConstructionNumber();
        constructionSiteRepository.findByConstructionNumberAndName(constructionNumber, name)
                .ifPresent(constructionSite -> {
                    throw new ConstructionSiteAlreadyExists("Construction site with number " + constructionSiteDTO.getConstructionNumber() + " and name " + constructionSiteDTO.getName() + " already exists");
                });
        checkIfExistsByName(name);
        checkIfExistsByNumber(constructionNumber);
    }

    private void checkIfExistsByNumber(String constructionNumber) {
        if (constructionSiteRepository.findByConstructionNumber(constructionNumber).isPresent()) {
            throw new ConstructionSiteAlreadyExists("Construction site with number " + constructionNumber + " already exists");
        }
    }

    private void checkIfExistsByName(String name) {
        if (constructionSiteRepository.findByName(name).isPresent()) {
            throw new ConstructionSiteAlreadyExists("Construction site with name " + name + " already exists");
        }
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

    public ConstructionSiteEntity getConstructionSiteByName(String name) {
        return constructionSiteRepository.findByName(name)
                .orElseThrow(() -> new ConstructionSiteNotFoundException("Construction site with name " + name + " not found"));
    }

    private void sendConstructionSiteEvent(EventData<ConstructionSiteEvent> constructionSiteEvent) {
        orderQueryServiceClient.sendConstructionSiteEvent(constructionSiteEvent, constructionSiteEvent.getEventType().toString());
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
        Optional<ConstructionSiteEntity> constructionSiteByName = constructionSiteRepository.findByName(name);
        if (constructionSiteByName.isPresent()) {
            return constructionSiteByName.get();
        }
        throw new ConstructionSiteNotFoundException("Construction site with number " + constructionNumber + " and name " + name + " not found");
    }
}

