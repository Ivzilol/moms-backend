package bg.mck.orderqueryservice.mapper;

import bg.mck.orderqueryservice.dto.EmailDTO;
import bg.mck.orderqueryservice.events.CreateOrderEvent;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {


    public EmailDTO toEmailDTO(CreateOrderEvent orderEvent) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(orderEvent.getEmail());
        emailDTO.setOrderNumber(String.valueOf(orderEvent.getOrderNumber()));
        emailDTO.setOrderDescription(orderEvent.getOrderDescription());
        emailDTO.setOrderDate(orderEvent.getOrderDate());
        emailDTO.setDeliveryDate(orderEvent.getDeliveryDate());
        emailDTO.setMaterialType(orderEvent.getMaterialType().name());
        emailDTO.setOrderStatus(orderEvent.getOrderStatus().name());
        emailDTO.setConstructionSiteName(orderEvent.getConstructionSite().getName());
        emailDTO.setSpecificationFileUrl(orderEvent.getSpecificationFileUrl());
        emailDTO.setConstructionSiteNumber(orderEvent.getConstructionSite().getConstructionNumber());
        emailDTO.setMaterials(orderEvent.getMaterials());
        return emailDTO;
    }
}
