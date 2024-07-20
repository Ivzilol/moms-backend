package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialType;
import bg.mck.ordercommandservice.repository.FastenerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateOrderService {

    private final FastenerRepository fastenerRepository;

    public UpdateOrderService(FastenerRepository fastenerRepository) {
        this.fastenerRepository = fastenerRepository;
    }

    public void updateOrder(UpdateOrderDTO updateOrderDTO, String email) {
        if (updateOrderDTO.getMaterialType().equals(String.valueOf(MaterialType.FASTENERS))) {
            updateFastenerEntity(updateOrderDTO);
        }
    }

    private void updateFastenerEntity(UpdateOrderDTO updateOrderDTO) {
        Optional<FastenerEntity> fastenerEntity = this.fastenerRepository
                .findById(Long.parseLong(updateOrderDTO.getId()));
        fastenerEntity.get().setType(updateOrderDTO.getType());
        fastenerEntity.get().setDiameter(updateOrderDTO.getDiameter());
        fastenerEntity.get().setLength(updateOrderDTO.getLength());
        fastenerEntity.get().setModel(updateOrderDTO.getModel());
        fastenerEntity.get().setClazz(updateOrderDTO.getClazz());
        fastenerEntity.get().setQuantity(updateOrderDTO.getQuantity());
        fastenerEntity.get().setDescription(updateOrderDTO.getDescription());
        fastenerEntity.get().setSpecificationFileUrl(updateOrderDTO.getSpecificationFileUrl());
        this.fastenerRepository.save(fastenerEntity.get());

    }
}
