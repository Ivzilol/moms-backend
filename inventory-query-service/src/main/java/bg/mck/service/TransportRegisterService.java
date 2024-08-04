package bg.mck.service;

import bg.mck.entity.transportEntity.TransportEntity;
import bg.mck.events.transport.TransportRegisteredEvent;
import bg.mck.repository.transport.TransportRepository;

public class TransportRegisterService {

    private final TransportRepository transportRepository;


    public TransportRegisterService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public void processingRegisterTransport(TransportRegisteredEvent event) {
        TransportEntity entity = new TransportEntity();
        entity.setId(event.getTransportId());
        entity.setName(event.getName());
        entity.setMaxLength(event.getMaxLength());
        entity.setWeight(event.getWeight());
        entity.setTruck(event.getTruck());
        entity.setQuantity(event.getQuantity());
        entity.setDescription(event.getDescription());
        entity.setSpecificationFileUrl(event.getSpecificationFileUrl());

        transportRepository.save(entity);
    }
}
