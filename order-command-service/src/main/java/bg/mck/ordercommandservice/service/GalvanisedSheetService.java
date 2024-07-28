package bg.mck.ordercommandservice.service;

import bg.mck.ordercommandservice.entity.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.entity.enums.MaterialStatus;
import bg.mck.ordercommandservice.mapper.GalvanisedSheetMapper;
import bg.mck.ordercommandservice.repository.GalvanisedSheetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GalvanisedSheetService {
    private final GalvanisedSheetRepository galvanisedSheetRepository;
    private final GalvanisedSheetMapper galvanisedSheetMapper;

    public GalvanisedSheetService(GalvanisedSheetRepository galvanisedSheetRepository, GalvanisedSheetMapper galvanisedSheetMapper) {
        this.galvanisedSheetRepository = galvanisedSheetRepository;
        this.galvanisedSheetMapper = galvanisedSheetMapper;
    }

    public void cancelGalvanisedSheet(Long id) {
        Optional<GalvanisedSheetEntity> galvanisedSheetById = galvanisedSheetRepository.findById(id);
        if (galvanisedSheetById.isEmpty()) {
            throw new RuntimeException("Galvanised sheet with id " + id + " not found");
        }
        galvanisedSheetById.get().setMaterialStatus(MaterialStatus.CANCELED);
        galvanisedSheetRepository.save(galvanisedSheetById.get());
    }
}
